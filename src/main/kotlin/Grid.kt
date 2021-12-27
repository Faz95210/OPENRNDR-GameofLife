import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.random
import org.openrndr.math.Vector2
import kotlin.math.floor
import kotlin.math.roundToInt

class Grid(
    private var width: Int = WINDOW_WIDTH,
    private var height: Int = WINDOW_HEIGHT,
    private var squareSize: Double = SQUARE_SIZE,
) {
    private var columns: Int = floor(width / squareSize).roundToInt()
    private var rows: Int = floor(height / squareSize).roundToInt()
    private var matrix: Array<Array<Square>> = Array(columns) { Array(size = rows, init = { j -> Square(squareSize) }) }

    fun getMatrix(): Array<Array<Square>> {
        return matrix
    }

    fun clearGrid() {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                matrix[x][y].empty()
            }
        }
    }

    fun changeSquareSize(squareSize: Double) {
        this.squareSize = squareSize
        initMatrix()
    }

    fun initMatrix() {
        columns = floor(width / squareSize).roundToInt()
        rows = floor(height / squareSize).roundToInt()
        matrix = Array(columns) { Array(size = rows, init = { j -> Square(squareSize) }) }
    }


    fun randomizeGrid() {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                if (random(0.0, 1.1).toInt() == 0)
                    matrix[x][y].empty()
                else
                    matrix[x][y].giveBirth()
            }
        }
    }

    fun draw(drawer: Drawer) {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                val square = matrix[x][y].getSquare()
                drawer.fill = square["fill"] as ColorRGBa?
                drawer.stroke = square["stroke"] as ColorRGBa?
                drawer.strokeWeight = square["strokeWeight"] as Double
                drawer.rectangle(
                    x * squareSize,
                    y * squareSize,
                    squareSize,
                    squareSize
                )
            }
        }
    }

    fun clickTrigger(position: Vector2) {
        val squareX: Int = floor(position.x / squareSize).toInt()
        val squareY: Int = floor(position.y / squareSize).toInt()
        if (matrix[squareX][squareY].isAlive()) {
            matrix[squareX][squareY].kill()
        } else {
            matrix[squareX][squareY].giveBirth()
        }
    }
}