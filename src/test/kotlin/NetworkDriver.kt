import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

object NetworkDriver {
    init {
        println("Init: $this")
    }

    fun log(): NetworkDriver = apply { println("Network driver: $this") }
}

class SingletonTest {

    @Test
    fun testSingleton() {
        println("start")

        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()

        assertThat(networkDriver1).isSameAs(NetworkDriver)
        assertThat(networkDriver2).isSameAs(NetworkDriver)
    }
}