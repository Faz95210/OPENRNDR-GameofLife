import org.openrndr.application
import org.openrndr.color.ColorRGBa

var TICK_BY_RUN = 10

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
        val grid = Grid(WINDOW_WIDTH, WINDOW_HEIGHT, SQUARE_SIZE)
        var isRunning = false
        keyboard.keyUp.listen {
            when (it.name) {
                "space" -> {
                    isRunning = !isRunning
                }
                "c", "C" -> {
                    grid.clearGrid()
                }
                "r", "R" -> {
                    grid.randomizeGrid()
                }
                "arrow-up" -> {
                    if (TICK_BY_RUN > 0)
                        TICK_BY_RUN -= 1
                }
                "arrow-down" -> {
                    TICK_BY_RUN += 1
                }
                else -> {
                    println("Pressed ${it.name} ${it.key} ")
                }
            }
        }
        mouse.buttonUp.listen {
            grid.clickTrigger(it.position)
        }
        extend {
            drawer.clear(ColorRGBa.PINK)
            grid.draw(drawer)
            if (grid.getMatrix().isNotEmpty() && isRunning)
                if (counter >= TICK_BY_RUN) {
                    processGameOfLife(grid.getMatrix())
                    counter = 0
                } else {
                    counter += 1
                }
        }
    }
}
