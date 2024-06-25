package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "genres")
data class GenreDB(
    @PrimaryKey val nameGenre: String,
)

