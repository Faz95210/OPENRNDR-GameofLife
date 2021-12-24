import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.random
import org.openrndr.math.Vector2
import kotlin.math.floor
import kotlin.math.roundToInt

class Grid(
    width: Int = WINDOW_WIDTH,
    height: Int = WINDOW_HEIGHT,
    private val squareSize: Double = SQUARE_SIZE,
) {
    private val columns: Int = floor(width / squareSize).roundToInt()
    private val rows: Int = floor(height / squareSize).roundToInt()
    private val matrix = mk.zeros<Int>(columns, rows)

    fun getMatrix(): D2Array<Int> {
        return matrix
    }

    fun clearGrid() {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                matrix[x, y] = 0
            }
        }
    }

    fun randomizeGrid() {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                matrix[x, y] = random(0.0, 1.1).toInt()
            }
        }
    }

    fun draw(drawer: Drawer) {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                if (matrix[x, y] == 0) {
                    drawer.fill = ColorRGBa.BLACK
                    drawer.stroke = ColorRGBa.WHITE
                    drawer.strokeWeight = 1.0
                } else {
                    drawer.fill = ColorRGBa.WHITE
                    drawer.stroke = ColorRGBa.BLACK
                    drawer.strokeWeight = 1.0
                }
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
        val onOrOff: Int = matrix[squareX, squareY]
        println("Position: $position square X =  $squareX square Y =  $squareY current value = $onOrOff")

        if (onOrOff == 0) {
            matrix[squareX, squareY] = 1
        } else {
            matrix[squareX, squareY] = 0
        }
    }
}