package pt.ulusofona.deisi.cm2223.g22005813_22004525.fragments

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22005813_22004525.R
import pt.ulusofona.deisi.cm2223.g22005813_22004525.adapter.PhotosAdapter
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.ConnectivityUtil
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.FragmentRegistoBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.Cinema
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeAvaliacao
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.NavigationManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


const val REQUEST_IMAGE_CAPTURE = 1

class RegistoFragment : Fragment() {
    private lateinit var binding: FragmentRegistoBinding
    private var tvSelectedDate: TextInputEditText? = null
    private lateinit var finalDate: Date
    private var adapter: PhotosAdapter = PhotosAdapter()
    private var fotos: MutableList<String> = mutableListOf()
    private val model = FilmeRepository.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.registo_de_filme_title)
        val view = inflater.inflate(
            R.layout.fragment_registo, container, false
        )
        binding = FragmentRegistoBinding.bind(view)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val adapterCinema: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.select_dialog_singlechoice,
            model.itemsCinema()
        )
        val cinemaTextView = binding.tfNomeCinema
        cinemaTextView.threshold = 1
        cinemaTextView.setAdapter(adapterCinema)

        tvSelectedDate = binding.tfEscolherDataEdit

        binding.lvListaPhotos.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.lvListaPhotos.adapter = adapter

        binding.tfEscolherDataEdit.setOnClickListener {
            clickDatePicker()
        }

        binding.btFoto.setOnClickListener {
            openCamera()
        }

        binding.btSubmeter.setOnClickListener {
            registerForm()
        }
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap: Bitmap = data?.extras?.get("data") as Bitmap
            fotos.add(model.encodeBitmapToBase64(imageBitmap))
            adapter.updateItems(fotos)
        }
    }

    private fun registerForm() {
        val nomeFilme = binding.tfNomeFilme.text.toString()
        val cinema = binding.tfNomeCinema.text.toString()
        val avaliacao = binding.tfFilmeAvaliacao.text.toString()
        val dataDoFilme = binding.tfEscolherDataEdit.text.toString()
        var observacao = ""
        val fotosToInput = fotos

        invalidFormDisplay(true, cinema, avaliacao, dataDoFilme, observacao)
        
        if (binding.tfObservacoes.text.toString() == ""){
            observacao = resources.getString(R.string.n_o_foram_introduzidos_coment_rios)
        } else {
            observacao = binding.tfObservacoes.text.toString()
        }

        if (!ConnectivityUtil.isOnline(requireContext())) {
            Toast.makeText(context, resources.getString(R.string.n_o_conseguiu_aceder_internet), Toast.LENGTH_LONG).show()
            return
        }

        if (!model.validForm(cinema, avaliacao, dataDoFilme, observacao)) {
            invalidFormDisplay(false, cinema, avaliacao, dataDoFilme, observacao)
            return
        }

        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        CoroutineScope(Dispatchers.IO).launch {
            model.getFilmeInfo(nomeFilme) { result ->
                if (result.isSuccess) {
                    // Dar tempo para a info ser introduzida
                    // na base de dados caso haja má performance
                    Thread.sleep(100)

                    val filme = FilmeAvaliacao(
                        result.getOrNull()!!,
                        model.findCinema(cinema)!!,
                        avaliacao.toInt(),
                        formatter.parse(dataDoFilme)!!,
                        fotosToInput,
                        observacao
                    )
                    model.insertFilme(filme) {}
                } else {
                    MainScope().launch {
                        invalidFormDisplay(true, cinema, avaliacao, dataDoFilme, observacao)
                        Toast.makeText(context, resources.getString(R.string.movie_not_found), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        clearForm()
    }

    private fun invalidFormDisplay(
        nome: Boolean,
        cinema: String,
        avaliacao: String,
        dataDoFilme: String,
        observacao: String
    ) {
        val red = ColorStateList.valueOf(resources.getColor(R.color.red))
        val message = resources.getString(R.string.fieldError)
        if (nome) {
            binding.nameFilmeRequired.helperText = message
            binding.nameFilmeRequired.setHelperTextColor(red)
        } else {
            binding.nameFilmeRequired.helperText = ""
        }
        if (!model.validCinema(cinema)) {
            binding.nameCinemaRequired.helperText = message
            binding.nameCinemaRequired.setHelperTextColor(red)
        } else {
            binding.nameCinemaRequired.helperText = ""
        }
        if (!model.validRating(avaliacao)) {
            binding.nameAvaliacaoRequired.helperText = message
            binding.nameAvaliacaoRequired.setHelperTextColor(red)
        } else {
            binding.nameAvaliacaoRequired.helperText = ""
        }
        if (dataDoFilme.isNullOrBlank()) {
            binding.tfEscolherData.helperText = message
            binding.tfEscolherData.setHelperTextColor(red)
        } else {
            binding.tfEscolherData.helperText = ""
        }
        if (!model.validObservacao(observacao)) {
            binding.nameObservacoes.helperText = message
            binding.nameObservacoes.setHelperTextColor(red)
        } else {
            binding.nameObservacoes.helperText = ""
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireActivity(),
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                // Estas 3 linhas sao logica de negocio
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                finalDate = sdf.parse(selectedDate) as Date

                tvSelectedDate?.setText(sdf.format(finalDate))
            },
            year,
            month,
            day
        )
        // make datepicker only for past dates
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }

    private fun clearForm() {
        fotos = mutableListOf()
        binding.tfNomeFilme.setText("").toString()
        binding.tfNomeCinema.setText("").toString()
        binding.tfFilmeAvaliacao.setText("").toString()
        binding.tfEscolherDataEdit.setText("").toString()
        adapter.updateItems(fotos)
        binding.tfObservacoes.setText("").toString()
        binding.nameFilmeRequired.helperText = ""
        binding.nameCinemaRequired.helperText = ""
        binding.nameAvaliacaoRequired.helperText = ""
        binding.tfEscolherData.helperText = ""
        binding.nameObservacoes.helperText = ""
    }
}