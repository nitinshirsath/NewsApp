package com.example.mynews.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynews.R
import com.example.mynews.model.Articles
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NewsAdapter(private val newsList: List<Articles>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news_adapter, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        return holder.bind(newsList[position])
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val photo: CircleImageView = itemView.findViewById(R.id.im_news_img)
    private val tvNewsTitle: TextView = itemView.findViewById(R.id.tv_news_title)
    private val tvNewsDesc: TextView = itemView.findViewById(R.id.tv_news_desc)
    private val tvNewsUrl: TextView = itemView.findViewById(R.id.tv_news_url)
    private val tvNewsAuthor: TextView = itemView.findViewById(R.id.tv_news_author)
    private val tvPublishAt: TextView = itemView.findViewById(R.id.tv_publish_at)

    fun bind(articles: Articles) {
        Glide.with(itemView.context).load(articles.urlToImage)
            .into(photo)
        tvNewsTitle.text = articles.title
        tvNewsDesc.text = articles.description
        tvNewsUrl.text = articles.url
        tvNewsAuthor.text = articles.author
        tvPublishAt.text = articles.publishedAt
    }
}