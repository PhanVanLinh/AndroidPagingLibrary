package vn.linh.androidpaginglibrary.screen.user

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import vn.linh.androidpaginglibrary.data.source.UsersDataSource
import vn.linh.androidpaginglibrary.data.source.UsersDataSourceFactory
import vn.linh.androidpaginglibrary.data.model.User
import vn.linh.androidpaginglibrary.data.source.remote.api.GithubService

class UsersViewModel : ViewModel() {

    var userList: LiveData<PagedList<User>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 10

    private val sourceFactory: UsersDataSourceFactory

    init {
        sourceFactory = UsersDataSourceFactory(
            compositeDisposable, GithubService.getService())
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(true)
            .build()
        userList = LivePagedListBuilder<Long, User>(sourceFactory, config).build()
    }

    fun isLoading(): LiveData<Boolean> = Transformations.switchMap<UsersDataSource, Boolean>(
        sourceFactory.usersDataSourceLiveData) { it.isLoading }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}