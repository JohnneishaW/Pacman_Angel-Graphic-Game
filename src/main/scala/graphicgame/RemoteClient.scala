package graphicgame

@remote trait RemoteClient {
   def updateLevel(lvl:PassableLevel, cx:Double, cy:Double, score:Int):Unit
}