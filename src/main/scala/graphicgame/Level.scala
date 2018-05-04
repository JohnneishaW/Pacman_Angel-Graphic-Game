package graphicgame

class Level(val maze: Maze, private var _entities: List[Entity]) {

  def entities = _entities

  def players = _entities.collect {
    case p: Player => p
  }
  def enemy1 = _entities.collect {
    case e: Enemy => e
  }
  def enemy2 = _entities.collect {
    case e: Enemy2 => e
  }
  def bullets = _entities.collect {
    case b: Bullet => b
  }
  def spikes = _entities.collect {
    case s: Spikes => s
  }

  def +=(e: Entity): Unit = synchronized {
    _entities ::= e
  }

  def -=(e: Entity): Unit = synchronized {
    _entities = _entities.filter(_ != e)
  }

  def buildPassable(): PassableLevel = {
    new PassableLevel(maze, entities.map(_.buildPassable))
  }

  def updateAll(delay: Double): Unit = synchronized {
    _entities.foreach(_.update(delay))
    _entities.foreach(_.postCheck())
    _entities = _entities.filter(_.stillHere())
  }
}