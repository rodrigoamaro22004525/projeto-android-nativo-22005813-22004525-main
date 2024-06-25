package pt.ulusofona.deisi.cm2223.g22005813_22004525.location

import android.util.Log
import com.google.android.gms.location.LocationResult

interface OnLocationChangedListener {
    fun onLocationChanged(latitude: Double, longitude: Double)
}