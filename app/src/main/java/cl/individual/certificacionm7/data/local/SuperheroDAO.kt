package cl.individual.certificacionm7.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuperheroDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperheroList(superheroList: List<SuperheroEntity>)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperheroDetails(superheroDetailsEntity: SuperheroDetailsEntity)

    @Query("SELECT * FROM superhero_table ORDER BY id ASC")
    fun getSuperheroData(): LiveData<List<SuperheroEntity>>

    @Query("SELECT * FROM superhero_details_table WHERE id LIKE :id")
    fun getSuperheroDetails(id:Int): LiveData<SuperheroDetailsEntity>
}