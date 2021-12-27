import org.openrndr.color.ColorRGBa

class Square(size: Double) {
    private var status = Status.EMPTY

    private var fillColor = ColorRGBa(0.0, 0.0, 0.0)
    private var strokeColor = ColorRGBa(1.0, 1.0, 1.0);

    fun kill() {
        status = Status.DYING
    }

    fun giveBirth() {
        status = Status.BIRTHING
    }

    fun empty() {
        status = Status.EMPTY
    }

    fun isAlive(): Boolean {
        return status == Status.ALIVE || status == Status.BIRTHING
    }

    fun getSquare(): Map<String, Any> {
        when (status) {
            Status.EMPTY -> {
                fillColor = ColorRGBa(0.0, 0.0, 0.0)
                strokeColor = ColorRGBa(1.0, 1.0, 1.0)
            }
            Status.DYING -> {
                val step = 1 / TICK_BY_RUN
                if (fillColor.r > 0 && strokeColor.r < 1) {
                    fillColor = ColorRGBa(fillColor.r - step, fillColor.g - step, fillColor.b - step)
                    strokeColor = ColorRGBa(fillColor.r + step, fillColor.g + step, fillColor.b + step)
                } else {
                    fillColor = ColorRGBa(0.0, 0.0, 0.0)
                    strokeColor = ColorRGBa(1.0, 1.0, 1.0)
                    status = Status.DEAD
                }
            }

            Status.DEAD -> {
                fillColor = ColorRGBa(0.0, 0.0, 0.0)
                strokeColor = ColorRGBa(1.0, 1.0, 1.0)
            }
            Status.BIRTHING -> {
                val step = 1.0 / TICK_BY_RUN
                println(step)
                println(status)
                println(fillColor)
                println(strokeColor)
                if (fillColor.r < 1 && strokeColor.r > 0) {
                    fillColor = ColorRGBa(fillColor.r + step, fillColor.g + step, fillColor.b + step)
                    strokeColor = ColorRGBa(fillColor.r - step, fillColor.g - step, fillColor.b - step)
                } else {
                    fillColor = ColorRGBa(1.0, 1.0, 1.0)
                    strokeColor = ColorRGBa(0.0, 0.0, 0.0)
                    status = Status.ALIVE
                }
            }
            Status.ALIVE -> {
                fillColor = ColorRGBa(1.0, 1.0, 1.0)
                strokeColor = ColorRGBa(0.0, 0.0, 0.0)
            }
        }
        return mapOf("fill" to fillColor, "stroke" to strokeColor, "strokeWeight" to 1.0)
    }
}