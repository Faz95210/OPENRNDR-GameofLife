import org.openrndr.KeyEvent
import org.openrndr.MouseEvent

fun keyboardListener(it: KeyEvent) {
    when (it.name) {
        "space" -> isRunning = !isRunning
        "c", "C" -> GAME_GRID.clearGrid()
        "r", "R" -> GAME_GRID.randomizeGrid()
        "arrow-up" -> if (TICK_BY_RUN > 0) TICK_BY_RUN -= 1
        "arrow-down" -> TICK_BY_RUN += 1
        else -> println("Pressed ${it.name} ${it.key} ")
    }
}

fun clickListener(it: MouseEvent) {
    GAME_GRID.clickTrigger(it.position)
}