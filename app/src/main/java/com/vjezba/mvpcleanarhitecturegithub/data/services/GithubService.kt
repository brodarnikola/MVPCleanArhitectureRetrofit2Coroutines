package com.vjezba.mvpcleanarhitecturegithub.data.services

import com.vjezba.mvpcleanarhitecturegithub.data.model.MainResponseDAO
import com.vjezba.mvpcleanarhitecturegithub.data.model.RepositoryDAO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    @Headers("Content-Type: application/json")
    //@Headers("Cache-Control: no-cache")
    fun getUserRepoAsync(@Query("q") q: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Deferred<MainResponseDAO>


    @GET("search/repositories")
    @Headers("Content-Type: application/json")
    fun getRepositoryAsync(@Query("q") q: String, @Query("sort") sort: String, @Query("order") order: String,
                           @Query("page") page:Int, @Query("per_page") pageNumber: Int): Deferred<RepositoryDAO>

}