package pt.ulusofona.deisi.cm2223.g22005813_22004525.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ulusofona.deisi.cm2223.g22005813_22004525.R
import pt.ulusofona.deisi.cm2223.g22005813_22004525.adapter.CinemaListAdapter
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.FragmentCinemasBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.ParseCinema

class CinemasFragment : Fragment() {
    private lateinit var binding: FragmentCinemasBinding
    private val model = FilmeRepository.getInstance()
    private var adapter: CinemaListAdapter = CinemaListAdapter(ParseCinema.listaCinemas)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.lista_de_cinemas)
        val view = inflater.inflate(
            R.layout.fragment_cinemas, container, false
        )
        binding = FragmentCinemasBinding.bind(view)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.lvListaCinemas.layoutManager = LinearLayoutManager(requireContext())

        adapter = CinemaListAdapter(ParseCinema.listaCinemas)
        binding.lvListaCinemas.adapter = adapter
    }


}