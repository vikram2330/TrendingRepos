package com.vikram.trendingrepos.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vikram.trendingrepos.data.model.TrendingRepositoryResponse
import com.vikram.trendingrepos.ui.homescreen.RepositoryItem

fun List<TrendingRepositoryResponse>.toRepositoryItemList(): List<RepositoryItem> {
    val list = mutableListOf<RepositoryItem>()
    forEach {
        list.add(it.toRepositoryItem())
    }
    return list
}

fun TrendingRepositoryResponse.toRepositoryItem(): RepositoryItem {
    return RepositoryItem(
        username = this.author,
        repoName = this.name,
        repoDesc = this.description,
        language = this.language,
        languageColor = this.languageColor,
        stars = this.stars,
        forks = this.forks,
        avatarUrl = this.avatar
    )
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun TextView.setDrawableStartColor(color: String) {
    compoundDrawables[0]?.let {
        val wrappedDrawable = DrawableCompat.wrap(it)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(color))
    }
}

fun ImageView.setRoundedImage(url: String) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions().circleCrop())
        .into(this)
}

fun RecyclerView.setDivider(@DrawableRes drawableRes: Int) {
    val divider = DividerItemDecoration(
        this.context,
        DividerItemDecoration.VERTICAL
    )
    val drawable = ContextCompat.getDrawable(
        this.context,
        drawableRes
    )
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}