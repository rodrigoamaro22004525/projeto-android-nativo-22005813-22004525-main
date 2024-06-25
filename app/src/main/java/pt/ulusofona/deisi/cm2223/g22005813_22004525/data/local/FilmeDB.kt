package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.*

@Entity(tableName = "filmes")
data class FilmeDB(
    @PrimaryKey val idFilme: String,
    @ColumnInfo(name = "idCinema") val idCinema: Int,
    @ColumnInfo(name = "linkInfo") val linkInfo: String,
    @ColumnInfo(name = "avaliacaoUtilizador") val avaliacaoUtilizador: Int,
    @ColumnInfo(name = "data") val data: Long,
    @ColumnInfo(name = "observacoes") val observacoes: String,
)