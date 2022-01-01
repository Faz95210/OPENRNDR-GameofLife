package utils

import TICK_BY_RUN
import gameManager
import isRunning
import org.openrndr.KeyEvent
import org.openrndr.MouseEvent

fun keyboardListener(it: KeyEvent) {
    when (it.name) {
        "space" -> isRunning = !isRunning
        "c", "C" -> gameManager.clearGrid()
        "r", "R" -> gameManager.randomizeGrid()
        "arrow-up" -> if (TICK_BY_RUN > 0) TICK_BY_RUN -= 1
        "arrow-down" -> TICK_BY_RUN += 1
        else -> println("Pressed ${it.name} ${it.key} ")
    }
}

fun clickListener(it: MouseEvent) {
    gameManager.clickTrigger(it.position)
}