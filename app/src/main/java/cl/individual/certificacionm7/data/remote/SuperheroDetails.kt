package cl.individual.certificacionm7.data.remote

import com.google.gson.annotations.SerializedName

data class SuperheroDetails(
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    @SerializedName("año_creacion") val fechaCreacion: Int,
    val color: String,
    val traduccion: String
)

