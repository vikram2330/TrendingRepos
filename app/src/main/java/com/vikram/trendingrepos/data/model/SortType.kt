package com.vikram.trendingrepos.data.model

sealed class SortType {
    object SortByName : SortType()
    object SortByStars : SortType()
}