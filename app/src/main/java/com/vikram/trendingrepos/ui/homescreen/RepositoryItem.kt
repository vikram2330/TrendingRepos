package com.vikram.trendingrepos.ui.homescreen

data class RepositoryItem(
    val username: String?,
    val repoName: String?,
    val repoDesc: String?,
    val language: String?,
    val languageColor: String?,
    val stars: Int = 0,
    val forks: Int = 0,
    val avatarUrl: String?
)