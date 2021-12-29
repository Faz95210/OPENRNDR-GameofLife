import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.openrndr.application
import org.openrndr.color.ColorRGBa

val GAME_GRID = Grid(WINDOW_WIDTH, WINDOW_HEIGHT, SQUARE_SIZE)
var isRunning = false

fun countNeighbors(x: Int, y: Int, matrix: D2Array<Double>): Int {
    var neighbors = 0
    val columns = matrix.shape[0]
    val rows = matrix.shape[1]
    // TOP ROW
    if (y > 0) {
        if (x > 0 && matrix[x - 1, y - 1] == 1.0) {
            neighbors += 1
        }
        if (matrix[x, y - 1] == 1.0) {
            neighbors += 1
        }
        if (x < columns - 1 && matrix[x + 1, y - 1] == 1.0) {
            neighbors += 1
        }
    }

    // MIDDLE ROW
    if (x > 0 && matrix[x - 1, y] == 1.0) {
        neighbors += 1
    }
    if (x < columns - 1 && matrix[x + 1, y] == 1.0) {
        neighbors += 1
    }

    // BOTTOM ROW
    if (y < rows - 1) {
        if (x > 0 && matrix[x - 1, y + 1] == 1.0) {
            neighbors += 1
        }
        if (matrix[x, y + 1] == 1.0) {
            neighbors += 1
        }
        if (x < columns - 1 && matrix[x + 1, y + 1] == 1.0) {
            neighbors += 1
        }
    }

    return neighbors
}

fun processGameOfLife(matrix: D2Array<Double>) {
    for (x in 0 until matrix.shape[0]) {
        for (y in 0 until matrix.shape[1]) {
            Runnable {
                val neighbors = countNeighbors(x, y, matrix)
                if (matrix[x, y] == 1.0) {
                    if (neighbors <= 1) {
                        matrix[x, y] = 0.0
                    } else if (neighbors >= 4) {
                        matrix[x, y] = 0.0
                    }
                } else {
                    if (neighbors == 3) {
                        matrix[x, y] = 1.0
                    }
                }
            }.run()
        }
    }
}

fun main() = application {
    configure {
        width = WINDOW_WIDTH
        height = WINDOW_HEIGHT
    }

    program {
        var counter = 0
        val userInterface = generateUserInterface()

        keyboard.keyUp.listen(::keyboardListener)
        mouse.buttonUp.listen(::clickListener)

        extend(userInterface)

        extend {

            if (SQUARE_SIZE != GUI_SETTINGS.squareSize) {
                SQUARE_SIZE = GUI_SETTINGS.squareSize
                GAME_GRID.changeSquareSize(SQUARE_SIZE)
            }

            if (TICK_BY_RUN != GUI_SETTINGS.tickDuration) {
                TICK_BY_RUN = GUI_SETTINGS.tickDuration
            }

            drawer.clear(ColorRGBa.PINK)
            GAME_GRID.draw(drawer)
            if (GAME_GRID.getMatrix().isNotEmpty() && isRunning) {
                processGameOfLife(GAME_GRID.getMatrix())
            }
        }
    }
}
