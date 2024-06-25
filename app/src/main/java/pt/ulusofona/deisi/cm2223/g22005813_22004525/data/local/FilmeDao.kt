package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local

import androidx.room.*
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.FilmeWithPhotos
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.InfoGenreCrossRef
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.InfoWithFilmes
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.InfoWithGenres
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.Filme

@Dao
interface FilmeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilme(filme: FilmeDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photo: PhotoDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfo(info: InfoDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: GenreDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfoGenreCrossRef(crossRef: InfoGenreCrossRef)

    @Query("SELECT * FROM infos WHERE linkInfo = :linkInfo")
    fun getInfo(linkInfo: String): InfoDB

    @Query("SELECT * FROM filmes JOIN infos USING (linkInfo) WHERE idFilme = :idFilme")
    fun getFilmeById(idFilme: String): FilmeDB

    @Query("SELECT * FROM filmes JOIN infos USING (linkInfo) WHERE name = :title")
    fun getFilmeByTitle(title: String): FilmeDB

    @Transaction
    @Query("SELECT * FROM infos")
    fun getInfosWithFilme():List<InfoWithFilmes>

    @Transaction
    @Query("SELECT * FROM infos")
    fun getInfosWithGenres():List<InfoWithGenres>

    @Transaction
    @Query("SELECT * FROM filmes WHERE idFilme = :idFilme")
    fun getFilmeWithPhotos(idFilme: String): List<FilmeWithPhotos>

    @Transaction
    @Query("SELECT * FROM infos WHERE linkInfo = :linkInfo")
    fun getInfoWithFilmes(linkInfo: String): List<InfoWithFilmes>

    @Transaction
    @Query("SELECT * FROM infos WHERE linkInfo = :linkInfo")
    fun getInfoWithGenres(linkInfo: String): List<InfoWithGenres>
}