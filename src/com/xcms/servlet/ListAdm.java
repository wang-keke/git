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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListAdm extends HttpServlet {
    private static final long serialVersionUID=1L;
    public ListAdm(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        int limit=0;
        int page=0;
        PrintWriter writer=null;
        try {
            limit=Integer.parseInt(request.getParameter("limit"));
            page=Integer.parseInt(request.getParameter("page"));
            AdmDao a=new AdmDaoImpl();
            ArrayList<Adm> aList=a.getPageAdm(page,limit);
            ArrayList<Adm> aListTotal=a.getAllAdm();
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("code",0);
            map.put("msg","");
            map.put("count",aListTotal.size());
            map.put("data",aList);
            writer=response.getWriter();
            net.sf.json.JSONObject jsonStr=net.sf.json.JSONObject.fromObject(map);
            writer.print(jsonStr);
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
