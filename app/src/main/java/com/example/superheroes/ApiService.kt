package com.example.superheroes

import retrofit2.http.GET

interface ApiService {

    @GET("all.json")
    suspend fun getSuperheroes()
}