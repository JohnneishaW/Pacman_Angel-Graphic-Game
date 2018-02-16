package graphicgame

class Enemy(val level: Level) extends Entity {
  level += this
  private var _x = 2.0
  private var _y = 2.0
  val speed = 10

  def x = _x
  def y = _y
  def width = 1
  def height = 1.0

  def update(delay: Double): Unit = {
    move(speed * delay* (math.random() - 0.5), speed * delay*(math.random() - 0.5))
  }

  def move(dx: Double, dy: Double) = {
    if (level.maze.isClear(_x + dx, _y + dy, width, height)) {
      _y += dy
      _x += dx
    }
  }

  def postCheck(): Unit = {
    ???
  }
  def stillHere(): Boolean = {
    ???
  }

}