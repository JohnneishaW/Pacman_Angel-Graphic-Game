package graphicgame

trait Entity{
  def x:Double
  def y:Double
  def width:Double
  def height: Double
  def level: Level
  
  def update(delay:Double):Unit
  def postCheck():Unit
  def stillHere():Boolean
  
}