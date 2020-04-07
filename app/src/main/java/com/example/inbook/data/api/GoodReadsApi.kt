package com.example.inbook.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GoodReadsApi {

    @GET("//books/v1/volumes")
    fun volumes(@Query("q") isbn: String?,
                @Query("apikey") apiKey: String): VolumesResponse?

    @GET("author/show/{id}")
    fun getAuthorById(@Path("id") id: Long,
                      @Query("apikey") apiKey: String): AuthorResponse?
}