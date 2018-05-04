package graphicgame

import java.rmi.server.UnicastRemoteObject

class Player(val level: Level) extends UnicastRemoteObject with Entity with RemotePlayer {
  level += this
  private var _x = 5.0
  private var _y = 5.0
  private var alive = true
  val speed = 3
  var totalScore = 0
  private var coolDownTime = 0.0

  def x = _x
  def y = _y
  def width = 1.3
  def height = 1.5

  def buildPassable(): PassableEntity = {
    new PassableEntity(Entity.EntityType.Player, x, y, width, height)
  }

  def updateScore(score: Int): Int = {
    println("score updating")
    totalScore += score
    totalScore
  }

  def update(delay: Double): Unit = {
    //move
    if (movingUp) move(0, -delay * speed)
    if (movingDown) move(0, delay * speed)
    if (movingLeft) move(-delay * speed, 0)
    if (movingRight) move(delay * speed, 0)

    //shoot
    coolDownTime -= delay

    if (shootRight && coolDownTime < 0) {
      coolDownTime = 0.5
      new Bullet(level, x, y, 1, 0, this)
      println("shooting right")
    }

    if (shootLeft && coolDownTime < 0) {
      coolDownTime = 0.5
      new Bullet(level, x, y, -1, 0, this)
    }
    if (shootDown && coolDownTime < 0) {
      coolDownTime = 0.5
      new Bullet(level, x, y, 0, 1, this)
      println("shooting up")
    }
    if (shootUp && coolDownTime < 0) {
      coolDownTime = 0.5
      new Bullet(level, x, y, 0, -1, this)
      println("shooting down")
    }

  }

  def move(dx: Double, dy: Double) = {
    if (level.maze.isClear(_x + dx, _y + dy, width, height)) {
      _y += dy
      _x += dx
    }

  }

  //BUTTONS
  private var movingUp = false
  private var movingLeft = false
  private var movingRight = false
  private var movingDown = false
  private var shootUp = false
  private var shootDown = false
  private var shootRight = false
  private var shootLeft = false
  //private var space = false

  def postCheck(): Unit = {
    val spikesHitMe = level.spikes.filter(s => Entity.intersects(s, this))
    alive = spikesHitMe.isEmpty
    
    val bulletsHitMe = level.bullets.filter(b => b.player != this && Entity.intersects(b, this))
    alive = alive && bulletsHitMe.isEmpty

    val eIntersect = level.enemy1.filter(e => Entity.intersects(e, this))
    alive = alive && eIntersect.isEmpty
    if (alive == false) level.-=(this)

  }

  def stillHere(): Boolean = {
    true
  }

  //BUTTON PRESSED   
  def moveUpPressed(): Unit = movingUp = true
  def moveDownPressed(): Unit = movingDown = true
  def moveRightPressed(): Unit = movingRight = true
  def moveLeftPressed(): Unit = movingLeft = true
  def wPressed(): Unit = shootUp = true
  def sPressed(): Unit = shootDown = true
  def aPressed(): Unit = shootLeft = true
  def dPressed(): Unit = shootRight = true
  //def spacePressed(): Unit = space = true

  //MOVE RELEASED    
  def moveUpReleased(): Unit = movingUp = false
  def moveDownReleased(): Unit = movingDown = false
  def moveRightReleased(): Unit = movingRight = false
  def moveLeftReleased(): Unit = movingLeft = false
  // def spaceReleased(): Unit = space = false
  def wReleased(): Unit = shootUp = false
  def sReleased(): Unit = shootDown = false
  def aReleased(): Unit = shootLeft = false
  def dReleased(): Unit = shootRight = false
}