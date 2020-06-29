package com.xcms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xcms.dao.ArticleDao;
import com.xcms.dao.ArticleDaoImpl;
import com.xcms.dao.Article_Tag_Dao;
import com.xcms.dao.Article_Tag_DaoImpl;
import com.xcms.dao.CategoryDao;
import com.xcms.dao.CategoryDaoImpl;
import com.xcms.pojo.Article;
import com.xcms.pojo.Article_Tag;
import com.xcms.pojo.Category;

/**
 * Servlet implementation class SelectArticle
 */
public class SelectArticle extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int article_id = Integer.parseInt(request.getParameter("article_id"));
            System.out.println("id值为:" + article_id);
            ArticleDao ad = new ArticleDaoImpl();
            Article a = ad.getOneArticle(article_id);
            Article_Tag_Dao atd = new Article_Tag_DaoImpl();
            ArrayList<Article_Tag> at = atd.searchArticle_Tag(article_id);

            if (a != null) {
                if (at.size() == 1) {
                    request.setAttribute("tag1", at.get(0).getTag_id());
                    request.setAttribute("tag2", 0);
                    request.setAttribute("tag3", 0);
                } else if (at.size() == 2) {
                    request.setAttribute("tag1", at.get(0).getTag_id());
                    request.setAttribute("tag2", at.get(1).getTag_id());
                    request.setAttribute("tag3", 0);
                } else if (at.size() == 3) {
                    request.setAttribute("tag1", at.get(0).getTag_id());
                    request.setAttribute("tag2", at.get(1).getTag_id());
                    request.setAttribute("tag3", at.get(2).getTag_id());
                    System.out.println(at.get(0).getTag_id());
                    System.out.println(at.get(1).getTag_id());
                    System.out.println(at.get(2).getTag_id());
                }

                request.setAttribute("article_content", a.getArticle_content());
                request.setAttribute("article_desc", a.getArticle_desc());
                request.setAttribute("article_id", a.getArticle_id());
                request.setAttribute("article_image", a.getArticle_image());
                request.setAttribute("article_isTop", a.getArticle_isTop());
                request.setAttribute("article_title", a.getArticle_title());

                CategoryDao cd = new CategoryDaoImpl();
                int parent_id = cd.getParentCategory(a.getCategory_id());
                Category cate = cd.getOneCategory(parent_id);
                request.setAttribute("parent_name", cate.getCategory_name());
                cate = cd.getOneCategory(a.getCategory_id());
                request.setAttribute("category_name",cate.getCategory_name());
                request.getRequestDispatcher("article/edit.jsp").forward(request, response);// 转发到成功页面
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

