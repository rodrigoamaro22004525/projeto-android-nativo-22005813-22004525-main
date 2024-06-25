package pt.ulusofona.deisi.cm2223.g22005813_22004525

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog.show
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.extension.send
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.ActivityMainBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.NavigationManager

private const val SPEECH_REQUEST_CODE = 0

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model = FilmeRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissionsBuilder(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION).build().send { result ->
            if (result.allGranted()) {
                if(!screenRotated(savedInstanceState)) {
                    NavigationManager.goToDashboardFragment(
                        supportFragmentManager
                    )
                }
            } else {
                finish()
            }
        }


    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(binding.toolbar)
        setupDrawerMenu()
    }

    private fun screenRotated(savedInstanceState: Bundle?): Boolean {
        return savedInstanceState != null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun setupDrawerMenu() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawer, binding.toolbar,
            R.string.drawer_open, R.string.drawer_close
        )
        binding.navDrawer.setNavigationItemSelectedListener {
            onClickNavigationItem(it)
        }
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun onClickNavigationItem(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dashboard ->
                NavigationManager.goToDashboardFragment(
                    supportFragmentManager
                )

            R.id.nav_registoDeFilmes ->
                NavigationManager.goToRegistoFragment(
                    supportFragmentManager
                )

            R.id.nav_lista ->
                NavigationManager.goToListaFragment(
                    supportFragmentManager
                )
            R.id.nav_mapa ->
                NavigationManager.goToMapaFragment(
                    supportFragmentManager
                )
            R.id.nav_cinemas ->
                NavigationManager.goToCinemasFragment(
                    supportFragmentManager
                )
        }
        binding.drawer.closeDrawer(GravityCompat.START)
        return true
    } // Alterar drawer mambos

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // We are using switch case because multiple icons can be kept
        when (item.itemId) {
            R.id.micButton -> {
                displaySpeechDialog("")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun displaySpeechDialog(text: String) {
        var message = resources.getString(R.string.dialog_message)
        MaterialAlertDialogBuilder(
            this,
            com.google.android.material.R.style.AlertDialog_AppCompat)
            .setMessage("$message: $text").setNeutralButton(resources.getString(R.string.dialog_voice)) { dialog, which ->
                displaySearch()
            }
            .setNegativeButton(resources.getString(R.string.dialog_leave)) { dialog, which ->
                // Dialog disappears
            }
            .setPositiveButton(resources.getString(R.string.dialog_search)) { dialog, which ->
                searchFilme(text)
            }.show()
    }

    private fun displaySearch() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        // This starts the activity and populates the intent with the speech text.
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            var spokenText =
                data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results!![0]
                }
            displaySpeechDialog(spokenText)
        } else if (requestCode == SPEECH_REQUEST_CODE){
            displaySpeechDialog("")
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun searchFilme(text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            model.getFilmeByTitle(text) { result ->
                if(result.isSuccess) {
                    NavigationManager.goToDetailFragment(supportFragmentManager, result.getOrNull()!!.uuid)
                } else {
                    MainScope().launch {
                        Toast.makeText(applicationContext, resources.getString(R.string.movie_not_found), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}