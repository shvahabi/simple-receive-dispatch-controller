import com.bsp.receivedispatch.controller._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new ReceiveDispatchServlet, "/*")
  }
}
