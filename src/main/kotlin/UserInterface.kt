import org.openrndr.extra.gui.GUI
import org.openrndr.extra.parameters.DoubleParameter
import org.openrndr.extra.parameters.IntParameter

class GuiSettings {
    @DoubleParameter("Square Size", 1.0, 100.0)
    var squareSize: Double = SQUARE_SIZE

    @IntParameter("Tick Duration", 1, 50)
    var tickDuration: Int = TICK_BY_RUN
}
val GUI_SETTINGS  = GuiSettings()

fun generateUserInterface(): GUI {
    val gui = GUI()

    gui.add(GUI_SETTINGS, "Settings")
    return gui
}