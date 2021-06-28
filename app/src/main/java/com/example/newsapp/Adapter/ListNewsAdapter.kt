package com.example.newsapp.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Activity.News
import com.example.newsapp.R
import com.example.newsapp.model.Article
import com.squareup.picasso.Picasso

class ListNewsAdapter(apikey: String, var articles: ArrayList<Article>, val context: Context): RecyclerView.Adapter<ListNewsAdapter.ViewHolder>() {

    inner class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view){
        val cardView = view.findViewById<CardView>(R.id.list_news_card_view)
        val imageView = view.findViewById<ImageView>(R.id.list_news_imagem_view)
        val title = view.findViewById<TextView>(R.id.list_news_title)
        val description = view.findViewById<TextView>(R.id.list_news_description)
        val author = view.findViewById<TextView>(R.id.list_news_author)
        val url = view.findViewById<TextView>(R.id.list_news_url)
        val publishedAt = view.findViewById<TextView>(R.id.list_news_publishedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val xmlView = LayoutInflater.from(parent.context).inflate(R.layout.list_news, parent, false )
        return ViewHolder(xmlView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(articles.get(position).title)
        holder.description.setText(articles.get(position).description)
        holder.author.setText(articles.get(position).author)
        holder.url.setText(articles.get(position).source.name)
        holder.publishedAt.setText(articles.get(position).publishedAt.toString())
        setImage(articles.get(position).urlToImage?: "", holder.imageView)

        holder.cardView.setOnClickListener {
            Log.e("url cv", articles.get(position).url?:"")
            val intent = Intent(this.context, News::class.java).putExtra("article", articles.get(position))
            this.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    private fun setImage(url: String, imageView: ImageView){
        if(!url.isEmpty()) Picasso.get().load(url).into(imageView)

    }

    fun addNews(arrayOfNews : List<Article>){
        val oldsize = articles.size
        articles.addAll(arrayOfNews)
        notifyItemRangeInserted(oldsize, articles.size)
    }
}