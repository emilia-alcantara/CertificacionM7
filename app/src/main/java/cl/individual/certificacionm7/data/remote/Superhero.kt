package cl.individual.certificacionm7.data.remote

import com.google.gson.annotations.SerializedName


data class Superhero (
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    @SerializedName("Año_creacion") val fechaCreacion: Int
        )