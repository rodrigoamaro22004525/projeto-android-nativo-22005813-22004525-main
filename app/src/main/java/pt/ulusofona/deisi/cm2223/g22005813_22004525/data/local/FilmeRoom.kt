package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local

import androidx.room.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.FilmeWithPhotos
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.InfoGenreCrossRef
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.Filme
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeAvaliacao
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeInfo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class FilmeRoom(private val filmeDao: FilmeDao) : Filme() {
    override fun insertFilme(filme: FilmeAvaliacao, onFinished: (Result<List<FilmeAvaliacao>>) -> Unit) {
        var filmeWithPhotos = mutableListOf<FilmeWithPhotos>()
        val info = filmeDao.getInfoWithFilmes(filme.info.linkIMDB)

        val filmeInput = FilmeDB(
            filme.uuid,
            filme.cinema.id,
            info[0].info.linkInfo,
            filme.avaliacaoUtilizador,
            filme.dataVisto.time,
            filme.observacoes
        )
        filme.fotos.forEach { filmeDao.insertPhotos(PhotoDB(it, filme.uuid)) }
        filmeDao.insertFilme(filmeInput)
    }

    override fun getFilmeInfo(titulo: String, onFinished: (Result<FilmeInfo>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            var infosWithFilmes = filmeDao.getInfosWithFilme()
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            for(info in infosWithFilmes) {
                if(info.info.name == titulo) {
                    var genreString = ""
                    filmeDao.getInfoWithGenres(info.info.linkInfo).forEach {
                        it.genres.forEach { it2 ->
                            genreString += ("${it2.nameGenre}, ")
                        }
                    }
                    genreString = genreString.substring(0, genreString.length - 2)

                    val date = Date(info.info.dataLancamento)
                    val dateToInput = sdf.format(date)
                    val dateToInput2 = sdf.parse(dateToInput)
                    val infoOutput = FilmeInfo(
                        info.info.name,
                        info.info.imagemCartaz,
                        genreString,
                        info.info.sinopse,
                        dateToInput2!!,
                        info.info.avaliacaoIMDB,
                        info.info.linkInfo
                    )
                    onFinished(Result.success(infoOutput))
                }
            }
            onFinished(Result.failure(Exception()))
        }
    }

    override fun getAllFilmes(onFinished: (Result<List<FilmeAvaliacao>>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            var filmes = mutableListOf<FilmeAvaliacao>()
            val infosWithFilmes = filmeDao.getInfosWithFilme()
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val sdf2 = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            for (info in infosWithFilmes) {
                var genreString = ""
                filmeDao.getInfoWithGenres(info.info.linkInfo).forEach {
                    it.genres.forEach { it2 ->
                        genreString += ("${it2.nameGenre}, ")
                    }
                }
                genreString = genreString.substring(0, genreString.length - 2)

                val date = Date(info.info.dataLancamento)
                val dateToInput = sdf.format(date)
                val dateToInput2 = sdf.parse(dateToInput)
                val infoToAdd = FilmeInfo(
                    info.info.name,
                    info.info.imagemCartaz,
                    genreString,
                    info.info.sinopse,
                    dateToInput2!!,
                    info.info.avaliacaoIMDB,
                    info.info.linkInfo
                )
                info.filmes.forEach {
                    val filmeWithPhotos = filmeDao.getFilmeWithPhotos(it.idFilme)
                    val photosInput = mutableListOf<String>()
                    filmeWithPhotos.forEach { it2 ->
                        it2.photos.forEach { photo ->
                            photosInput.add(photo.photoStr)
                        }
                    }

                    val date2 = Date(it.data)
                    val dateToInput3 = sdf2.format(date2)
                    val dateToInput4 = sdf2.parse(dateToInput3)

                    filmes.add(
                        FilmeAvaliacao(
                            infoToAdd,
                            findCinemaById(it.idCinema)!!,
                            it.avaliacaoUtilizador,
                            dateToInput4!!,
                            photosInput,
                            it.observacoes,
                            it.idFilme
                        )
                    )
                }
            }

            onFinished(Result.success(filmes))
        }
    }

    override fun getFilme(id: String, onFinished: (Result<FilmeAvaliacao>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            var filmeOutput: FilmeAvaliacao? = null
            val infosWithFilmes = filmeDao.getInfosWithFilme()
            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val sdf2 = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            var genreString = ""
            var filmeFromFilmeDB: FilmeDB = filmeDao.getFilmeById(id)


            val info = filmeDao.getInfoWithFilmes(filmeFromFilmeDB.linkInfo)

            filmeDao.getInfoWithGenres(filmeFromFilmeDB.linkInfo).forEach {
                it.genres.forEach { it2 ->
                    genreString += ("${it2.nameGenre}, ")
                }
            }

            genreString = genreString.substring(0, genreString.length - 2)

            var infoInput: FilmeInfo? = null
            info.forEach {
                val date = Date(it.info.dataLancamento)
                val dateToInput = sdf.format(date)
                val dateToInput2 = sdf.parse(dateToInput)

                infoInput = FilmeInfo(
                    it.info.name,
                    it.info.imagemCartaz,
                    genreString,
                    it.info.sinopse,
                    dateToInput2!!,
                    it.info.avaliacaoIMDB,
                    it.info.linkInfo
                )

            }


            val date2 = Date(filmeFromFilmeDB.data)
            val dateToInput3 = sdf2.format(date2)
            val dateToInput4 = sdf2.parse(dateToInput3)


            val filmeWithPhotos = filmeDao.getFilmeWithPhotos(filmeFromFilmeDB.idFilme)
            val photosInput = mutableListOf<String>()
            filmeWithPhotos.forEach { it2 ->
                it2.photos.forEach { photo ->
                    photosInput.add(photo.photoStr)
                }
            }

            filmeOutput = FilmeAvaliacao(
                infoInput!!,
                findCinemaById(filmeFromFilmeDB.idCinema)!!,
                filmeFromFilmeDB.avaliacaoUtilizador,
                dateToInput4!!,
                photosInput,
                filmeFromFilmeDB.observacoes,
                filmeFromFilmeDB.idFilme

            )

            onFinished(Result.success(filmeOutput))

        }
    }

    override fun insertFilmeInfo(filmeInfo: FilmeInfo, onFinished: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val genres = mutableListOf<GenreDB>()
            val infoGenreCrossRefs = mutableListOf<InfoGenreCrossRef>()
            val info = InfoDB(
                filmeInfo.linkIMDB,
                filmeInfo.nome,
                filmeInfo.imagemCartaz,
                filmeInfo.sinopse,
                filmeInfo.dataLancamento.time,
                filmeInfo.avaliacaoIMDB
            )

            val genreArray = filmeInfo.genero.split(", ")

            for (genre in genreArray) {
                genres.add(GenreDB(genre))
                infoGenreCrossRefs.add(InfoGenreCrossRef(info.linkInfo, genre))
            }

            filmeDao.insertInfo(info)
            genres.forEach { filmeDao.insertGenre(it) }
            infoGenreCrossRefs.forEach { filmeDao.insertInfoGenreCrossRef(it) }
        }
        onFinished()
    }

    override fun getFilmeByTitle(
        titulo: String,
        onFinished: (Result<FilmeAvaliacao>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            var filmeOutput: FilmeAvaliacao? = null
            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val sdf2 = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            var genreString = ""
            var filmeFromFilmeDB: FilmeDB? = filmeDao.getFilmeByTitle(titulo)

            if(filmeFromFilmeDB == null) {
                onFinished(Result.failure(Exception()))
            } else {
                val info = filmeDao.getInfoWithFilmes(filmeFromFilmeDB.linkInfo)

                filmeDao.getInfoWithGenres(filmeFromFilmeDB.linkInfo).forEach {
                    it.genres.forEach { it2 ->
                        genreString += ("${it2.nameGenre}, ")
                    }
                }

                genreString = genreString.substring(0, genreString.length - 2)

                var infoInput: FilmeInfo? = null
                info.forEach {
                    val date = Date(it.info.dataLancamento)
                    val dateToInput = sdf.format(date)
                    val dateToInput2 = sdf.parse(dateToInput)

                    infoInput = FilmeInfo(
                        it.info.name,
                        it.info.imagemCartaz,
                        genreString,
                        it.info.sinopse,
                        dateToInput2!!,
                        it.info.avaliacaoIMDB,
                        it.info.linkInfo
                    )

                }


                val date2 = Date(filmeFromFilmeDB.data)
                val dateToInput3 = sdf2.format(date2)
                val dateToInput4 = sdf2.parse(dateToInput3)


                val filmeWithPhotos = filmeDao.getFilmeWithPhotos(filmeFromFilmeDB.idFilme)
                val photosInput = mutableListOf<String>()
                filmeWithPhotos.forEach { it2 ->
                    it2.photos.forEach { photo ->
                        photosInput.add(photo.photoStr)
                    }
                }

                filmeOutput = FilmeAvaliacao(
                    infoInput!!,
                    findCinemaById(filmeFromFilmeDB.idCinema)!!,
                    filmeFromFilmeDB.avaliacaoUtilizador,
                    dateToInput4!!,
                    photosInput,
                    filmeFromFilmeDB.observacoes,
                    filmeFromFilmeDB.idFilme

                )

                onFinished(Result.success(filmeOutput))
            }
        }
    }
}