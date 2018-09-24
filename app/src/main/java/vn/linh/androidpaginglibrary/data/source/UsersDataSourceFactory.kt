package vn.linh.androidpaginglibrary.data.source

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import vn.linh.androidpaginglibrary.data.source.remote.api.GithubService
import vn.linh.androidpaginglibrary.data.model.User

class UsersDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                             private val githubService: GithubService)
    : DataSource.Factory<Long, User>() {

    val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Long, User> {
        val usersDataSource = UsersDataSource(
            githubService,
            compositeDisposable)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }
}