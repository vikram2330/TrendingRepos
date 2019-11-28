package com.vikram.trendingrepos.utils

import io.reactivex.Scheduler



interface IRxSchedulers {
    fun main(): Scheduler
    fun io(): Scheduler
    fun compute(): Scheduler
}