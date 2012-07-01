package fr.xebia.extremestartup;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebServer extends AbstractHandler {

    public static final String QUERY_PARAMETER = "q";

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch)
            throws IOException, ServletException {
        String resp = request.getParameter(QUERY_PARAMETER);
        System.out.println(resp);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.println(resp);
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new WebServer());
        server.start();
        server.join();
    }

}