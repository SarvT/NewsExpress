package com.example.theqnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MAdapter(private val clickCheck:itemClicked): RecyclerView.Adapter<ViewHolder>() {

    private val items: ArrayList<Source> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false) //converts the layout we made to a view
        val viewHolder = ViewHolder(view)
        view.setOnClickListener{
            clickCheck.clicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.heading.text = currentItem.title
        holder.subHeading.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imgUrl).into(holder.picture)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updater(updatedSource:ArrayList<Source>){
        items.clear()
        items.addAll(updatedSource)

        notifyDataSetChanged() // tells the updater to update yourself thn all the three functions will run again.
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val heading:TextView = itemView.findViewById(R.id.itemHeadingText)
    val subHeading:TextView = itemView.findViewById(R.id.subHeadingText)
    val picture:ImageView = itemView.findViewById(R.id.pictureTitle)
}

interface itemClicked{
    fun clicked(item: Source)
}