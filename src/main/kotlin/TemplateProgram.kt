import org.openrndr.application
import utils.keyboardListener

val gameManager = GameManager()
var isRunning = false

fun main() = application {
    configure {
        width = WINDOW_WIDTH
        height = WINDOW_HEIGHT
    }

    program {
        keyboard.keyUp.listen(::keyboardListener)

        extend(gameManager)
    }
}
