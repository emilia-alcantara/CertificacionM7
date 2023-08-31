package cl.individual.certificacionm7.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroAPI {

    @GET("superheroes/")
    suspend fun getSuperheroesData(): Response<List<Superhero>>

    @GET("superheroes/{id}")
    suspend fun getSuperheroDetails(@Path("id") id:Int) : Response<SuperheroDetails>
}