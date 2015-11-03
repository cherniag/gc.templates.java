package com.gc.template.tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Author: Gennadii Cherniaiev
 * Date: 11/3/2015
 */
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<html>\n" +
                        "<body>\n" +
                        "<h2>StartServlet </h2>\n" + new Date() +
                        "</body>\n" +
                        "</html>"
        );

    }
}
