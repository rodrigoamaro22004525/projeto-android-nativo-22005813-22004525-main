package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["linkInfo", "nameGenre"])
data class InfoGenreCrossRef(
    val linkInfo: String,
    val nameGenre: String,
)