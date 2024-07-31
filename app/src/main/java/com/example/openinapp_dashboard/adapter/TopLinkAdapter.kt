package com.example.openinapp_dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openinapp_dashboard.R
import com.example.openinapp_dashboard.models.TopLink

class TopLinkAdapter : ListAdapter<TopLink, TopLinkAdapter.TopLinkViewHolder>(TopLinkDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopLinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return TopLinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopLinkViewHolder, position: Int) {
        val topLink = getItem(position)
        holder.bind(topLink)
    }

    class TopLinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.original_image)
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val dateTextView: TextView = itemView.findViewById(R.id.created_at)
        private val linkTextView: TextView = itemView.findViewById(R.id.web_link)
        private val clicksTextView: TextView = itemView.findViewById(R.id.total_clicks)

        fun bind(topLink: TopLink) {
            titleTextView.text = topLink.title
            dateTextView.text = topLink.created_at
            linkTextView.text = topLink.web_link
            clicksTextView.text = topLink.total_clicks.toString()

            Glide.with(itemView.context)
                .load(topLink.original_image)
                .into(imageView)
        }
    }
}

class TopLinkDiffCallback : DiffUtil.ItemCallback<TopLink>() {
    override fun areItemsTheSame(oldItem: TopLink, newItem: TopLink): Boolean {
        return oldItem.url_id == newItem.url_id
    }

    override fun areContentsTheSame(oldItem: TopLink, newItem: TopLink): Boolean {
        return oldItem == newItem
    }
}