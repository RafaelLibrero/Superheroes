package com.example.superheroes

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("search/{name}")
    suspend fun getSuperheroByName(@Path("name") superheroName: String):Response<SuperHeroDataResponse>
}