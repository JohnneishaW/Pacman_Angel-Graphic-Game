package graphicgame

//extend maze?

class Player extends Entity {
  private var _x =5.0
  private var _y = 6.0
  //val level = 
   
   val x = _x
   val y = _y
   val width = 12.0
   val height = 6.0
   
   
   private var delaySum = 0.0
   val interval = 0.0
   
   def update(delay:Double): Unit = {
     delaySum +=delay
     if(delaySum >= interval){
       x+1
       y+1
       delaySum = 0.0
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