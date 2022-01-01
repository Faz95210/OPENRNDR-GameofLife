import org.openrndr.draw.Drawer

interface Game {
    fun process(){}
    fun draw(drawer: Drawer){}
}