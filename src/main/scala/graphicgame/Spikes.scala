package graphicgame

class Spikes(val level: Level, private var _x:Double, private var _y:Double, dx:Double, dy:Double) extends Entity{
  level += this
  val speed = 4
  private var alive = true
  
  def x = _x
  def y = _y
  def width = 0.5
  def height = 0.5

  def buildPassable():PassableEntity = {
     new PassableEntity(Entity.EntityType.Spike, x, y, width, height)
  }   
  def update(delay: Double): Unit = {
    move(speed*dx*delay, speed*delay*dy)
  }

  def move(dx: Double, dy: Double) = {
    if (level.maze.isClear(_x + dx, _y + dy, width, height)) {
      _y += dy
      _x += dx
    }
    else alive=false
  }

  def postCheck(): Unit = {
   
  }
  def stillHere(): Boolean = {
    alive
  }
}
