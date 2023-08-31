package cl.individual.certificacionm7.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperheroRetrofit {

    companion object {
        private const val URL_BASE =
            "https://y-mariocanedo.vercel.app/"

        fun getRetrofitSuperhero() : SuperheroAPI {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(SuperheroAPI::class.java)
        }
    }
}