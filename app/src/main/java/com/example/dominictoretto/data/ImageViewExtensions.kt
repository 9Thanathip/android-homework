package com.example.dominictoretto.data

import android.content.Context
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.bumptech.glide.Glide

fun ImageView.loadImage(context: Context, imageUrl: String?) {
    val isSvg = imageUrl?.lowercase()?.endsWith(".svg") ?: false
    if (isSvg) {
        this.load(
            imageUrl,
            ImageLoader.Builder(context)
                .components { add(SvgDecoder.Factory()) }
                .build(),
        ) {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
    } else {
        this.load(imageUrl)
    }
}

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}