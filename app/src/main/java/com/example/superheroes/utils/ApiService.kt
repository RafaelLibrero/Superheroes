package com.example.superheroes.utils

import com.example.superheroes.data.SuperHeroDataResponse
import com.example.superheroes.data.SuperHeroDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("search/{name}")
    suspend fun getSuperheroByName(@Path("name") superheroName: String): Response<SuperHeroDataResponse>

    @GET("{id}")
    suspend fun getSuperheroById(@Path("id") superheroId: String): Response<SuperHeroDetailResponse>
}