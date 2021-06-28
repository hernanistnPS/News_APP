package com.example.newsapp.Activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import com.example.newsapp.R
import com.example.newsapp.model.Article
import com.squareup.picasso.Picasso

class News : AppCompatActivity() {
    private lateinit var btn_share : ImageButton
    private lateinit var btn_save : ImageButton
    private lateinit var img_news : ImageView
    private lateinit var txt_news : TextView
    private lateinit var article : Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        article = intent.getSerializableExtra("article") as Article
        setContentView(R.layout.activity_news)
        initViews()
        insertDataViews()
    }

    private fun initViews(){
        btn_share = findViewById(R.id.show_news_dialog_image_btn_share)
        btn_save = findViewById(R.id.show_news_dialog_image_btn_save)
        img_news = findViewById(R.id.show_news_dialog_image_view_news)
        txt_news = findViewById(R.id.show_news_dialog_txt_news)

        btn_share.setOnClickListener {
            if(img_news.drawable != null){
                val path = MediaStore.Images.Media.insertImage(contentResolver, img_news.drawable.toBitmap(), "image-share", "")
                val uti = Uri.parse(path)
                Log.e("Path", uti.toString())
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, uti)
                    type = "image/*"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }else{
                Toast.makeText(this, "Não é possível compartilhar esta imagem", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun insertDataViews(){
        img_news.setImageURI(article.url!!.toUri())
        txt_news.setText(String(article.content!!.toByteArray(), Charsets.UTF_8))
        Picasso.get().load(article.urlToImage).into(img_news)
    }


}