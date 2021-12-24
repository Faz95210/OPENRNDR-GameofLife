import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.openrndr.KEY_SPACEBAR
import org.openrndr.application
import org.openrndr.color.ColorRGBa

fun countNeighbors(x: Int, y: Int, matrix: D2Array<Int>): Int {
    var neighbors = 0
    val columns = matrix.shape[0]
    val rows = matrix.shape[1]
    // TOP ROW
    if (y > 0) {
        if (x > 0 && matrix[x - 1, y - 1] == 1) {
            neighbors += 1
        }
        if (matrix[x, y - 1] == 1) {
            neighbors += 1
        }
        if (x < columns - 1 && matrix[x + 1, y - 1] == 1) {
            neighbors += 1
        }
    }

    // MIDDLE ROW
    if (x > 0 && matrix[x - 1, y] == 1) {
        neighbors += 1
    }
    if (x < columns - 1 && matrix[x + 1, y] == 1) {
        neighbors += 1
    }

    // BOTTOM ROW
    if (y < rows - 1) {
        if (x > 0 && matrix[x - 1, y + 1] == 1) {
            neighbors += 1
        }
        if (matrix[x, y + 1] == 1) {
            neighbors += 1
        }
        if (x < columns - 1 && matrix[x + 1, y + 1] == 1) {
            neighbors += 1
        }
    }

    return neighbors
}

fun processGameOfLife(matrix: D2Array<Int>) {
    for (x in 0 until matrix.shape[0]) {
        for (y in 0 until matrix.shape[1]) {
            val neighbors = countNeighbors(x, y, matrix)
            if (matrix[x, y] == 0) {
                if (neighbors == 3) {
                    matrix[x, y] = 1
                }
            } else {
                if (neighbors <= 1) {
                    matrix[x, y] = 0
                } else if (neighbors >= 4) {
                    matrix[x, y] = 0
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
        val grid = Grid(WINDOW_WIDTH, WINDOW_HEIGHT, SQUARE_SIZE)
        var isRunning = false
        keyboard.keyUp.listen {
            if (it.key == KEY_SPACEBAR) {
                isRunning = !isRunning
            } else if (it.name == "c" || it.name == "C") {
                grid.clearGrid()
            } else if (it.name == "r" || it.name == "R") {
                grid.randomizeGrid()
            } else {
                println("Pressed ${it.name} ${it.key} ")
            }
        }
        mouse.buttonUp.listen {
            grid.clickTrigger(it.position)
        }
        extend {
            drawer.clear(ColorRGBa.PINK)
            grid.draw(drawer)
            if (grid.getMatrix().size > 0 && isRunning)
                processGameOfLife(grid.getMatrix())
        }
    }
}
