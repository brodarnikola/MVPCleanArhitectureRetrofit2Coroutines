package com.vjezba.mvpcleanarhitecturegithub.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vjezba.mvpcleanarhitecturegithub.R
import com.vjezba.mvpcleanarhitecturegithub.core.entities.RepositoryDetails
import kotlinx.android.synthetic.main.repository_list.view.*


class RepositoryAdapter(var userList: MutableList<RepositoryDetails>, var userListFiltered: MutableList<RepositoryDetails>) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private fun loadIntet() {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.imagePhoto
        val layoutParent: ConstraintLayout = itemView.parentLayout

        val authorName: TextView = itemView.textAuthorName
        val fullName: TextView = itemView.textRepositoryName
        val description: TextView = itemView.textDescription
        val starGazers: TextView = itemView.textStarGazers
        val forksCount: TextView = itemView.textForksCount
        val issueCount: TextView = itemView.textIssueCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userListFiltered[position]

        Picasso.get().load(user.owner.avatar_url)
                .resize(40, 40).centerCrop()
                .into(holder.photo)

        holder.authorName.text = "Name: " + user.owner.login
        holder.fullName.text = "Repositoriy: " + user.name
        holder.description.text = "Description: " + user.description
        holder.starGazers.text = "Star gazers: " + user.stargazers_count
        holder.forksCount.text = "Forks count: " + user.forks
        holder.issueCount.text = "Issue count: " + user.open_issues

        holder.layoutParent.setOnClickListener{
            loadIntet()
        }
    }

    override fun getItemCount(): Int {
        return userListFiltered.size
    }

    open fun setItems(data: List<RepositoryDetails>) {
        userList = ArrayList(data)
        userListFiltered.addAll(data)
        notifyDataSetChanged()
    }

    fun getItems() = userListFiltered

    /*fun clear() {
        userList.clear()
        userListFiltered.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<RepositoryDetails>) {
        userList.addAll(list)
        userListFiltered.addAll(list)
        notifyDataSetChanged()
    }

    fun add(user: RepositoryDetails) {
        userList.add(user)
        userListFiltered.add(user)
        notifyDataSetChanged()
    }*/


}