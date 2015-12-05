package gameoflife

import Domain._
import Constants._

import scala.util.Random

object Logic {

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
    State(
      state.rows.zipWithIndex.map { case (row, i) =>
        row.zipWithIndex.map { case (isAlive, j)  =>
          val p = Pos(i, j)
          val neighboursAsBooleans = p.neighbours.map { case Pos(x, y) =>
            Pos(x, y) -> state.rows(x)(y)
          }.toMap
          val res = (isAlive, neighboursAsBooleans.values.count(b => b)) match {
            case (true, 2) => true
            case (true, 3) => true
            case (true, _) => false
            case (false, 3) => true
            case (false, _) => false
          }
          res
        }
      }
    )
  }
}
