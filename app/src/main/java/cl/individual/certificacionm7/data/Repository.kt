package cl.individual.certificacionm7.data

import android.util.Log
import androidx.lifecycle.LiveData
import cl.individual.certificacionm7.data.local.SuperheroDAO
import cl.individual.certificacionm7.data.local.SuperheroDetailsEntity
import cl.individual.certificacionm7.data.local.SuperheroEntity
import cl.individual.certificacionm7.data.remote.SuperheroAPI

class Repository(private val superheroAPI: SuperheroAPI, private val superheroDAO: SuperheroDAO) {

    suspend fun loadSuperheroesToDatabase() {
        val response = superheroAPI.getSuperheroesData()
        if (response.isSuccessful) {
            val superheroList = response.body()
            superheroList?.let { superheroes ->
                val superheroEntity = superheroes.map {
                    it.transformToSuperheroEntity()
                }
                superheroDAO.insertSuperheroList(superheroEntity)
            }
        } else {
            Log.e("load SH to database", response.errorBody().toString())
        }
    }

    suspend fun loadSuperheroDetailsToDatabase(id: Int) {
        try {
            val response = superheroAPI.getSuperheroDetails(id)
            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.let { superheroDetails ->
                    val superheroDetailsEntity =
                        superheroDetails.transformToSuperheroDetailsEntity(id)
                    superheroDAO.insertSuperheroDetails(superheroDetailsEntity)
                }
            } else {
                Log.e("load details to database", response.errorBody().toString())
            }
        } catch (exception: Exception) {
            Log.e("catch exception", exception.toString())
        }
    }

    fun getSuperheroesFromLocal(): LiveData<List<SuperheroEntity>> = superheroDAO.getSuperheroData()

    fun getSuperheroDetailsFromLocal(id: Int): LiveData<SuperheroDetailsEntity> =
        superheroDAO.getSuperheroDetails(id)

}