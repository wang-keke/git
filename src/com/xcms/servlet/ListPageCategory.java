package com.xcms.servlet;

import com.xcms.dao.CategoryDao;
import com.xcms.dao.CategoryDaoImpl;
import com.xcms.pojo.Category;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListPageCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ListPageCategory(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int limit=0;
        int page=0;
        PrintWriter writer=null;
        try {
            limit= Integer.parseInt(request.getParameter("limit"));
            page=Integer.parseInt(request.getParameter("page"));
            CategoryDao c=new CategoryDaoImpl();
            ArrayList<Category> aList=c.getAllCategory();
            ArrayList<Category> aPageList=c.getPageCategory(page,limit);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", aList.size());
            map.put("data", aPageList);
            //net.sf.json.JSONObject jsonStr=net.sf.json.JSONObject.fromObject(map);
            writer=response.getWriter();
            JSONObject jsonStr = JSONObject.fromObject(map);
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
