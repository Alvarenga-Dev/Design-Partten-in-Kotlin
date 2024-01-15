import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

sealed class Country {
    object Canada : Country()
}

object Spain : Country()
class Greece(val someProperty: String) : Country()
data class Brazil(val someProperty: String) : Country()

class Currency(val code: String)

object CurrencyFactory {
    fun currencyForCountry(country: Country): Currency =
        when (country) {
            is Spain -> Currency("EUR")
            is Greece -> Currency("EUR")
            is Brazil -> Currency("BRL")
            is Country.Canada -> Currency("CAD")
        }
}

class FactoryMethodTest {
    @Test
    fun currencyTest() {
        val greekCurrency = CurrencyFactory.currencyForCountry(Greece("")).code
        println("Greek currency: $greekCurrency")

        val brazilCurrency = CurrencyFactory.currencyForCountry(Brazil("")).code
        println("Brazil currency: $brazilCurrency")

        assertThat(greekCurrency).isEqualTo("EUR")
        assertThat(brazilCurrency).isEqualTo("BRL")
    }
}
