package com.example.newsapp.Utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.newsapp.R

class Utils {
    fun getTheme(context: Context): SharedPreferences? {
        return context.getSharedPreferences(
            context.getString(R.string.app_theme_id),
            Context.MODE_PRIVATE
        )
    }

    fun changeTheme(context: Context) {
        val themeConfig = getTheme(context) ?: return
        if (themeConfig.getString(context.getString(R.string.actual_theme), "") == context.getString(R.string.theme_white)) {
            with(themeConfig.edit()) {
                putString(context.getString(R.string.actual_theme),context.getString(R.string.theme_black)
                )
                apply()
                Log.e("Tema alterado", "Para black")
            }
        } else {
            with(themeConfig.edit()) {
                putString(
                    context.getString(R.string.actual_theme),
                    context.getString(R.string.theme_white)
                )
                apply()
                Log.e("Tema alterado", "Para white")
            }
        }
    }

    fun getThemeString(context: Context): String? {
        return context.getSharedPreferences(
            context.getString(R.string.app_theme_id),
            Context.MODE_PRIVATE
        ).getString(context.getString(R.string.actual_theme), "")
    }

    fun setThemeOnActivity(context: Context){
        val theme = Utils().getThemeString(context)
        if(theme == context.getString(R.string.theme_white)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}