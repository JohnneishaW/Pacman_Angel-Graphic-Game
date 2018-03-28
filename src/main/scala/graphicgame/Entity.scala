package graphicgame

trait Entity {
  def x: Double
  def y: Double
  def width: Double
  def height: Double
  def level: Level

  def update(delay: Double): Unit
  def postCheck(): Unit
  def stillHere(): Boolean

  def buildPassable():PassableEntity 
}

object Entity {
  def intersects(e1: Entity, e2: Entity): Boolean = {
    val intersectX = (e1.x - e2.x).abs < (e1.width + e2.width) / 2
    val intersectY = (e1.y - e2.y).abs < (e1.height + e2.height) / 2
    intersectX && intersectY
  }

  object EntityType extends Enumeration {
    val Player, Enemy, Bullet, Generator = Value
   }
}

