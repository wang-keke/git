package com.xcms.servlet;

import com.xcms.dao.CategoryDao;
import com.xcms.dao.CategoryDaoImpl;
import com.xcms.pojo.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SelectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SelectServlet(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int category_id= Integer.parseInt(request.getParameter("category_id"));
            CategoryDao c=new CategoryDaoImpl();
            Category c1=c.getOneCategory(category_id);
            if(c1!=null){
                request.setAttribute("category_id",c1.getCategory_id());
                request.setAttribute("category_name",c1.getCategory_name());
                request.setAttribute("parent_id", c1.getParent_id());
                request.getRequestDispatcher("category/editCategory.jsp").forward(request, response);//转发到成功页面
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
