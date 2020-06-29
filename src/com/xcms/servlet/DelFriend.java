package com.xcms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xcms.dao.FriendDao;
import com.xcms.dao.FriendDaoImpl;

/**
 * Servlet implementation class DelFriend
 */
public class DelFriend extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "false";
        PrintWriter writer = null;
        try {
            System.out.println("哎呀。你进了delFriend没有呢！");
            int id = Integer.parseInt(request.getParameter("friend_id"));
            FriendDao fd = new FriendDaoImpl();

            boolean flag = fd.delFriend(id);
            if(flag) {
                result="true";
            }
            writer = response.getWriter();
            writer.write(result);
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
