import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class AlertBox {
    var message: String? = null

    fun show() {
        println("AlertBox $this: $message")
    }
}

class Window {
    val box by lazy { AlertBox() }

    fun showMessage(message: String) {
        box.message = message
        box.show()
    }
}

class Window2 {
    lateinit var box: AlertBox

    fun showMessage(message: String) {
        box = AlertBox()
        box.message = message
        box.show()
    }
}

class LazyInitializationTest {
    @Test
    fun windowTest() {
        val window = Window()
        window.showMessage("First Window")
        Assertions.assertThat(window.box).isNotNull

        println("-----------------")

        val window2 = Window2()
        window2.showMessage("Second Window")
        Assertions.assertThat(window2.box).isNotNull
    }
}