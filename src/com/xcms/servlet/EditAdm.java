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

public class EditAdm extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public EditAdm(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result="false";
        PrintWriter writer=null;
        try {
            int adm_id=Integer.parseInt(request.getParameter("adm_id"));
            String adm_name=request.getParameter("adm_name");
            String adm_password=request.getParameter("adm_password");
            Adm adm=new Adm();
            adm.setAdm_id(adm_id);
            adm.setAdm_name(adm_name);
            adm.setAdm_passward(adm_password);
            AdmDao a=new AdmDaoImpl();
            boolean flag=a.updateAdm(adm);
            System.out.println(flag);
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
