package gameoflife

object Domain {

  case class Pos(x: Int, y: Int)

  case class State(rows: Seq[Seq[Boolean]])

  case class Matrix(rows: Seq[Seq[String]])

  sealed trait Direction
  object East  extends Direction
  object West  extends Direction
  object North extends Direction
  object South extends Direction

  val directions = Seq(
    East,
    West,
    North,
    South
  )


}
