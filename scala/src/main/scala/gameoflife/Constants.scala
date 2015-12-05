package gameoflife

import gameoflife.Domain.State

import scala.util.Try

object Constants {

  val size = 20

  import emoji.ShortCodes.Defaults._
  import emoji.ShortCodes.Implicits._
  val herb = "herb".emoji
  val octopus = "octopus".emoji


  private val initialPattern =
    Seq(
      Seq(false, false, true),
      Seq(true, false, true),
      Seq(false, true, true)
    )

  val initialState = State(
    (0 until size).map { i =>
      (0 until size).map { j =>
        Try {
          initialPattern(i)(j)
        }.getOrElse(false)
      }
    }
  )
}
