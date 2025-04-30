package com.example.superheroes.data

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperHeroImageDetailResponse,
    @SerializedName("biography") val biography: SuperHeroBiography,
    @SerializedName("work") val work: SuperHeroWork,
    @SerializedName("appearance") val appearance: SuperHeroAppearance
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
    @SerializedName("publisher") val publisher: String,
    @SerializedName("place-of-birth") val placeOfBirth: String,
)

data class SuperHeroWork(
    @SerializedName("occupation") val occupation: String,
    @SerializedName("base") val base: String
)

data class SuperHeroAppearance(
    @SerializedName("race") val race: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("eye-color") val eyeColor: String,
    @SerializedName("hair-color") val hairColor: String,
    @SerializedName("height") val height: List<String>,
    @SerializedName("weight") val weight: List<String>,
)