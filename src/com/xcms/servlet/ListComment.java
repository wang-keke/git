package com.xcms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xcms.dao.CommentDao;
import com.xcms.dao.CommentDaoImpl;
import com.xcms.pojo.Comment;
import com.xcms.util.JsonUtil;

/**
 * Servlet implementation class ListComment
 */
public class ListComment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListComment() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        int limit = 0;
        int page = 0;
        PrintWriter writer = null;
        try {
            limit = Integer.parseInt(request.getParameter("limit"));
            page = Integer.parseInt(request.getParameter("page"));
            System.out.println("我是listComment："+limit+"，"+page);
            CommentDao cd = new CommentDaoImpl();
            ArrayList<Comment> cList = cd.getAllComment();
            ArrayList<Comment> cPageList = cd.getPageComment(page, limit);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", cList.size());
            map.put("data", cPageList);
            String jsonStr = JsonUtil.beanToString(map);
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
