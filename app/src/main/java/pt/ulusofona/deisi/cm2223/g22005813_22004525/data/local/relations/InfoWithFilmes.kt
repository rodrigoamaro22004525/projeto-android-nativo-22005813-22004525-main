package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.FilmeDB
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.InfoDB
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.PhotoDB

data class InfoWithFilmes(
    @Embedded val info: InfoDB,
    @Relation(
        parentColumn = "linkInfo",
        entityColumn = "linkInfo"
    )
    val filmes: List<FilmeDB>
)