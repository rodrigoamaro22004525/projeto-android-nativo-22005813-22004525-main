package pt.ulusofona.deisi.cm2223.g22005813_22004525.models

class Cinema(
    val id: Int,
    val name: String,
    val provider: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val postCode: String,
    val county: String,
    val ratings: List<Pair<String, Int>>,
    val openingHours: List<Triple<String, String, String>>,
    val logoUrl: String = "",
    val photos: List<String> = mutableListOf()
) {

}