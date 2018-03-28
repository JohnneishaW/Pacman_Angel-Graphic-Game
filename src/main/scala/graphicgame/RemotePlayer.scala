package graphicgame

@remote trait RemotePlayer {
  def moveUpPressed(): Unit 
  def moveDownPressed(): Unit 
  def moveRightPressed(): Unit
  def moveLeftPressed(): Unit 
  def spacePressed(): Unit
  
  def moveUpReleased(): Unit 
  def moveDownReleased(): Unit 
  def moveRightReleased(): Unit
  def moveLeftReleased(): Unit 
  def spaceReleased(): Unit
}