package pt.ulusofona.deisi.cm2223.g22005813_22004525;

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.FilmeDatabase
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.FilmeRoom
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.remote.FilmeOkHttp
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.ActivitySplashScreenBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.location.FusedLocation
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.ParseCinema

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val inputStream = applicationContext.assets.open("cinemas.json")
        ParseCinema.loadCinemas(inputStream)

        FilmeRepository.init(this, initFilmeRoom(), initFilmeOkHttp())
        setContentView(binding.root)
        //this.deleteDatabase("filme_db")
    }

    override fun onStart() {
        super.onStart()
        FusedLocation.start(this)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }

    private fun initFilmeOkHttp(): FilmeOkHttp {
        return FilmeOkHttp(
            "https://www.omdbapi.com/",
            "1a757de3",
            OkHttpClient()
        )
    }

    private fun initFilmeRoom(): FilmeRoom {
        return FilmeRoom(
            FilmeDatabase.getInstance(applicationContext).filmeDao,
        )
    }
}
