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

public class ListCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ListCategory(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer=null;
        try {
            CategoryDao c=new CategoryDaoImpl();
            ArrayList<Category> aList=c.getAllCategory();
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", aList.size());
            map.put("data", aList);
//        String jsonStr = JsonUtil.beanToString(map);
//        writer= response.getWriter();
//        writer.write(jsonStr);
            writer=response.getWriter();
            JSONObject jsonStr = JSONObject.fromObject(map);
            writer.print(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }
}
