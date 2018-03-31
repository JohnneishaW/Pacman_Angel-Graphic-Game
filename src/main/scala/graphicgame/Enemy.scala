package graphicgame

import collection.mutable

class Enemy(val level: Level) extends Entity {
//class Enemy[A](val level: Level[A]) extends Entity {
  //level.+=(this)
  level += this
  private var _x = 2.0
  private var _y = 2.0
  val speed = 10
  private var alive = true

  def x = _x
  def y = _y
  def width = 1
  def height = 1.0

  def buildPassable(): PassableEntity = {
    new PassableEntity(Entity.EntityType.Enemy, x, y, width, height)
  }

  def update(delay: Double): Unit = {
    val players = level.players
    if (players.nonEmpty) {
      val p = players.head
      val ex = p.x.toInt
      val ey = p.y.toInt
      val left = bfs((x - 1).toInt, y.toInt, ex, ey)
      val right = bfs((x + 1).toInt, y.toInt, ex, ey)
      val up = bfs(x.toInt, (y - 1).toInt, ex, ey)
      val down = bfs(x.toInt, (y + 1).toInt, ex, ey)

      if (left <= right && left <= up && left <= down) move(-delay * speed, 0)
      if (right <= left && right <= up && right <= down) move(delay * speed, 0)
      if (up <= right && up <= left && up <= down) move(0, -delay * speed)
      if (down <= right && down <= up && down <= left) move(0, delay * speed)
    }
  }

  def move(dx: Double, dy: Double) = {
    if (level.maze.isClear(_x + dx, _y + dy, width, height)) {
      _y += dy
      _x += dx
    }
  }

  def postCheck(): Unit = {
    alive = level.bullets.forall(b => !Entity.intersects(b, this))
  }
  def stillHere(): Boolean = {
    alive
  }

  val offsets = List((1, 0), (-1, 0), (0, 1), (0, -1))

  def bfs(sx: Int, sy: Int, ex: Int, ey: Int): Int = {
    if (!level.maze.isClear(sx, sy, width, height)) 10000000
    else {
      val queue = new mutable.Queue[(Int, Int, Int)]
      var visited = Set[(Int, Int)]()
      queue.enqueue((sx, sy, 0))
      visited += (sx -> sy)
      while (!queue.isEmpty) {
        val (x, y, steps) = queue.dequeue()
        for ((dx, dy) <- offsets) {
          val (nx, ny) = (x + dx, y + dy)
          if (nx == ex && ny == ey) return steps + 1
          if (nx >= 0 && nx < level.maze.width && ny >= 0 && ny < level.maze.height
            && level.maze.isClear(nx, ny, width, height) && !visited(nx -> ny)) {
            queue.enqueue((nx, ny, steps + 1))
            visited += (nx -> ny)
          }
        }
      }
      10000000
    }
  }

}