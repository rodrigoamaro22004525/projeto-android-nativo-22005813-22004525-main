package pt.ulusofona.deisi.cm2223.g22005813_22004525.data

import android.content.Context
import android.util.Log
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.Filme
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeAvaliacao
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeInfo

class FilmeRepository(
    private val context: Context,
    private val local: Filme,
    private val remote: Filme
) : Filme() {
    override fun insertFilme(filme: FilmeAvaliacao, onFinished: (Result<List<FilmeAvaliacao>>) -> Unit) {
        local.insertFilme(filme) { result ->
            onFinished(Result.success(result.getOrNull()!!))
        }
    }

    override fun getFilmeInfo(titulo: String, onFinished: (Result<FilmeInfo>) -> Unit) {
        if (ConnectivityUtil.isOnline(context)) {
            remote.getFilmeInfo(titulo) { result ->
                if (result.isSuccess) {
                    result.getOrNull()
                        ?.let { filmeInfo ->
                            Log.i("APP", "Got filme from the server")

                            local.insertFilmeInfo(filmeInfo) {
                                onFinished(Result.success(filmeInfo))
                            }
                        }
                } else {
                    Log.w("APP", "Error getting filme from server")
                    onFinished(Result.failure(Exception()))
                }
            }
        } else if(!ConnectivityUtil.isOnline(context)) {
            Log.i("APP", "App is offline. Getting filme from the database")
            local.getFilmeInfo(titulo) { result ->
                if(result.isSuccess) {
                    onFinished(Result.success(result.getOrNull()!!))
                } else {
                    Log.w("APP", "Error getting filme from DB")
                    onFinished(Result.failure(Exception()))
                }
            }
        }
    }

    override fun getAllFilmes(onFinished: (Result<List<FilmeAvaliacao>>) -> Unit) {
        local.getAllFilmes{result ->
            onFinished(Result.success(result.getOrDefault(mutableListOf())))
        }
    }

    override fun getFilme(id: String, onFinished: (Result<FilmeAvaliacao>) -> Unit) {
        local.getFilme(id, onFinished)
    }

    override fun insertFilmeInfo(filmeInfo: FilmeInfo, onFinished: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getFilmeByTitle(
        titulo: String,
        onFinished: (Result<FilmeAvaliacao>) -> Unit
    ) {
        local.getFilmeByTitle(titulo) { result ->
            if(result.isSuccess) {
                onFinished(Result.success(result.getOrNull()!!))
            } else {
                onFinished(Result.failure(Exception()))
            }
        }
    }

    companion object {
        private var instance: FilmeRepository? = null

        // Temos de executar o init antes do getInstance
        fun init(context: Context, local: Filme, remote: Filme) {
            if (instance == null) {
                instance = FilmeRepository(context, local, remote)
            }
        }

        fun getInstance(): FilmeRepository {
            if (instance == null) {
                // Primeiro temos de invocar o init, se não lança esta Exception
                throw IllegalStateException("singleton not initialized")
            }
            return instance as FilmeRepository
        }

    }
}