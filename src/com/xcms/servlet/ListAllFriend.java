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

import com.xcms.dao.FriendDao;
import com.xcms.dao.FriendDaoImpl;
import com.xcms.pojo.Friend;
import com.xcms.util.JsonUtil;

/**
 * Servlet implementation class ListFriend
 */
public class ListAllFriend extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        System.out.println("测试是否进入了ListFriend的servlet方法");
		/*int limit = 0;
		int page = 0;		*/
        PrintWriter writer = null;
        try {
			/*limit = Integer.parseInt(request.getParameter("limit"));
			page = Integer.parseInt(request.getParameter("page"));*/

            FriendDao fd = new FriendDaoImpl();
            /*	ArrayList<Friend> fList = fd.getPageFriend(page, limit);*/
            ArrayList<Friend> fListTotal = fd.getAllFriend();


            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", fListTotal.size());
            map.put("data", fListTotal);
            String jsonStr = JsonUtil.beanToString(map);
            System.out.println(jsonStr);
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
