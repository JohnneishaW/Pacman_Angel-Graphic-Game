package graphicgame

@remote trait RemotePlayer {
  def moveUpPressed(): Unit 
  def moveDownPressed(): Unit 
  def moveRightPressed(): Unit
  def moveLeftPressed(): Unit 
  def spacePressed(): Unit
}