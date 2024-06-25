package pt.ulusofona.deisi.cm2223.g22005813_22004525.models

import java.util.*

class FilmeAvaliacao(
    var info: FilmeInfo,
    var cinema: Cinema,
    var avaliacaoUtilizador: Int,
    var dataVisto: Date,
    var fotos: MutableList<String>,
    var observacoes: String = "",
    val uuid: String = UUID.randomUUID().toString()
) {
}