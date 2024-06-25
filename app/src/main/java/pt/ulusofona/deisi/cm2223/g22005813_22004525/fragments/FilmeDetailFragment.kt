package pt.ulusofona.deisi.cm2223.g22005813_22004525.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22005813_22004525.R
import pt.ulusofona.deisi.cm2223.g22005813_22004525.adapter.PhotosAdapter
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.FragmentFilmeDetailBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.Filme
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeAvaliacao
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

private const val ARG_OPERATION_UUID = "ARG_OPERATION_UUID"

class FilmeDetailFragment : Fragment() {
    private lateinit var binding: FragmentFilmeDetailBinding
    private lateinit var adapter: PhotosAdapter //adapter que só é usada pa encontrar os filmes pelo id e dar refresh á lista
    private var filmeUUID: String? = null
    private var fotosAdd: String = ""
    private var resCode by Delegates.notNull<Int>()
    private val model = FilmeRepository.getInstance()
    //para receber os detalhes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            filmeUUID = it.getString(ARG_OPERATION_UUID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.detalhe_de_filme)
        val view = inflater.inflate(
            R.layout.fragment_filme_detail, container, false
        )
        binding = FragmentFilmeDetailBinding.bind(view)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        filmeUUID?.let { uuid ->
            model.getFilme(uuid) { result ->
                result.getOrNull()?.let { filmeAvaliacao ->
                    adapter = PhotosAdapter(filmeAvaliacao.fotos)
                    model.getFilmeInfo(filmeAvaliacao.info.nome) { result ->
                        if (result.isSuccess) {
                            placeData(
                                FilmeAvaliacao(
                                    result.getOrNull()!!,
                                    filmeAvaliacao.cinema,
                                    filmeAvaliacao.avaliacaoUtilizador,
                                    filmeAvaliacao.dataVisto,
                                    filmeAvaliacao.fotos,
                                    filmeAvaliacao.observacoes,
                                    filmeAvaliacao.uuid
                                )
                            )
                        } else {
                            placeData(filmeAvaliacao)
                        }
                    }
                }
            }
        }

        binding.lvListaPhotos.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

    private fun placeData(filme: FilmeAvaliacao) {
        var generoText = ""
        CoroutineScope(Dispatchers.Main).launch {
            if (filme.fotos.isEmpty()) {
                binding.tvSemFoto.visibility = View.VISIBLE
                binding.lvListaPhotos.visibility = View.INVISIBLE
            } else {
                adapter.updateItems(filme.fotos)
                binding.lvListaPhotos.adapter = adapter
            }

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val generoArray = filme.info.genero.split(", ")
            for (genero in generoArray) {
                if (genero == generoArray[generoArray.size - 1]) {
                    generoText += genero
                } else {
                    generoText += "$genero, "
                }
            }
            Glide.with(requireContext())
                .load(filme.info.imagemCartaz)
                .into(binding.ivPoster)
            binding.tvDetailFilme.text = "  " + filme.info.nome
            binding.tvDetailGenero.text = "  " + generoText
            binding.tvDetailSinopse.text = "  " + filme.info.sinopse
            binding.tvDetailImdbRating.text = "  " + filme.info.avaliacaoIMDB.toString()
            binding.tvDetailDataLanc.text = "  " + sdf.format(filme.info.dataLancamento)
            binding.tvDetailImdbLink.text = "  " + filme.info.linkIMDB
            binding.tvDetailCinema.text = "  " + filme.cinema.name
            binding.tvDetailAvaliacao.text = "  " + filme.avaliacaoUtilizador.toString()
            binding.tvDetailData.text = "  " + sdf.format(filme.dataVisto)
            binding.tvDetailObservaEs.text = "  " + filme.observacoes

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap: Bitmap = data?.extras?.get("data") as Bitmap
            resCode = resultCode
            fotosAdd = model.encodeBitmapToBase64(imageBitmap)
            val f: MutableList<String> = mutableListOf()
            adapter.updateItems(f)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(uuid: String) =
            FilmeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_OPERATION_UUID, uuid)
                }
            }
    }
}