package graphicgame

import java.rmi.server.UnicastRemoteObject
import java.io.PrintStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import scala.concurrent.Future
import java.io.BufferedInputStream
import java.io.ObjectInputStream

object Server extends UnicastRemoteObject with RemoteServer with App {
  java.rmi.registry.LocateRegistry.createRegistry(1099)
  java.rmi.Naming.rebind("Fighting the Devil", this)

  val maze = Maze(3, false, 10, 10, 0.5)
  val level = new Level(maze, List[Entity]())

  val e = new Enemy(level)
  level += e

  private var pLst = List[(Player, RemoteClient)]()
  def connect(client: RemoteClient): RemotePlayer = {
    println("connecting")
    val player = new Player(level)
    pLst ::= (player, client)
    player
  }

  var lastTime = 0L
  val updateInterval = 0.1
  var delaySum = 0.0
  while (true) {
    val time = System.nanoTime()
    if (lastTime > 0) {
      val delay = (time - lastTime) / 1e9
      level.updateAll(delay)
      delaySum += delay
      if (delaySum >= updateInterval) {
        if(math.random() < delaySum/7) new Enemy(level)
        val passLevel = level.buildPassable
        delaySum = 0
        for ((p, c) <- pLst) {
          c.updateLevel(passLevel, p.x, p.y, p.totalScore)
        }
      }
    }
    lastTime = time
  }
}


