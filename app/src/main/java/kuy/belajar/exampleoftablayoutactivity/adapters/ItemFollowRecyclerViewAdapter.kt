package kuy.belajar.exampleoftablayoutactivity.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kuy.belajar.exampleoftablayoutactivity.R
import kuy.belajar.exampleoftablayoutactivity.model.GithubUser

class ItemFollowRecyclerViewAdapter() : RecyclerView.Adapter<ItemFollowRecyclerViewAdapter.ViewHolder>() {

    private val listUsers = ArrayList<GithubUser>()

    // buat fungsi set data
    fun setData(users : List<GithubUser>) {
        listUsers.clear()
        listUsers.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_following_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listUsers[position]
        holder.contentView.text = item.login
    }

    override fun getItemCount(): Int = listUsers.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.user_name)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}