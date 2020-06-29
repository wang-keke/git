package com.xcms.servlet;

import com.xcms.dao.AdmDao;
import com.xcms.dao.AdmDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class delAdm extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public delAdm(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result="false";
        PrintWriter writer=null;
        try {
            int id=Integer.parseInt(request.getParameter("adm_id"));
            AdmDao a=new AdmDaoImpl();
            boolean flag=a.deleteAdm(id);
            if(flag){
                result="true";
            }
            writer=response.getWriter();
            writer.write(result);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }

    }
}
