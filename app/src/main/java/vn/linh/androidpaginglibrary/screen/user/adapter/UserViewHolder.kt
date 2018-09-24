package vn.linh.androidpaginglibrary.screen.user.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.view.*
import vn.linh.androidpaginglibrary.R
import vn.linh.androidpaginglibrary.data.model.User

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(user: User?) {
        Glide.with(itemView.context)
            .load(user?.avatarUrl)
            .into(itemView.UserAvatar)
        itemView.UserName.text = user?.login
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view)
        }
    }
}