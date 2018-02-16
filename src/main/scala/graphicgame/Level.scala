package graphicgame

class Level(val maze:Maze, private var _entities:List[Entity]) {
  def entities = _entities 
  
  def += (e:Entity):Unit = {
    _entities::=e
  }
  
  def -= (e:Entity):Unit = {
    _entities=_entities.filter(_!=e)
    
  }
  
  def updateAll(delay:Double):Unit = {
    _entities.foreach(_.update(delay))
  }
  
}