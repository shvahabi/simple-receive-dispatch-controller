import com.bsp.receivedispatch.controller._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new ReceiveDispatchServlet, "/*")
context.setInitParameter("org.scalatra.cors.allowedOrigins", "*")
context.setInitParameter("org.scalatra.cors.allowCredentials", "false")
context.setInitParameter("org.scalatra.cors.allowedMethods", "GET,POST,PUT,DELETE,OPTIONS,HEAD,PATCH")
context.setInitParameter("org.scalatra.cors.enable", "true")
  }
}
