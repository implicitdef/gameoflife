package gameoflife



import scala.concurrent.duration._
import scala.scalajs._
import scala.scalajs.js.annotation._
import scala.scalajs.js.timers._
import scala.util.control.NonFatal
import scala.util.{Try, Random}


import Logic._
import Constants._
import Main._


object Main {

  type Callback = js.Function1[Any, _]
}

@JSExport("ScalaMain")
class Main {


  var state = initialState
  var maybeCallback: Option[Callback] = None

  @JSExport
  def getState() = upickle.json.writeJs(upickle.default.writeJs(stateToMatrix(state)))

  @JSExport
  def doNext() = {
    state = nextState(state)
    maybeCallback.foreach { cb =>
      cb(getState())
    }
  }

  @JSExport
  def subscribe(callback: Callback): Unit =
    maybeCallback = Some(callback)


  setInterval(0.3.second){
    doNext()
  }





}
