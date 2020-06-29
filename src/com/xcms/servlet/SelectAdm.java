package com.xcms.servlet;

import com.xcms.dao.AdmDao;
import com.xcms.dao.AdmDaoImpl;
import com.xcms.pojo.Adm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SelectAdm extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SelectAdm(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int adm_id=Integer.parseInt(request.getParameter("adm_id"));
            AdmDao a=new AdmDaoImpl();
            Adm adm=a.getOneAdm(adm_id);
            if(adm!=null){
                request.setAttribute("adm_id",adm.getAdm_id());
                request.setAttribute("adm_name",adm.getAdm_name());
                request.setAttribute("adm_password",adm.getAdm_password());
                request.getRequestDispatcher("adm/editAdm.jsp").forward(request,response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
