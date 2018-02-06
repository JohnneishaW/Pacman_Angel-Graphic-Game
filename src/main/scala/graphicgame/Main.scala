package graphicgame

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import scalafx.Includes._
import scalafx.scene.canvas.Canvas


object Main extends JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Pirates" // Change this to match the theme of your game.
		scene = new Scene(1000,800){
		  val canvas = new Canvas(1000,800)
      val gc = canvas.graphicsContext2D
      val Player = new Player
      //grid?
      content = canvas
      
      /*      
      //TODO: key movements + animation timer
      
      onKeyPressed = (e: KeyEvent) => {
        e.code match{
          case KeyCode.Up => Player.moveUpPressed()
          case KeyCode.Down => Player.moveDownPressed()
          case KeyCode.Left => Player.moveLeftPressed()
          case KeyCode.Right => Player.moveRightPressed()
          case KeyCode.Space => Player.spacePressed()
          case _ => 
        }
      }
        
      onKeyReleased = (e: KeyEvent) => {
        e.code match{
          case KeyCode.Up => Player.moveUpReleased()
          case KeyCode.Down => Player.moveDownReleased()
          case KeyCode.Left => Player.moveLeftReleased()
          case KeyCode.Right => Player.moveRightReleased()
          case KeyCode.Space => Player.spaceReleased()
          case _ => 
        }
      }
      
      */
        
      //TODO: animation timer
        
    }
  }
  
  
}