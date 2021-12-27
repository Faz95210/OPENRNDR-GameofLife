import org.openrndr.application
import org.openrndr.color.ColorRGBa

val GAME_GRID = Grid(WINDOW_WIDTH, WINDOW_HEIGHT, SQUARE_SIZE)
var isRunning = false

fun countNeighbors(x: Int, y: Int, matrix: Array<Array<Square>>): Int {
    var neighbors = 0
    val columns = matrix.size
    val rows = matrix[0].size
    // TOP ROW
    if (y > 0) {
        if (x > 0 && matrix[x - 1][y - 1].isAlive()) {
            neighbors += 1
        }
        if (matrix[x][y - 1].isAlive()) {
            neighbors += 1
        }
        if (x < columns - 1 && matrix[x + 1][y - 1].isAlive()) {
            neighbors += 1
        }
    }

    // MIDDLE ROW
    if (x > 0 && matrix[x - 1][y].isAlive()) {
        neighbors += 1
    }
    if (x < columns - 1 && matrix[x + 1][y].isAlive()) {
        neighbors += 1
    }

    // BOTTOM ROW
    if (y < rows - 1) {
        if (x > 0 && matrix[x - 1][y + 1].isAlive()) {
            neighbors += 1
        }
        if (matrix[x][y + 1].isAlive()) {
            neighbors += 1
        }
        if (x < columns - 1 && matrix[x + 1][y + 1].isAlive()) {
            neighbors += 1
        }
    }

    return neighbors
}

fun processGameOfLife(matrix: Array<Array<Square>>) {
    for (x in matrix.indices) {
        for (y in matrix[x].indices) {
            Runnable {
                val neighbors = countNeighbors(x, y, matrix)
                if (matrix[x][y].isAlive()) {
                    if (neighbors <= 1) {
                        matrix[x][y].kill()
                    } else if (neighbors >= 4) {
                        matrix[x][y].kill()
                    }
                } else {
                    if (neighbors == 3) {
                        matrix[x][y].giveBirth()
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
                if (counter >= TICK_BY_RUN) {
                    processGameOfLife(GAME_GRID.getMatrix())
                    counter = 0
                } else {
                    counter += 1
                }
            }
        }
    }
}
