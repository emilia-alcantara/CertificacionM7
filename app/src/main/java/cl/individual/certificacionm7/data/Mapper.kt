package cl.individual.certificacionm7.data

import cl.individual.certificacionm7.data.local.SuperheroDetailsEntity
import cl.individual.certificacionm7.data.local.SuperheroEntity
import cl.individual.certificacionm7.data.remote.Superhero
import cl.individual.certificacionm7.data.remote.SuperheroDetails

fun Superhero.transformToSuperheroEntity(): SuperheroEntity = SuperheroEntity(
    this.id,
    this.nombre,
    this.origen,
    this.imagenLink,
    this.poder,
    this.fechaCreacion
)

fun SuperheroDetails.transformToSuperheroDetailsEntity(id: Int): SuperheroDetailsEntity =
    SuperheroDetailsEntity(
        id,
        this.nombre,
        this.origen,
        this.imagenLink,
        this.poder,
        this.fechaCreacion,
        this.color,
        this.traduccion
    )