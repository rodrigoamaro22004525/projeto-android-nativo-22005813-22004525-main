package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoDB(
    @PrimaryKey val photoStr: String,
    val idFilme: String
)