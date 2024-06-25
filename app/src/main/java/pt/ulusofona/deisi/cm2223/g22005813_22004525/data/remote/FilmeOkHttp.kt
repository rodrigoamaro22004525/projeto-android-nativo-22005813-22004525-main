package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.remote

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.Filme
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeAvaliacao
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeInfo
import java.io.IOException
import java.text.SimpleDateFormat

class FilmeOkHttp(
    private val baseUrl: String, private val apiKey: String, private val client: OkHttpClient
) : Filme() {
    override fun insertFilme(
        filme: FilmeAvaliacao,
        onFinished: (Result<List<FilmeAvaliacao>>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getFilmeInfo(titulo: String, onFinished: (Result<FilmeInfo>) -> Unit) {
        //  https://www.omdbapi.com/?t=John%20Wick&apikey=1a757de3
        val request: Request =
            Request.Builder().url("$baseUrl/?t=$titulo&apikey=$apiKey").build()
        // Request.Builder().url("https://www.omdbapi.com/?t=John%20Wick&apikey=1a757de3").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFinished(Result.failure(e))

            }

            // Processar a resposta ao pedido
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    // Estamos a guardar o objeto assinalado a amarelo no exemplo aqui
                    val jsonObject = JSONObject(body)

                    if (jsonObject.optString("Response") == "False") {
                        onFinished(Result.failure(Exception()))
                    } else {
                        val formatter = SimpleDateFormat("dd MMM yyyy")

                        val filmeInfo = FilmeInfo(
                            jsonObject.getString("Title"),
                            jsonObject.getString("Poster"),
                            jsonObject.getString("Genre"),
                            jsonObject.getString("Plot"),
                            formatter.parse(jsonObject.getString("Released"))!!,
                            jsonObject.getDouble("imdbRating"),
                            jsonObject.getString("imdbID"),
                        )
                        onFinished(Result.success(filmeInfo))
                    }
                }
            }
        })
    }

    override fun getAllFilmes(onFinished: (Result<List<FilmeAvaliacao>>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getFilme(id: String, onFinished: (Result<FilmeAvaliacao>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun insertFilmeInfo(filmeInfo: FilmeInfo, onFinished: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getFilmeByTitle(titulo: String, onFinished: (Result<FilmeAvaliacao>) -> Unit) {
        TODO("Not yet implemented")
    }
}