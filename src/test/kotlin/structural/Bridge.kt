package structural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

interface Device {
    var volume: Int
    fun getName(): String
}

class Radio: Device {
    override var volume = 0
    override fun getName(): String = "Radio: $this"
}

class TV: Device {
    override var volume = 0
    override fun getName(): String = "TV: $this"
}

interface Remote {
    fun volumeUp()
    fun volumeDown()
}

//basic remote is a bridge, is connection between classes.
class BasicRemote(val device: Device): Remote {
    override fun volumeUp() {
        device.volume++
        println("${device.getName()} volume up: ${device.volume}")
    }

    override fun volumeDown() {
        device.volume--
        println("${device.getName()} volume down: ${device.volume}")
    }
}

class BridgeTes {
    @Test
    fun testBridge() {
        val tv = TV()
        val radio = Radio()

        val tvRemote = BasicRemote(tv)
        val radioRemote = BasicRemote(radio)

        tvRemote.volumeUp()
        tvRemote.volumeUp()
        tvRemote.volumeDown()

        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeDown()

        Assertions.assertThat(tv.volume).isEqualTo(1)
        Assertions.assertThat(radio.volume).isEqualTo(2)
    }
}