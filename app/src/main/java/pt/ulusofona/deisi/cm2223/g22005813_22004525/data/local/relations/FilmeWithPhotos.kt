package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.FilmeDB
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.PhotoDB

data class FilmeWithPhotos(
    @Embedded val filme: FilmeDB,
    @Relation(
        parentColumn = "idFilme",
        entityColumn = "idFilme"
    )
    val photos: List<PhotoDB>
)