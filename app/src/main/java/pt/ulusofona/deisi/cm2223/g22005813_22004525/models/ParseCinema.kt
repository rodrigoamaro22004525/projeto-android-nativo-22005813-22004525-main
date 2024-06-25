package pt.ulusofona.deisi.cm2223.g22005813_22004525.models

import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

object ParseCinema {
    var listaCinemas: MutableList<Cinema> = mutableListOf()

    fun loadCinemas(inputStream: InputStream) {
        val json = readJSONFromAsset(inputStream)

        val jsonObject = JSONObject(json)
        val cinemasArray = jsonObject.getJSONArray("cinemas")

        for (i in 0 until cinemasArray.length()) {
            val cinemaJson = cinemasArray.getJSONObject(i)

            val ratingsArray = cinemaJson.getJSONArray("ratings")
            val ratings = parseRatings(ratingsArray)

            val openingHoursObject = cinemaJson.getJSONObject("opening_hours")
            val openingHours = parseOpeningHours(openingHoursObject)

            val cinema = Cinema(
                cinemaJson.getInt("cinema_id"),
                cinemaJson.getString("cinema_name"),
                cinemaJson.getString("cinema_provider"),
                cinemaJson.getDouble("latitude"),
                cinemaJson.getDouble("longitude"),
                cinemaJson.getString("address"),
                cinemaJson.getString("postcode"),
                cinemaJson.getString("county"),
                ratings,
                openingHours
            )

            listaCinemas.add(cinema)
        }

    }

    fun parseRatings(ratingsArray: JSONArray): List<Pair<String, Int>> {
        val ratings = mutableListOf<Pair<String, Int>>()
        for (i in 0 until ratingsArray.length()) {
            val ratingObject = ratingsArray.getJSONObject(i)
            val category = ratingObject.getString("category")
            val score = ratingObject.getInt("score")
            ratings.add(category to score)
        }
        return ratings
    }

    fun parseOpeningHours(openingHoursObject: JSONObject): List<Triple<String, String, String>> {
        val openingHours = mutableListOf<Triple<String, String, String>>()
        val days = openingHoursObject.keys()
        for (day in days) {
            val dayObject = openingHoursObject.getJSONObject(day)
            val open = dayObject.getString("open")
            val close = dayObject.getString("close")
            openingHours.add(Triple(day, open, close))
        }
        return openingHours
    }

    private fun readJSONFromAsset(inputStream: InputStream): String {
        val json: String
        try {
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        return json
    }
}