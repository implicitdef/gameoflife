package gameoflife


import gameoflife.Domain.GlobalState

import scala.concurrent.duration._
import scala.scalajs._
import scala.scalajs.js.annotation._
import scala.scalajs.js.timers._
import scala.util.Random


object Domain {
  case class GlobalState(rows: Seq[Seq[String]])
}





@JSExport("ScalaMain")
class Main {



  val messages = Seq(
    "Hohoh",
    "Damn",
    "Yihaah",
    "Kamehameha"
  )

  var state = GlobalState(
    (1 to 50).map { _ =>
      (1 to 100).map { _ =>
        Random.nextInt(2).toString
      }
    }
  )

  @JSExport
  def getState() = upickle.json.writeJs(upickle.default.writeJs(state))

  type Callback = js.Function1[Any, _]

  var maybeCallback: Option[Callback] = None

  setInterval(0.1.second){
    state = state.copy(
      rows = state.rows.map {
        _.map { _ =>
          Random.nextInt(2).toString
        }
      }
    )
    maybeCallback.foreach { cb =>
      cb(getState())
    }
  }


  @JSExport
  def subscribe(callback: Callback): Unit =
    maybeCallback = Some(callback)



}
