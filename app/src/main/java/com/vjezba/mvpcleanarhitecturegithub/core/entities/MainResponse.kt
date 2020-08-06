package com.vjezba.mvpcleanarhitecturegithub.core.entities


data class MainResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<UserRepo>
)
