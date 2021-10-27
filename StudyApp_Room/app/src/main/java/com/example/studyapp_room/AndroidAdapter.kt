package com.example.studyapp_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class AndroidAdapter ( private val android:AndroidActivity, private val listandroid: List<AndroidLesson>):  RecyclerView.Adapter<AndroidAdapter.ItemViewHolder>(){
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var lesson = listandroid[position]

        holder.itemView.apply {
            tvtitle.text = lesson.title
            tvdescription.text = lesson.description
            cvCard.setOnClickListener { CustomAlertDialog(android, lesson.title, lesson.details) }
            editBtn.setOnClickListener {
                var id = lesson.id
                var title = lesson.title
                var description = lesson.description
                var details = lesson.details
                android.editAlert(id,title,description,details)

            }
            deleteBtn.setOnClickListener {
                var id = lesson.id
                var title = lesson.title
                var description = lesson.description
                var details = lesson.details
                android.deleteAlert(id,title,description,details)
            }
        }
    }

    override fun getItemCount() = listandroid.size
}