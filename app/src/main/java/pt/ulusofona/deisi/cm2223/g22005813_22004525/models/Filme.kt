package pt.ulusofona.deisi.cm2223.g22005813_22004525.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import org.json.JSONArray
import org.json.JSONObject
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.FilmeDB
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.ParseCinema.listaCinemas
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*

abstract class Filme {

    abstract fun insertFilme(filme: FilmeAvaliacao, onFinished: (Result<List<FilmeAvaliacao>>) -> Unit)
    abstract fun getFilmeInfo(titulo: String, onFinished: (Result<FilmeInfo>) -> Unit)
    abstract fun getAllFilmes(onFinished: (Result<List<FilmeAvaliacao>>) -> Unit)
    abstract fun getFilme(id: String, onFinished: (Result<FilmeAvaliacao>) -> Unit)
    abstract fun insertFilmeInfo(filmeInfo: FilmeInfo, onFinished: () -> Unit)
    abstract fun getFilmeByTitle(titulo: String, onFinished: (Result<FilmeAvaliacao>) -> Unit)


    fun mostWatchedCategory(listaFilmes: List<FilmeAvaliacao>): String {
        var map = mutableMapOf<String, Int>()
        var max = -1
        var result = ""

        if(listaFilmes.isEmpty()) {
            return "N/A"
        }

        for(filmes in listaFilmes) {
            var generos = filmes.info.genero.split(", ")
            for(category in generos) {
                if(map.containsKey(category)) {
                    var count = map.getValue(category)
                    map.remove(category)
                    map.put(category, ++count)
                } else {
                    map.put(category, 1)
                }
            }
        }

        for(category in map) {
            if(category.value > max) {
                result = category.key
            }
        }

        return result
    }

    fun itemsCinema(): List<String> {
        var result: MutableList<String> = mutableListOf()

        for(cinema in listaCinemas) {
            result.add(cinema.name)
        }
        return result
    }

    fun findCinema(name: String) : Cinema? {
        for(cinema in listaCinemas) {
            if(cinema.name == name) {
                return cinema
            }
        }
        return null
    }

    fun findCinemaById(id: Int) : Cinema? {
        for(cinema in listaCinemas) {
            if(cinema.id == id) {
                return cinema
            }
        }
        return null
    }

    fun validForm(
        cinema: String?,
        avaliacao: String?,
        data: String?,
        observacao: String?
    ): Boolean {
        return validCinema(cinema) && validRating(avaliacao) && !data.isNullOrBlank() &&
                validObservacao(observacao)
    }

    fun validObservacao(observacao: String?): Boolean {
        if (observacao == null)
            return false

        if (observacao.length <= 200) {
            return true
        }

        return false
    }

    fun validCinema(nome: String?): Boolean {
        if (nome == null)
            return false

        var isValid = false
        for(cinema in listaCinemas) {
            if(cinema.name == nome) {
                isValid = true
            }
        }

        return isValid
    }

    fun validRating(avaliacao: String?): Boolean {
        if (avaliacao == null || avaliacao.isEmpty()) {
            return false
        }
        val valor = avaliacao.toInt()
        return valor in 0..10
    }

    fun encodeBitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun decodeBase64ToImageView(base64String: String): Bitmap {
        val byteArray = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    fun buildHorario(cinema: Cinema): String {
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK]
        var result = ""

        when (day) {
            Calendar.MONDAY -> {
                result += cinema.openingHours[0].second + "-" + cinema.openingHours[0].third
            }
            Calendar.TUESDAY -> {
                result += cinema.openingHours[1].second + "-" + cinema.openingHours[1].third
            }
            Calendar.WEDNESDAY -> {
                result += cinema.openingHours[2].second + "-" + cinema.openingHours[2].third
            }
            Calendar.THURSDAY -> {
                result += cinema.openingHours[3].second + "-" + cinema.openingHours[3].third
            }
            Calendar.FRIDAY -> {
                result += cinema.openingHours[4].second + "-" + cinema.openingHours[4].third
            }
            Calendar.SATURDAY -> {
                result += cinema.openingHours[5].second + "-" + cinema.openingHours[5].third
            }
            Calendar.SUNDAY -> {
                result += cinema.openingHours[6].second + "-" + cinema.openingHours[6].third
            }
        }
        return result
    }

    fun isOpen(cinema: Cinema): Boolean {
        val calendar = Calendar.getInstance()

        val hourNow = calendar.time.hours
        val minuteNow = calendar.time.minutes

        val horarioRaw = buildHorario(cinema)
        var horario = horarioRaw.split("-").toMutableList()

        for(i in 0 until horario.size) {
            if(horario[i] == "00:00") {
                horario[i] = "24:00"
            }
        }

        val openingHours = horario[0].split(":")
        val closingHours = horario[1].split(":")


        if(openingHours[0].toInt() <= hourNow && closingHours[0].toInt() > hourNow) {
            if(openingHours[0].toInt() == hourNow) {
                if(openingHours[1].toInt() <= minuteNow) {
                    return true
                }
                return false
            }
            if(closingHours[0].toInt() == hourNow) {
                if(closingHours[1].toInt() > minuteNow) {
                    return true
                }
                return false
            }
            return true
        }
        return false
    }
}