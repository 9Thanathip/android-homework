package com.example.dominictoretto.data

import android.content.Context
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.bumptech.glide.Glide
fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}