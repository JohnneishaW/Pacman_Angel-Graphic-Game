package graphicgame

class Level(val maze: Maze, private var _entities: List[Entity]){
  def entities = _entities

  def players = _entities.collect {
    case p: Player => p
  }
  def enemies = _entities.collect {
    case e: Enemy => e
  }
  def bullets = _entities.collect {
    case b: Bullet => b
  }
 
  
  def +=(e: Entity): Unit = {
    _entities ::= e
  }
  
  def -=(e: Entity): Unit = {
    _entities = _entities.filter(_ != e)

  }

  def buildPassable():PassableLevel = {
    new PassableLevel(maze, entities.map(_.buildPassable))  
  }
  
  def updateAll(delay: Double): Unit = {
    _entities.foreach(_.update(delay))
    _entities.foreach(_.postCheck())
    _entities = _entities.filter(_.stillHere())
  }

}