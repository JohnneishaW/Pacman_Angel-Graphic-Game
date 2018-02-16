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
  def intersects(e:Entity):Boolean = {
    val intersectX = (x-e.x).abs < (width+e.width)/2
    val intersectY = (y-e.y).abs < (height+e.height)/2
    intersectX && intersectY
  }
}