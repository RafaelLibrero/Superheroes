package com.example.superheroes

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        retrofit = getRetrofit()
        getSuperhero(id)
    }

    private fun getSuperhero(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperheroById(id)
            if (myResponse.body() != null) {
                runOnUiThread {
                    createUI(myResponse.body()!!)
                }
            }
        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvName.text = superhero.name
        binding.tvRealName.text = superhero.biography.fullName
        binding.tvPublisher.text = superhero.biography.publisher
        prepareStats(superhero.powerstats)
    }

    private fun prepareStats(powerStats: PowerStatsResponse) {
        updateHeight(binding.vIntelligence, powerStats.intelligence.toInt())
        updateHeight(binding.vStrength, powerStats.strength.toInt())
        updateHeight(binding.vDurability, powerStats.durability.toInt())
        updateHeight(binding.vPower, powerStats.power.toInt())
        updateHeight(binding.vSpeed, powerStats.speed.toInt())
        updateHeight(binding.vCombat, powerStats.combat.toInt())
    }

    private fun updateHeight(view: View, stat: Int) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/83a008ea5119f21c66e03061f3f1bd8d/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}