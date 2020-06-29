import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends javax.servlet.http.HttpServlet {
    public HelloServlet(){
        System.out.println("Hello Servlet create");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();//向网页输出打印
        out.println("Hello Servlet");
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();//向网页输出打印
        out.println("Hello Servlet");
        out.close();
        System.out.println("do get");
        System.out.println(this.getServletConfig().getInitParameter("name"));
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
