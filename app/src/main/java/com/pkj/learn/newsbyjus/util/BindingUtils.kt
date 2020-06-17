package com.pkj.learn.newsbyjus.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.Coil
import coil.api.load
import com.pkj.learn.newsbyjus.R

/**
 * @author Pankaj Jangid
 */

class BindingUtils {

    companion object{

        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(view: ImageView, url: String?){
            view.load(url){
                crossfade(true)
                placeholder(R.drawable.placeholder)
                fallback(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
        }
    }

}