package com.vikram.trendingrepos.data.model

data class TrendingRepositoryResponse(
    val author: String?,
    val name: String?,
    val avatar: String?,
    val url: String?,
    val description: String?,
    val language: String?,
    val languageColor: String?,
    val stars: Int = 0,
    val forks: Int = 0,
    val currentPeriodStars: Int = 0,
    val builtBy: List<BuiltBy>?
)

data class BuiltBy(
    val username: String,
    val href: String,
    val avatar: String
)
