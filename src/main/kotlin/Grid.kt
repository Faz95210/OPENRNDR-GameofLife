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

/*
class Grid {

    fun getMatrix(): D2Array<Double> {
        return shallowMatrix
    }

    fun clearGrid() {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                shallowMatrix[x, y] = 0.0
            }
        }
    }

    fun changeSquareSize(squareSize: Double) {
        this.squareSize = squareSize
        initMatrix()
    }

    fun randomizeGrid() {
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                shallowMatrix[x, y] = floor(random(0.0, 1.1))
            }
        }
    }

    fun draw(drawer: Drawer) {
        for (x in 0 until shallowMatrix.shape[0]) {
            for (y in 0 until shallowMatrix.shape[1]) {
                if (shallowMatrix[x, y] == 0.0) {
                    drawer.fill = ColorRGBa.BLACK
                    drawer.stroke = ColorRGBa.BLACK
                    drawer.strokeWeight = 0.0
                } else {
                    drawer.fill = ColorRGBa.WHITE
                    drawer.stroke = ColorRGBa.BLACK
                    drawer.strokeWeight = 0.0

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
        if (shallowMatrix[squareX, squareY] == 1.0) {
            shallowMatrix[squareX, squareY] = 0.0
        } else {
            shallowMatrix[squareX, squareY] = 1.0
        }
    }

    private fun initMatrix() {
        columns = floor(width / squareSize).roundToInt()
        rows = floor(height / squareSize).roundToInt()
        shallowMatrix = mk.zeros(columns, rows)
    }
}
*/