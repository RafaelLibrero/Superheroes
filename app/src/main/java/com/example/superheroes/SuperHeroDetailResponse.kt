package com.example.superheroes

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperHeroImageDetailResponse,
    @SerializedName("biography") val biography: SuperHeroBiography
)

data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("combat") val combat: String
)

data class SuperHeroImageDetailResponse(@SerializedName("url") val url: String)

data class SuperHeroBiography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String
)