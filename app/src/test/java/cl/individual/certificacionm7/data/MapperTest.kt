package cl.individual.certificacionm7.data

import cl.individual.certificacionm7.data.remote.Superhero
import cl.individual.certificacionm7.data.remote.SuperheroDetails
import org.junit.Assert.*

import org.junit.Test

class MapperTest {

    @Test
    fun transformToSuperheroEntity() {

        // given
        val id = 1
        val nombre = "Spiderman"
        val origen = "example"
        val imagenLink = "www.link.com"
        val poder = "super strength"
        val fechaCreacion = 1900

        val hero = Superhero(id, nombre, origen, imagenLink, poder, fechaCreacion)

        // when
        val result = hero.transformToSuperheroEntity()

        // then
        assertEquals(id, result.id)
        assertEquals(nombre, result.nombre)
        assertEquals(origen, result.origen)
        assertEquals(imagenLink, result.imagenLink)
        assertEquals(poder, result.poder)
        assertEquals(fechaCreacion, result.fechaCreacion)

    }

    @Test
    fun transformToSuperheroDetailsEntity() {

        // given
        val id = 1
        val nombre = "Spiderman"
        val origen = "example"
        val imagenLink = "www.link.com"
        val poder = "super strength"
        val fechaCreacion = 1900
        val color = "red"
        val traduccion = "true"

        val heroDetails = SuperheroDetails(
            id,
            nombre,
            origen,
            imagenLink,
            poder,
            fechaCreacion,
            color,
            traduccion
        )

        // when
        val result = heroDetails.transformToSuperheroDetailsEntity(id)

        // then
        assertEquals(id, result.id)
        assertEquals(nombre, result.nombre)
        assertEquals(origen, result.origen)
        assertEquals(imagenLink, result.imagenLink)
        assertEquals(poder, result.poder)
        assertEquals(fechaCreacion, result.fechaCreacion)
        assertEquals(color, result.color)
        assertEquals(traduccion, result.traduccion)

    }
}