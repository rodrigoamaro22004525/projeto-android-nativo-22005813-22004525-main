package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.GenreDB
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.InfoDB

data class InfoWithGenres(
    @Embedded val info: InfoDB,
    @Relation(
        parentColumn = "linkInfo",
        entityColumn = "nameGenre",
        associateBy = Junction(InfoGenreCrossRef::class)
    )
    val genres: List<GenreDB>
)