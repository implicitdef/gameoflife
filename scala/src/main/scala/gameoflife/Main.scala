package gameoflife


import scala.concurrent.duration._
import scala.scalajs._
import scala.scalajs.js.annotation._
import scala.scalajs.js.timers._
import scala.util.control.NonFatal
import scala.util.{Try, Random}

object Domain {

  val size = 20
  import emoji.ShortCodes.Defaults._
  import emoji.ShortCodes.Implicits._

  val herb = "herb".emoji
  val octopus = "octopus".emoji

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


object Logic {

  import Domain._

  def stateToMatrix(state: State): Matrix =
    Matrix(
      state.rows.map {
        _.map { bool =>
          (if (bool) octopus else herb).toString
        }
      }
    )

  private implicit class RichSeq[A](seq: Seq[A]) {
    def rand = seq(Random.nextInt(seq.length))
  }

  private implicit class RichPos(p: Pos){
    def neighbours: Seq[Pos] =
      (-1 to 1).flatMap { i =>
        (-1 to 1).flatMap { j =>
          if (i != 0 || j != 0)
            Some(p.copy(p.x + i, p.y + j))
          else
            None
        }
      }.filter(_.isValid)
    def isValid = {
      val range = 0 until size
      range.contains(p.x) && range.contains(p.y)
    }

  }


  def nextState(state: State) = {
    state.rows.zipWithIndex.map { case (row, i) =>
      row.zipWithIndex.map { case (isAlive, j) =>
        val p = Pos(i, j)
        println(s"$p => $isAlive => ${p.neighbours.size} neighbouring cells => ${p.neighbours.filter{ n => state.rows(n.x)(n.y)}} are alive")
      }
    }
    State(
      state.rows.zipWithIndex.map { case (row, i) =>
        row.zipWithIndex.map { case (isAlive, j)  =>
          val p = Pos(i, j)
          println("---------")
          println(p)
          println(p.neighbours)
          val neighboursAsBooleans = p.neighbours.map { case Pos(x, y) =>
            Pos(x, y) -> state.rows(x)(y)
          }.toMap
          println(neighboursAsBooleans)
          val res = (isAlive, neighboursAsBooleans.values.count(b => b)) match {
            case (true, 2) => true
            case (true, 3) => true
            case (true, _) => false
            case (false, 3) => true
            case (false, _) => false
          }
          println(s"$isAlive => $res")
          res
        }
      }
    )
  }

}





@JSExport("ScalaMain")
class Main {

  import Domain._
  import Logic._



  val initialPattern =
    Seq(
      Seq(false, false, true),
      Seq(true, false, true),
      Seq(false, true, true)
    )

  var state = State(
    (0 until size).map { i =>
      (0 until size).map { j =>
        Try {
          initialPattern(i)(j)
        }.getOrElse(false)
      }
    }
  )

  @JSExport
  def getState() = upickle.json.writeJs(upickle.default.writeJs(stateToMatrix(state)))

  type Callback = js.Function1[Any, _]

  var maybeCallback: Option[Callback] = None



  @JSExport
  def doNext() = {
    state = nextState(state)
    maybeCallback.foreach { cb =>
      cb(getState())
    }
  }

  setInterval(0.1.second){
    doNext
  }


  @JSExport
  def subscribe(callback: Callback): Unit =
    maybeCallback = Some(callback)



}
