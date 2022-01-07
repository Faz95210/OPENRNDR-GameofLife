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
    private var livingCells: ArrayList<Pair<Int, Int>> = arrayListOf()

    override fun draw(drawer: Drawer) {
        drawer.strokeWeight = 0.0
        drawer.fill = ColorRGBa.WHITE
        drawer.stroke = ColorRGBa.WHITE

        for (pair in livingCells) {
            drawer.rectangle(
                pair.first * squareSize,
                pair.second * squareSize,
                squareSize,
                squareSize
            )
        }
    }

    fun clear() {
        livingCells.clear()
    }

    fun clickTrigger(position: Vector2) {
        val squareX: Int = floor(position.x / squareSize).toInt()
        val squareY: Int = floor(position.y / squareSize).toInt()
        val pair = Pair(squareX, squareY)
        if (!livingCells.contains(pair)) {
            livingCells.add(pair)
        }
    }

    fun randomize() {
        livingCells.clear()
        for (x in 0 until columns) {
            for (y in 0 until rows) {
                if (floor(random(0.0, 1.1)) >= 1.0)
                    livingCells.add(Pair(x, y))
            }
        }
    }

    override fun process() {
        val daNewnew: ArrayList<Pair<Int, Int>> = arrayListOf()
        if (livingCells.size > 0) {
            for(coordinates in livingCells) {
                val x = coordinates.first
                val y = coordinates.second
                for(xx in max(x -1, 0) .. min(x + 1, columns - 1)) {
                    for(yy in max(y -1, 0) .. min(y + 1, rows - 1)) {
                        val neighbors = countNeighbors(xx, yy)
                        if (neighbors == 3 || (neighbors == 2 && livingCells.contains(Pair(xx, yy)))) {
                            daNewnew.add(Pair(xx, yy))
                        }
                    }
                }
            }
        }
        livingCells.clear()
        livingCells.addAll(daNewnew)

    }

    private fun countNeighbors(x: Int, y: Int): Int {
        var neighbors = 0
        if (livingCells.contains(Pair(x, y - 1)))
            neighbors += 1
        if (livingCells.contains(Pair(x, y + 1)))
            neighbors += 1
        if (livingCells.contains(Pair(x - 1, y - 1)))
            neighbors += 1
        if (livingCells.contains(Pair(x - 1, y + 1)))
            neighbors += 1
        if (livingCells.contains(Pair(x - 1, y)))
            neighbors += 1
        if (livingCells.contains(Pair(x + 1, y - 1)))
            neighbors += 1
        if (livingCells.contains(Pair(x + 1, y)))
            neighbors += 1
        if (livingCells.contains(Pair(x + 1, y + 1)))
            neighbors += 1
        return neighbors
    }
}