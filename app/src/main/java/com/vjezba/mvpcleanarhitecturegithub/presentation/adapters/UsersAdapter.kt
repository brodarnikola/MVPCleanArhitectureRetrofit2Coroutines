package com.vjezba.mvpcleanarhitecturegithub.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vjezba.domain.entities.UserRepo
import com.vjezba.mvpcleanarhitecturegithub.R
import kotlinx.android.synthetic.main.user_list.view.*


class UsersAdapter(var userList: MutableList<UserRepo>, var userListFiltered: MutableList<UserRepo>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>(), Filterable {

    private fun loadIntet() {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.textRepositoryName
        val photo: ImageView = itemView.imagePhoto
        val layoutParent: ConstraintLayout = itemView.parentLayout
        val repositoryName: TextView = itemView.textDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userListFiltered[position]

        if( user.avatar_url != null )
            Picasso.get().load(user.avatar_url)
                .resize(40, 40).centerCrop()
                .into(holder.photo)

        holder.repositoryName.text = user.login
        holder.fullName.text = user.repos_url
        holder.layoutParent.setOnClickListener{
            loadIntet()
        }
    }

    override fun getItemCount(): Int {
        return userListFiltered.size
    }

    open fun setItems(data: List<UserRepo>) {
        userList = ArrayList(data)
        userListFiltered.addAll(data)
        notifyDataSetChanged()
    }

    fun getItems() = userListFiltered

    fun clear() {
        userList.clear()
        userListFiltered.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<UserRepo>) {
        userList.addAll(list)
        userListFiltered.addAll(list)
        notifyDataSetChanged()
    }

    fun add(user: UserRepo) {
        userList.add(user)
        userListFiltered.add(user)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


}