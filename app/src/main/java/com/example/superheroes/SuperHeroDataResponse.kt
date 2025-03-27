package com.example.superheroes

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperHeroItemResponse>?
)

data class SuperHeroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperHeroImageResponse
)

data class SuperHeroImageResponse(@SerializedName("url") val url: String)