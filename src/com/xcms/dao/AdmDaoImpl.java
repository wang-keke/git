package com.xcms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.xcms.pojo.Adm;
import com.xcms.pojo.ChangePage;
import com.xcms.util.DBUtil;

public class AdmDaoImpl implements AdmDao {

    // 分页方法
    @Override
    public ArrayList<Adm> getPageAdm(int page, int limit) {
        ArrayList<Adm> adm = new ArrayList<Adm>();
        Connection conn = DBUtil.getConnection();
        ChangePage cp = new ChangePage();
        cp.setLimit(limit);
        cp.setPage(page);
        int startRow = cp.getStartRow();
        String sql = "select * from member limit " + startRow + "," + limit + "";
        // 预编译
        try {
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rst = prep.executeQuery();
            while (rst.next()) {
                Adm ad = new Adm();
                ad.setAdm_id(rst.getInt(1));
                ad.setAdm_name(rst.getString("adm_name"));
                ad.setAdm_passward(rst.getString("adm_password"));
                adm.add(ad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return adm;
    }

    // 查询所有的管理员
    @Override
    public ArrayList<Adm> getAllAdm() {
        ArrayList<Adm> adm = new ArrayList<Adm>();
        Connection conn = DBUtil.getConnection();
        // 在数据库中添加记录
        String sql = "select * from member";
        // 预编译
        try {
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rst = prep.executeQuery();
            while (rst.next()) {
                Adm ad = new Adm();
                ad.setAdm_id(rst.getInt(1));
                ad.setAdm_name(rst.getString("adm_name"));
                ad.setAdm_passward(rst.getString("adm_password"));
                adm.add(ad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return adm;
    }

    @Override
    public Adm getAdm(String username, String password) {
        Adm a = null;
        Connection conn = DBUtil.getConnection();
        String sql = "select * from member where adm_name = ? and adm_password = ?";
        // 预编译
        PreparedStatement prep;
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, password);
            ResultSet rst = prep.executeQuery();
            if (rst.next()) {
                System.out.println("我是servlet，我已经查到信息了！！！");
                a = new Adm();
                a.setAdm_id(rst.getInt("adm_id"));
                a.setAdm_name(rst.getString("adm_name"));
                a.setAdm_passward(rst.getString("adm_password"));
                return a;
            } else {
                // System.out.println(username+","+password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    // 3.添加管理员
    @Override
    public boolean insertAdm(String username, String pwd) {
        Connection conn = DBUtil.getConnection();
        // 在数据库中添加记录
        String sql = "insert into member(adm_name,adm_password) values(?,?)";
        // 预编译
        PreparedStatement prep;
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, pwd);
            prep.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteAdm(int adm_id) {
        Connection conn = DBUtil.getConnection();
        // 在数据库中删除记录
        String sql = "delete from member where adm_id = ?";
        // 预编译
        PreparedStatement prep;
        try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, adm_id);
            prep.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateAdm(Adm a) {
        Connection conn = DBUtil.getConnection();
        // 在数据库中修改记录
        String sql = "update member set adm_name = ?,adm_password = ? where adm_id=?";
        // 预编译
        PreparedStatement prep;
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, a.getAdm_name());
            prep.setString(2, a.getAdm_password());
            prep.setInt(3, a.getAdm_id());
            prep.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean queryIsExist(String username) {
        Connection conn = DBUtil.getConnection();
        // 在数据库中查询记录
        String sql = "select * from member where adm_name = ?";
        // 预编译
        PreparedStatement prep;
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, username);
            ResultSet rst = prep.executeQuery();
            if (rst.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Adm getOneAdm(int adm_id) {
        Adm a = null;
        Connection conn = DBUtil.getConnection();
        // 在数据库中修改记录
        String sql = "select * from member where adm_id = ?";
        // 预编译
        PreparedStatement prep;
        try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, adm_id);
            ResultSet rst = prep.executeQuery();
            a = new Adm();
            if (rst.next()) {
                a.setAdm_id(rst.getInt(1));
                a.setAdm_name(rst.getString("adm_name"));
                a.setAdm_passward(rst.getString("adm_password"));
                return a;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return a;
    }

}