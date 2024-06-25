package pt.ulusofona.deisi.cm2223.g22005813_22004525.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22005813_22004525.R
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.FragmentDashboardBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.location.OnLocationChangedListener
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeAvaliacao
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.NavigationManager

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val model = FilmeRepository.getInstance()
    private var mapSmall: MapView? = null
    private var mapSmallBundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.dashboard)
        val view = inflater.inflate(
            R.layout.fragment_dashboard, container, false
        )
        binding = FragmentDashboardBinding.bind(view)


        mapSmall = MapView(requireContext())
        mapSmall?.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        binding.mapContainer.addView(mapSmall)
        return binding.root
    }

    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()

        mapSmall?.getMapAsync {it1 ->
            it1.isMyLocationEnabled = true
            var hasCameraMoved = false;

            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    var cameraPosition = CameraPosition.Builder()
                        .target(currentLatLng)
                        .zoom(7f)
                        .build()

                    it1.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                }
            }
            var cameraPosition: CameraPosition? = null
            if (!hasCameraMoved){
                cameraPosition = CameraPosition.Builder()
                    .target(LatLng(38.6949792, -8.942057))
                    .zoom(7f)
                    .build()
                it1.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }



            // Para não podermos interagir com o mapa, assim quando clicarmos, vamos para o map fragment
            it1.uiSettings.isMapToolbarEnabled = false
            it1.uiSettings.isZoomControlsEnabled = false
            it1.uiSettings.isZoomGesturesEnabled = false
            it1.uiSettings.isScrollGesturesEnabled = false
            it1.setOnMapClickListener {
                NavigationManager.goToMapaFragment(parentFragmentManager)
            }
        }

        var listaFilmes: List<FilmeAvaliacao> = listOf()

        model.getAllFilmes { result ->
            listaFilmes = result.getOrDefault(listOf())

            CoroutineScope(Dispatchers.Main).launch {
                binding.tvCounterMovies.text = listaFilmes.size.toString()
                binding.tvMostWatched.text = model.mostWatchedCategory(listaFilmes)
            }
        }
        binding.btRegister.setOnClickListener {
            NavigationManager.goToRegistoFragment(parentFragmentManager)
        }
        binding.llMap.setOnClickListener {
            NavigationManager.goToMapaFragment(parentFragmentManager)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapSmall?.onCreate(savedInstanceState)
        mapSmallBundle = savedInstanceState
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapSmall?.onSaveInstanceState(outState)
    }

    // Tratamento de lifecycle
    override fun onResume() {
        super.onResume()
        mapSmall?.onResume()
    }

    // Tratamento de lifecycle
    override fun onPause() {
        super.onPause()
        mapSmall?.onPause()
    }

    // Tratamento de lifecycle
    override fun onDestroy() {
        super.onDestroy()
        mapSmall?.onDestroy()
    }

    // Tratamento de lifecycle
    override fun onLowMemory() {
        super.onLowMemory()
        mapSmall?.onLowMemory()
    }
}