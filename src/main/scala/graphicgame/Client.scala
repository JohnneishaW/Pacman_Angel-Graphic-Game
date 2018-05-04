package graphicgame

import java.rmi.server.UnicastRemoteObject
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyEvent

object Client extends UnicastRemoteObject with RemoteClient with JFXApp {
  val server = java.rmi.Naming.lookup("rmi://localhost/PirateGame") match {
    case rs: RemoteServer => rs
  }

  val player = server.connect(this)
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val r = new Renderer2D(gc, 30)

  stage = new JFXApp.PrimaryStage {
    title = "Pirates"
    scene = new Scene(1000, 800) {
      content = canvas

      //key movements + animation timer

      onKeyPressed = (e: KeyEvent) => {
        e.code match {
          case KeyCode.Up => player.moveUpPressed()
          case KeyCode.Down => player.moveDownPressed()
          case KeyCode.Left => player.moveLeftPressed()
          case KeyCode.Right => player.moveRightPressed()
          // case KeyCode.Space => player.spacePressed()
          case KeyCode.W => player.wPressed()
          case KeyCode.A => player.aPressed()
          case KeyCode.S => player.sPressed()
          case KeyCode.D => player.dPressed()
          case _ =>
        }
      }

      onKeyReleased = (e: KeyEvent) => {
        e.code match {
          case KeyCode.Up => player.moveUpReleased()
          case KeyCode.Down => player.moveDownReleased()
          case KeyCode.Left => player.moveLeftReleased()
          case KeyCode.Right => player.moveRightReleased()
          // case KeyCode.Space => player.spaceReleased()
          case KeyCode.W => player.wReleased()
          case KeyCode.S => player.sReleased()
          case KeyCode.A => player.aReleased()
          case KeyCode.D => player.dReleased()
          case _ =>
        }
      }
    }
  }

  def updateLevel(lvl: PassableLevel, cx: Double, cy: Double, score: Int): Unit = {
    Platform.runLater {
      r.render(lvl, cx, cy, score)
    }
  }
}