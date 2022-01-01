import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2
import utils.clickListener

class GameManager: Extension {
    override var enabled: Boolean = true
    private var game: GameOfLife = GameOfLife()

    fun clearGrid() {
        game.clear()
    }

    fun randomizeGrid() {
        game.randomize()
    }

    fun clickTrigger(position: Vector2) {
       game.clickTrigger(position)
    }


    override fun beforeDraw(drawer: Drawer, program: Program) {
        program.mouse.buttonUp.listen(::clickListener)
        if (isRunning) {
            game.process()
        }
    }

    override fun afterDraw(drawer: Drawer, program: Program) {
        game.draw(drawer)
    }
}