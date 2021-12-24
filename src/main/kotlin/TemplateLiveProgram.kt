import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.olive.oliveProgram

/**
 *  This is a template for a live program.
 *
 *  It uses oliveProgram {} instead of program {}. All code inside the
 *  oliveProgram {} can be changed while the program is running.
 */


fun main() = application {
    configure {
        width = WINDOW_WIDTH
        height = WINDOW_HEIGHT
    }
    oliveProgram {
        val grid = Grid(WINDOW_WIDTH, WINDOW_HEIGHT, SQUARE_SIZE)
        extend {
            mouse.buttonUp.listen {
                // -- it refers to a MouseEvent instance here
                println(it.position)
                grid.clickTrigger(it.position)
            }
            drawer.clear(ColorRGBa.PINK)
            grid.draw(drawer)
        }
    }
}