package cl.individual.certificacionm7.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superhero_details_table")
data class SuperheroDetailsEntity(
    @PrimaryKey val id : Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    @ColumnInfo("fecha_creacion") val fechaCreacion: Int,
    val color: String,
    val traduccion: String
)
