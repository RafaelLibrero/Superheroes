package com.example.superheroes.data

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

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
    @JsonAdapter(IntegerAdapter::class) @SerializedName("intelligence") val intelligence: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("strength") val strength: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("durability") val durability: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("power") val power: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("speed") val speed: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("combat") val combat: Int
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

class IntegerAdapter : TypeAdapter<Int>() {
    override fun write(out: JsonWriter?, value: Int) {
        out?.value(value)
    }

    override fun read(`in`: JsonReader?): Int {
        return try {
            `in`!!.nextString()!!.toInt()
        } catch (e: Exception) {
            0
        }
    }
}