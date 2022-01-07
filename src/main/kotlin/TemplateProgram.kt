import org.openrndr.application

val gameManager = GameManager()
var isRunning = false

fun main() = application {
    configure {
        width = WINDOW_WIDTH
        height = WINDOW_HEIGHT
    }

    program {
        extend(gameManager)
    }
}
