package graphicgame

class Bullet(val level: Level, private var _x:Double, private var _y:Double, dx:Double, dy:Double) extends Entity {
  level += this
  val speed = 10
  private var alive = true
  
  def x = _x
  def y = _y
  def width = 1
  def height = 1.0

  def buildPassable():PassableEntity = {
     new PassableEntity(Entity.EntityType.Bullet, x, y, width, height)
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