package com.xcms.servlet;

import com.xcms.dao.AdmDao;
import com.xcms.dao.AdmDaoImpl;
import com.xcms.pojo.Adm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginServlet(){
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String pwd = request.getParameter("pwd");

        AdmDao a = new AdmDaoImpl();
        Adm adm = a.getAdm(name,pwd);
        System.out.println(a.toString());
        if(adm!=null) {
            request.getSession().setAttribute("admin", adm);
            //重定向到成功页面
            response.sendRedirect("index.jsp");
        }else {
            request.setAttribute("errMsg","帐号或密码错误，请重新输入！");
            //转发到login页面
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
