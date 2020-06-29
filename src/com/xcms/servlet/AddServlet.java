package com.xcms.servlet;

import com.xcms.dao.CategoryDao;
import com.xcms.dao.CategoryDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public AddServlet(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result="false";
        PrintWriter writer=null;
        try {
            int parent_id=Integer.parseInt(request.getParameter("parent_id"));
            String category_name=request.getParameter("category_name");
            CategoryDao c=new CategoryDaoImpl();
            boolean flag=c.insertCategory(parent_id,category_name);
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
