package com.example.newsapp.Activity

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newsapp.Adapter.ListNewsAdapter
import com.example.newsapp.R
import com.example.newsapp.Utils.Utils
import com.example.newsapp.ViewModel.ArticleVM
import com.example.newsapp.model.Article

class MainActivity : AppCompatActivity(){
    private lateinit var adapter : ListNewsAdapter
    private lateinit var recycleViewNews: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var image_btn_change_theme: ImageButton
    private var pageActual = 1
    private var listOfArticles: ArrayList<Article> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        supportActionBar.apply {
            setActionBar(findViewById(R.id.activity_main_toolbar))
        }
        initViews()
        loadRecycleViewNews()
        Utils().setThemeOnActivity(this)
    }

    private fun loadRecycleViewNews(){
        val apikey = this.getString(R.string.news_api_key)
        ArticleVM(this, apikey).getAll("Tecnologia", "1").observe(this){
            listOfArticles.addAll(it)
            adapter = ListNewsAdapter(apikey, listOfArticles, this)
            recycleViewNews.adapter = adapter
        }
    }

    private fun loadRecycleViewNewsNextPage(){
        val apikey = this.getString(R.string.news_api_key)
        pageActual++
        ArticleVM(this, apikey).getAll("Tecnologia", pageActual.toString()).observe(this){
            listOfArticles.addAll(it)
            adapter.addNews(it)
        }
    }

    private fun initViews(){
        image_btn_change_theme = findViewById(R.id.activity_main_img_btn_change_theme)
        recycleViewNews = findViewById(R.id.activity_main_recycle_view)
        recycleViewNews.layoutManager = LinearLayoutManager(this)
        swipeRefresh = findViewById(R.id.activity_main_swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            loadRecycleViewNews()
            swipeRefresh.isRefreshing = false
        }
        recycleViewNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!recyclerView.canScrollVertically(1)){
                    loadRecycleViewNewsNextPage()
                    Toast.makeText(this@MainActivity, "carregando novas news", Toast.LENGTH_LONG).show()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        image_btn_change_theme.setOnClickListener {
            val utils = Utils()
            utils.changeTheme(this)
            utils.setThemeOnActivity(this)
        }
    }
}