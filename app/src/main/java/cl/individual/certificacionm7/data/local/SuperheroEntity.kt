package cl.individual.certificacionm7.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "superhero_table")
data class SuperheroEntity (
    @PrimaryKey val id : Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    @ColumnInfo("fecha_creacion") val fechaCreacion: Int
        )