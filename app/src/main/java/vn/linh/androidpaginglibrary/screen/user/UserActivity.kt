package vn.linh.androidpaginglibrary.screen.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_user.*
import vn.linh.androidpaginglibrary.R
import vn.linh.androidpaginglibrary.screen.user.adapter.UserAdapter
import vn.linh.androidpaginglibrary.data.model.User

class UserActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        usersViewModel = ViewModelProviders.of(this).get(
            UsersViewModel::class.java)
        initRecyclerView()
        observer()
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        userAdapter = UserAdapter()
        recycler_users.layoutManager = linearLayoutManager
        recycler_users.adapter = userAdapter
        recycler_users.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun observer() {
        usersViewModel.userList.observe(this,
            Observer<PagedList<User>> { userAdapter.submitList(it) })

        usersViewModel.isLoading().observe(this, Observer {
            progress_bar_loading.visibility = if (it == true) View.VISIBLE else View.GONE
        })
    }
}
