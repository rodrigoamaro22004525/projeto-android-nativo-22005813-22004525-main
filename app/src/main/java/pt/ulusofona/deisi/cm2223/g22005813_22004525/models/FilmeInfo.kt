package pt.ulusofona.deisi.cm2223.g22005813_22004525.models

import java.util.*

class FilmeInfo(
    val nome: String,
    val imagemCartaz: String,
    val genero: String,
    val sinopse: String,
    val dataLancamento: Date,
    val avaliacaoIMDB: Double,
    val linkIMDB: String) {
}