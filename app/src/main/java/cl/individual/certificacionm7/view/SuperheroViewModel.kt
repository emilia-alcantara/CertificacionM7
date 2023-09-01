package cl.individual.certificacionm7.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import cl.individual.certificacionm7.data.Repository
import cl.individual.certificacionm7.data.local.SuperheroDatabase
import cl.individual.certificacionm7.data.local.SuperheroDetailsEntity
import cl.individual.certificacionm7.data.remote.SuperheroRetrofit
import kotlinx.coroutines.launch

class SuperheroViewModel(app: Application) : AndroidViewModel(app) {

    val repo: Repository

    init {
        val myAPI = SuperheroRetrofit.getRetrofitSuperhero()
        val myDAO = SuperheroDatabase.getDatabase(app).getSuperheroDAO()

        repo = Repository(myAPI, myDAO)
    }

    fun getAllSuperheroes() = viewModelScope.launch {
        repo.loadSuperheroesToDatabase()
    }

    fun getSuperheroDetails(id: Int) = viewModelScope.launch {
        repo.loadSuperheroDetailsToDatabase(id)
    }

    fun superheroesLiveData() = repo.getSuperheroesFromLocal()

    fun superheroesDetailsLiveData(id: Int): LiveData<SuperheroDetailsEntity> =
        repo.getSuperheroDetailsFromLocal(id)


}