package com.example.superheroes.activities

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.utils.ApiService
import com.example.superheroes.R
import com.example.superheroes.data.SuperHeroDetailResponse
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
    private lateinit var superhero: SuperHeroDetailResponse

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

        binding.navigationView.setOnItemSelectedListener { menuItem ->
            binding.contentBiography.root.visibility = View.GONE
            binding.contentAppearance.root.visibility = View.GONE
            binding.contentStats.root.visibility = View.GONE

            when (menuItem.itemId) {
                R.id.menu_biography -> binding.contentBiography.root.visibility = View.VISIBLE
                R.id.menu_appearance -> binding.contentAppearance.root.visibility = View.VISIBLE
                R.id.menu_stats -> binding.contentStats.root.visibility = View.VISIBLE
            }
            true
        }

        binding.navigationView.selectedItemId = R.id.menu_biography
    }

    private fun getSuperhero(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperheroById(id)
            if (myResponse.body() != null) {
                runOnUiThread {
                    superhero = myResponse.body()!!
                    createUI()
                }
            }
        }
    }

    private fun createUI() {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvName.text = superhero.name
        binding.tvRealName.text = superhero.biography.fullName
        loadData()
    }

    private fun loadData() {
        // Biography
        binding.contentBiography.publisherTextView.text = superhero.biography.publisher
        binding.contentBiography.placeOfBirthTextView.text = superhero.biography.placeOfBirth
        binding.contentBiography.alignmentTextView.text = superhero.biography.alignment
        binding.contentBiography.alignmentTextView.setTextColor(getColor(superhero.getAlignmentColor()))
        binding.contentBiography.occupationTextView.text = superhero.work.occupation
        binding.contentBiography.baseTextView.text = superhero.work.base

        //Appearance
        binding.contentAppearance.genderTextView.text = superhero.appearance.gender
        binding.contentAppearance.raceTextView.text = superhero.appearance.race
        binding.contentAppearance.eyeColorTextView.text = superhero.appearance.eyeColor
        binding.contentAppearance.hairColorTextView.text = superhero.appearance.hairColor
        binding.contentAppearance.weightTextView.text = superhero.appearance.weight[1]
        binding.contentAppearance.heightTextView.text = superhero.appearance.height[1]

        //Stats
        updateHeight(binding.contentStats.vIntelligence, superhero.powerstats.intelligence)
        updateHeight(binding.contentStats.vStrength, superhero.powerstats.strength)
        updateHeight(binding.contentStats.vDurability, superhero.powerstats.durability)
        updateHeight(binding.contentStats.vPower, superhero.powerstats.power)
        updateHeight(binding.contentStats.vSpeed, superhero.powerstats.speed)
        updateHeight(binding.contentStats.vCombat, superhero.powerstats.combat)
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