package cl.individual.certificacionm7.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SuperheroEntity::class, SuperheroDetailsEntity::class], version = 1)
abstract class SuperheroDatabase : RoomDatabase() {

    abstract fun getSuperheroDAO(): SuperheroDAO

    companion object {
        @Volatile
        private var INSTANCE: SuperheroDatabase? = null

        fun getDatabase(context: Context): SuperheroDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuperheroDatabase::class.java,
                    "superhero_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}