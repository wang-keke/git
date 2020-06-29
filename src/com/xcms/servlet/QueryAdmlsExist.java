package com.xcms.servlet;

import com.xcms.dao.AdmDao;
import com.xcms.dao.AdmDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QueryAdmlsExist extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public QueryAdmlsExist(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //根据username查询用户是否存在
        String result="false";
        PrintWriter writer=null;
        try {
            String username=request.getParameter("username");
            AdmDao a=new AdmDaoImpl();
            boolean flag=a.queryIsExist(username);
            if(flag==true){
                result="true";
            }
            writer=response.getWriter();
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }
}
