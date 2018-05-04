package graphicgame

import collection.mutable

//TODO: LL && SLOW DOWN SPIKES

class Enemy2(val level: Level) extends Entity {
  level += this
  val worth = 10
  private var _x: Double = 2.0 + 3 * util.Random.nextInt(10)
  private var _y: Double = 2.0 + 3 * util.Random.nextInt(10)
  val speed = util.Random.nextInt(6)
  private var alive = true
  private var coolDownTime = 0.0

  val delay = 30

  def x = _x
  def y = _y
  def width = 1
  def height = 1.2

  def buildPassable(): PassableEntity = {
    new PassableEntity(Entity.EntityType.Enemy2, x, y, width, height)
  }

  def update(delay: Double): Unit = {
    val players = level.players
    if (players.nonEmpty) {
      val p = players.head
      val ex = p.x.toInt
      val ey = p.y.toInt
      val left = bfs((x - 1), y, ex, ey)
      val right = bfs((x + 1), y, ex, ey)
      val up = bfs(x, (y - 1), ex, ey)
      val down = bfs(x, (y + 1), ex, ey)

      if (left <= right && left <= up && left <= down) move(-delay * speed, 0)
      else if (right <= left && right <= up && right <= down) move(delay * speed, 0)
      else if (up <= right && up <= left && up <= down) move(0, -delay * speed)
      else if (down <= right && down <= up && down <= left) move(0, delay * speed)

      coolDownTime -= delay

      if (math.random() < delay) new Spikes(level, x, y, 1, 0)
    }
  }

  def move(dx: Double, dy: Double) = {
    if (level.maze.isClear(_x + dx, _y + dy, width, height)) {
      _y += dy
      _x += dx
    }
  }

  def postCheck(): Unit = {
    if (alive) {
      val bulletsHitMe = level.bullets.filter(b => Entity.intersects(b, this))
      alive = bulletsHitMe.isEmpty
      bulletsHitMe.foreach(b => b.player.updateScore(worth))
      if (alive == false) new Enemy2(level)
    }
  }
  def stillHere(): Boolean = {
    alive
  }

  val offsets = List((1, 0), (-1, 0), (0, 1), (0, -1))

  def bfs(sx: Double, sy: Double, ex: Double, ey: Double): Int = {
    if (!level.maze.isClear(sx, sy, width, height)) 10000000
    else {
      val queue = new mutable.Queue[(Double, Double, Int)]
      var visited = Set[(Double, Double)]()
      queue.enqueue((sx, sy, 0))
      visited += (sx -> sy)
      while (!queue.isEmpty) {
        val (x, y, steps) = queue.dequeue()
        for ((dx, dy) <- offsets) {
          val (nx, ny) = (x + dx, y + dy)
          if (nx.toInt == ex.toInt && ny.toInt == ey.toInt) return steps + 1
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