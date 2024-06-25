package pt.ulusofona.deisi.cm2223.g22005813_22004525.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22005813_22004525.R
import pt.ulusofona.deisi.cm2223.g22005813_22004525.adapter.FilmeListAdapter
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.FragmentListaBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeAvaliacao
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.NavigationManager

class ListaFragment : Fragment() {
    private lateinit var binding: FragmentListaBinding
    private val model = FilmeRepository.getInstance()
    private lateinit var adapter: FilmeListAdapter //adapter que só é usada pa encontrar os filmes pelo id e dar refresh á lista

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.lista_de_filmes_title)
        val view = inflater.inflate(
            R.layout.fragment_lista, container, false
        )
        binding = FragmentListaBinding.bind(view)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.lvListaFilmes.layoutManager = LinearLayoutManager(requireContext())
        var listaFilmes: List<FilmeAvaliacao> = listOf()

        model.getAllFilmes { result ->
            listaFilmes = result.getOrDefault(listOf())
            adapter = FilmeListAdapter(listaFilmes, ::onFilmeClick)
            CoroutineScope(Dispatchers.Main).launch {
                binding.lvListaFilmes.adapter = adapter
            }
        }
    }

    private fun onFilmeClick(uuid: String) {
        NavigationManager.goToDetailFragment(parentFragmentManager, uuid)
    }
}