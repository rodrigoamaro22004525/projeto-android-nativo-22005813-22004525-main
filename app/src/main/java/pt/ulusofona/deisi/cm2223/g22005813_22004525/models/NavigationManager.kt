package pt.ulusofona.deisi.cm2223.g22005813_22004525.models

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.deisi.cm2223.g22005813_22004525.R
import pt.ulusofona.deisi.cm2223.g22005813_22004525.fragments.*
import pt.ulusofona.deisi.cm2223.g22005813_22004525.location.MapFragment

object NavigationManager {

    private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
        val transition = fm.beginTransaction()
        transition.replace(R.id.frame, fragment)
        transition.addToBackStack(null)
        transition.commit()
    }

    fun goToDashboardFragment(fm: FragmentManager) {
        placeFragment(fm, DashboardFragment())
    }

    fun goToRegistoFragment(fm: FragmentManager){
        placeFragment(fm, RegistoFragment())
    }

    fun goToListaFragment(fm: FragmentManager) {
        placeFragment(fm, ListaFragment())
    }

    fun goToDetailFragment(fm: FragmentManager, uuid: String) {
        placeFragment(fm, FilmeDetailFragment.newInstance(uuid))
    }

    fun goToCinemasFragment(fm: FragmentManager) {
        placeFragment(fm, CinemasFragment())
    }

    fun goToMapaFragment(fm: FragmentManager) {
        placeFragment(fm, MapFragment())
    }
}
