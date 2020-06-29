package com.xcms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xcms.dao.TagDao;
import com.xcms.dao.TagDaoImpl;
import com.xcms.pojo.Tag;
import com.xcms.util.JsonUtil;

/**
 * Servlet implementation class GetFirstTag
 */

public class GetFirstTag extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFirstTag() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("测试是否进入了getfirsttag方法");
        PrintWriter writer = null;
        try {
            TagDao td = new TagDaoImpl();
            ArrayList<Tag> tTotal = td.getAllTag();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", tTotal.size());
            map.put("data", tTotal);
            String jsonStr = JsonUtil.beanToString(map);
            //System.out.println(jsonStr);
            writer= response.getWriter();
            writer.write(jsonStr);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
