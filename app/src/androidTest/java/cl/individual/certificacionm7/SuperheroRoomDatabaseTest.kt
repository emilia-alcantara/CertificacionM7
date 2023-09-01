package cl.individual.certificacionm7

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import cl.individual.certificacionm7.data.local.SuperheroDAO
import cl.individual.certificacionm7.data.local.SuperheroDatabase
import cl.individual.certificacionm7.data.local.SuperheroEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class SuperheroRoomDatabaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var superheroDAO: SuperheroDAO
    private lateinit var database: SuperheroDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, SuperheroDatabase::class.java).build()
        superheroDAO = database.getSuperheroDAO()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertSuperheroes_empty() = runBlocking {
        // given
        val heroesList = listOf<SuperheroEntity>()

        // when
        superheroDAO.insertSuperheroList(heroesList)

        // then A
        val it = superheroDAO.getSuperheroData().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // then B
        superheroDAO.getSuperheroData().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertSuperheroes_happyCase_1element() = runBlocking {
        // given
        val heroesList = listOf(SuperheroEntity(1, "spiderman", "origen", "link", "poderes", 1900))

        // when
        superheroDAO.insertSuperheroList(heroesList)

        // then
        superheroDAO.getSuperheroData().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertSuperheroes_happyCase_3element() = runBlocking {
        // given
        val heroesList = listOf(
            SuperheroEntity(1, "spiderman", "origen", "link", "poderes", 1900),
            SuperheroEntity(2, "iron man", "origen", "link", "poderes", 1900),
            SuperheroEntity(3, "thor", "origen", "link", "poderes", 1900)
        )

        // when
        superheroDAO.insertSuperheroList(heroesList)

        // then
        superheroDAO.getSuperheroData().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}