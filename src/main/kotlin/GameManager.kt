import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2
import utils.clickListener
import utils.keyboardListener

class GameManager : Extension {
    override var enabled: Boolean = true
    private var game: GameOfLife = GameOfLife()
    private var tick = 0

    override fun setup(program: Program) {
        program.keyboard.keyUp.listen(::keyboardListener)
        program.mouse.buttonUp.listen(::clickListener)
    }

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
        if (isRunning && tick++ > TICK_BY_RUN) {
            game.process()
            tick = 0
        }
    }

    override fun afterDraw(drawer: Drawer, program: Program) {
        game.draw(drawer)
    }
}