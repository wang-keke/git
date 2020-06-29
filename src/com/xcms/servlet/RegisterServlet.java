package com.xcms.servlet;

import com.xcms.dao.AdmDao;
import com.xcms.dao.AdmDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public RegisterServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result="false";
        PrintWriter writer=null;

        try {
            PrintWriter out=response.getWriter();
            //接收参数
            String username=request.getParameter("username");
            String pwd=request.getParameter("pwd");

            AdmDao a=new AdmDaoImpl();
            boolean flag=a.insertAdm(username,pwd);
            if(flag){
                result="true";
            }
            writer=response.getWriter();
            writer.write(result);
//        out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }


    }
}
