package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.InfoGenreCrossRef

@Database(
    entities = [
        FilmeDB::class,
        InfoDB::class,
        PhotoDB::class,
        GenreDB::class,
        InfoGenreCrossRef::class
    ],
    version = 1
)
abstract class FilmeDatabase : RoomDatabase() {
    abstract val filmeDao: FilmeDao

    companion object {
        private var instance: FilmeDatabase? = null

        fun getInstance(context: Context): FilmeDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        FilmeDatabase::class.java,
                        "filme_db"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance as FilmeDatabase
            }
        }
    }
}