import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.random
import org.openrndr.math.Vector2
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

class GameOfLife : Game {
    private var width: Int = WINDOW_WIDTH
    private var height: Int = WINDOW_HEIGHT
    private var squareSize: Double = SQUARE_SIZE
    private var columns: Int = floor(width / squareSize).roundToInt()
    private var rows: Int = floor(height / squareSize).roundToInt()
    private var matrix = mk.zeros<Double>(columns, rows)

    override fun draw(drawer: Drawer) {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                drawer.fill = ColorRGBa(matrix[x, y], matrix[x, y], matrix[x, y])
                drawer.stroke = ColorRGBa(matrix[x, y], matrix[x, y], matrix[x, y])
                drawer.strokeWeight = 0.0

                drawer.rectangle(
                    x * squareSize,
                    y * squareSize,
                    squareSize,
                    squareSize
                )
            }
        }
    }

    fun clear() {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                matrix[x, y] = 0.0
            }
        }
    }

    fun clickTrigger(position: Vector2) {
        val squareX: Int = floor(position.x / squareSize).toInt()
        val squareY: Int = floor(position.y / squareSize).toInt()
        if (matrix[squareX, squareY] == 1.0) {
            matrix[squareX, squareY] = 0.0
        } else {
            matrix[squareX, squareY] = 1.0
        }
    }

    fun randomize() {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                matrix[x, y] = floor(random(0.0, 1.1))
            }
        }
    }

    override fun process() {
        for (x in 0 until matrix.shape[0]) {
            for (y in 0 until matrix.shape[1]) {
                val neighbors = countNeighbors(x, y)
                if (neighbors <= 1) {
                    matrix[x, y] = 0.0
                } else if (neighbors >= 4) {
                    matrix[x, y] = 0.0
                } else if (neighbors == 3) {
                    matrix[x, y] = 1.0
                }
            }
        }
    }


    private fun countNeighbors(x: Int, y: Int): Int {
        var neighbors = 0
        for (xx in max(0, x - 1)..min(columns - 1, x + 1)) {
            for (yy in max(0, y - 1)..min(rows - 1, y + 1)) {
                neighbors += matrix[xx, yy].toInt()
            }
        }
        return neighbors
    }
}