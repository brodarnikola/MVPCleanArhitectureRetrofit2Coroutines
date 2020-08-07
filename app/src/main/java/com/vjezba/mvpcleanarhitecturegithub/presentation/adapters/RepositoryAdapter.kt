package com.vjezba.mvpcleanarhitecturegithub.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vjezba.domain.entities.RepositoryDetails
import com.vjezba.mvpcleanarhitecturegithub.R
import com.vjezba.mvpcleanarhitecturegithub.presentation.common.ListDiffer
import kotlinx.android.synthetic.main.repository_list.view.*


class RepositoryAdapter(var repositoryList: MutableList<RepositoryDetails>, var repositoryFiltered: MutableList<RepositoryDetails>) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

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
        val user = repositoryFiltered[position]

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

    fun updateDevices(updatedDevices: List<RepositoryDetails>) {
        val listDiff = ListDiffer.getDiff(
            repositoryFiltered,
            updatedDevices,
            { old, new ->
                old.description == new.description &&
                        old.forks == new.forks &&
                old.name == new.name &&
                old.owner.login == new.owner.login
            })

        for (diff in listDiff) {
            when (diff) {
                is ListDiffer.DiffInserted -> {
                    repositoryFiltered.addAll(diff.elements)
                    notifyItemRangeInserted(diff.position, diff.elements.size)
                }
                is ListDiffer.DiffRemoved -> {
                    //remove devices
                    for (i in (repositoryFiltered.size - 1) downTo diff.position) {
                        repositoryFiltered.removeAt(i)
                    }
                    notifyItemRangeRemoved(diff.position, diff.count)
                }
                is ListDiffer.DiffChanged -> {
                    repositoryFiltered[diff.position] = diff.newElement
                    notifyItemChanged(diff.position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return repositoryFiltered.size
    }

    open fun setItems(data: List<RepositoryDetails>) {
        repositoryFiltered.addAll(data)
        notifyDataSetChanged()
    }

    fun getItems() = repositoryFiltered

}