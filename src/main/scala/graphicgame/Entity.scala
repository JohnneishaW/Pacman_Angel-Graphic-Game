package graphicgame

trait Entity{
  val x:Double
  val y:Double
  val width:Double
  val height: Double
  val level: Level
  
  def update(delay:Double):Unit
  def postCheck():Unit
  def stillHere():Boolean
  
}