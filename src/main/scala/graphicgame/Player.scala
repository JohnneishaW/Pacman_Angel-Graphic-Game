package graphicgame

//extend maze?

class Player(val level:Level) extends Entity {
  level+=this
  private var _x =5.0
  private var _y = 5.0
  val speed = 3
   
   def x = _x
   def y = _y
   def width = 1
   def height = 1.0
   
 
   
   def update(delay:Double): Unit = {
    if(movingUp) move(0,-delay*speed)
    if(movingDown) move(0, delay*speed)
    if(movingLeft) move(-delay*speed,0)
    if(movingRight) move(delay*speed,0)
    
  }
  
  def move(dx:Double, dy:Double) = {
    if(level.maze.isClear(_x+dx, _y+dy, width, height)){
    	_y+=dy
      _x+=dx
    }
    
  }
  
  
  //BUTTONS
   private var movingUp = false
   private var movingLeft = false
   private var movingRight = false
   private var movingDown = false
   private var space = false
   
   def postCheck():Unit = {
     ???
   }
   def stillHere():Boolean = {
     ???
   }
   
    //BUTTON PRESSED   
    def moveUpPressed():Unit = movingUp = true
    def moveDownPressed():Unit = movingDown = true
    def moveRightPressed():Unit = movingRight = true
    def moveLeftPressed():Unit = movingLeft = true
    def spacePressed():Unit = space = true
    
    //MOVE RELEASED    
     def moveUpReleased():Unit = movingUp = false
     def moveDownReleased():Unit = movingDown = false
   	 def moveRightReleased():Unit = movingRight = false
     def moveLeftReleased():Unit = movingLeft = false
     def spaceReleased():Unit = space = false
 
}