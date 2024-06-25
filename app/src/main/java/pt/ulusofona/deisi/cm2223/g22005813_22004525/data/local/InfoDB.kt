package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "infos")
data class InfoDB(
    @PrimaryKey val linkInfo: String,
    val name: String,
    val imagemCartaz: String,
    val sinopse: String,
    val dataLancamento: Long,
    val avaliacaoIMDB: Double
)