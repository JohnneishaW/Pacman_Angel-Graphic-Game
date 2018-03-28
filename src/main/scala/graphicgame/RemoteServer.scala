package graphicgame

@remote trait RemoteServer {
  def connect(client:RemoteClient):RemotePlayer
}