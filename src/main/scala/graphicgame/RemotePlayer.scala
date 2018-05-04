package graphicgame

@remote trait RemotePlayer {
  //button pressed
  def moveUpPressed(): Unit 
  def moveDownPressed(): Unit 
  def moveRightPressed(): Unit
  def moveLeftPressed(): Unit 
 // def spacePressed(): Unit
  def wPressed():Unit
  def sPressed():Unit
  def aPressed():Unit
  def dPressed():Unit
  
  //button released
  def moveUpReleased(): Unit 
  def moveDownReleased(): Unit 
  def moveRightReleased(): Unit
  def moveLeftReleased(): Unit 
 // def spaceReleased(): Unit
  def wReleased():Unit
  def sReleased():Unit
  def aReleased():Unit
  def dReleased():Unit
}