package graphicgame

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import scalafx.Includes._
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyEvent
import Maze._

object Main extends JFXApp {
  val maze = Maze(3,false, 10, 10, 0.5)
  val level = new Level(maze, List[Entity]())
  //val passLevel = new PassableLevel(maze, Seq[PassableEntity]())
  val player = new Player(level)
  new Enemy(level)

  stage = new JFXApp.PrimaryStage {
    title = "Flying Angel" 
    scene = new Scene(1000, 800) {
      val canvas = new Canvas(1000, 800)
      val gc = canvas.graphicsContext2D
      val r = new Renderer2D(gc, 30)
      content = canvas

      //key movements + animation timer

      onKeyPressed = (e: KeyEvent) => {
        e.code match {
          case KeyCode.Up => player.moveUpPressed()
          case KeyCode.Down => player.moveDownPressed()
          case KeyCode.Left => player.moveLeftPressed()
          case KeyCode.Right => player.moveRightPressed()
         // case KeyCode.Space => player.spacePressed()
          case _ =>
        }
      }

      onKeyReleased = (e: KeyEvent) => {
        e.code match {
          case KeyCode.Up => player.moveUpReleased()
          case KeyCode.Down => player.moveDownReleased()
          case KeyCode.Left => player.moveLeftReleased()
          case KeyCode.Right => player.moveRightReleased()
          //case KeyCode.Space => player.spaceReleased()
          case _ =>
        }
      }

      var lastTime = 0L
      val timer = AnimationTimer(time => {
        if (lastTime > 0) {
          val delay = (time - lastTime) / 1e9
          level.updateAll(delay)
        }
        lastTime = time
        val passLevel = level.buildPassable
      //  val score = player.totalScore
       // r.render(passLevel, player.x, player.y,score)
      })
      timer.start()
    }
  }
}
        
   
