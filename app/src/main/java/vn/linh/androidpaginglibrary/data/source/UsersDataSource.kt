package vn.linh.androidpaginglibrary.data.source

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import vn.linh.androidpaginglibrary.data.model.User
import vn.linh.androidpaginglibrary.data.source.remote.api.GithubService

class UsersDataSource(
    private val githubService: GithubService,
    private val compositeDisposable: CompositeDisposable)
    : ItemKeyedDataSource<Long, User>() {

    val isLoading = MutableLiveData<Boolean>()

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User>) {
        compositeDisposable.add(
            githubService.getUsers(1, params.requestedLoadSize)
                .doOnSubscribe {
                    isLoading.postValue(true)
                }
                .subscribe({ users ->
                    isLoading.postValue(false)
                    callback.onResult(users)
                }, { throwable ->
                    isLoading.postValue(false)
                }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User>) {
        compositeDisposable.add(
            githubService.getUsers(params.key, params.requestedLoadSize)
                .doOnSubscribe {
                    isLoading.postValue(true)
                }
                .subscribe({ users ->
                    isLoading.postValue(false)
                    callback.onResult(users)
                }, { throwable ->
                    isLoading.postValue(false)
                }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User>) {
    }

    override fun getKey(item: User): Long {
        return item.id
    }
}