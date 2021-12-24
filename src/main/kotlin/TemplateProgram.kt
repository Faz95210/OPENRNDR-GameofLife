import org.openrndr.application
import org.openrndr.color.ColorRGBa

fun main() = application {
    configure {
        width = WINDOW_WIDTH
        height = WINDOW_HEIGHT
    }

    program {
        val grid = Grid(WINDOW_WIDTH, WINDOW_HEIGHT, SQUARE_SIZE)
        mouse.buttonUp.listen {
            grid.clickTrigger(it.position)
        }
        extend {
            drawer.clear(ColorRGBa.PINK)
            grid.draw(drawer)
        }
    }
}
