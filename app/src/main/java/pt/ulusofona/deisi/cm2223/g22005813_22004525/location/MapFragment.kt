package pt.ulusofona.deisi.cm2223.g22005813_22004525.location

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22005813_22004525.R
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.FragmentMapBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeAvaliacao
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.NavigationManager
import java.util.*


class MapFragment : Fragment(), OnLocationChangedListener, OnMarkerClickListener {

    private lateinit var binding: FragmentMapBinding
    private var map: GoogleMap? = null
    private lateinit var geocoder: Geocoder
    private val model = FilmeRepository.getInstance()

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.mapa)

        var listaFilmes: List<FilmeAvaliacao> = listOf()

        model.getAllFilmes { result ->
            listaFilmes = result.getOrDefault(listOf())
        }

        geocoder = Geocoder(context, Locale.getDefault())
        binding = FragmentMapBinding.bind(view)
        binding.map.onCreate(savedInstanceState)
        binding.map.getMapAsync { map ->
            this.map = map
            map.isMyLocationEnabled = true
            map.setOnMarkerClickListener(this)

            for (filme in listaFilmes) {
                map.addMarker(
                    when (filme.avaliacaoUtilizador) {
                        in 1..2 -> MarkerOptions().position(
                            LatLng(
                                filme.cinema.latitude,
                                filme.cinema.longitude
                            )
                        )
                            .title(filme.info.nome)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        in 3..4 -> MarkerOptions().position(
                            LatLng(
                                filme.cinema.latitude,
                                filme.cinema.longitude
                            )
                        )
                            .title(filme.info.nome)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                        in 5..6 -> MarkerOptions().position(
                            LatLng(
                                filme.cinema.latitude,
                                filme.cinema.longitude
                            )
                        )
                            .title(filme.info.nome)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                        in 7..8 -> MarkerOptions().position(
                            LatLng(
                                filme.cinema.latitude,
                                filme.cinema.longitude
                            )
                        )
                            .title(filme.info.nome)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        in 9..10 -> MarkerOptions().position(
                            LatLng(
                                filme.cinema.latitude,
                                filme.cinema.longitude
                            )
                        )
                            .title(filme.info.nome)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        else -> MarkerOptions().position(
                            LatLng(
                                filme.cinema.latitude,
                                filme.cinema.longitude
                            )
                        )
                            .title(filme.info.nome)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    }
                )
            }
            FusedLocation.registerListener(this)
        }
        return binding.root

    }

    override fun onLocationChanged(latitude: Double, longitude: Double) {
        placeCamera(latitude, longitude)
        placeCityName(latitude, longitude)
    }

    private fun placeCamera(latitude: Double, longitude: Double) {
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(latitude, longitude))
            .zoom(15f)
            .build()

        map?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun placeCityName(latitude: Double, longitude: Double) {
        val addresses = geocoder.getFromLocation(latitude, longitude, 5)
        val location = addresses.first {
            it.locality != null && it.locality.isNotEmpty()
        }
        binding.tvCityName.text = location.locality
    }

    override fun onDestroy() {
        super.onDestroy()
        FusedLocation.unregisterListener()
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        model.getFilmeByTitle(marker.title!!) {
            NavigationManager.goToDetailFragment(parentFragmentManager, it.getOrNull()!!.uuid)
        }
        return true
    }

}