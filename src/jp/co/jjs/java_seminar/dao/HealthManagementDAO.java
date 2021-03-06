package jp.co.jjs.java_seminar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jp.co.jjs.java_seminar.beans.MyHealth;

public class HealthManagementDAO {

    private DataSource ds;

    public HealthManagementDAO() {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext
                    .lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/crud");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    //データベースに入っているデータをArrayListに入れて
    //返すメソッドです。
    public ArrayList<MyHealth> getHealth() {
        ArrayList<MyHealth> health = new ArrayList<>();
        try (Connection con = ds.getConnection();
                PreparedStatement ps = con
                        .prepareStatement("SELECT * FROM HEALTH")) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MyHealth myhealth = new MyHealth();
                    myhealth.setId(rs.getInt("id"));
                    myhealth.setDate(rs.getString("date"));
                    myhealth.setHeight(rs.getDouble("height"));
                    myhealth.setWeight(rs.getDouble("weight"));
                    myhealth.setBmi(rs.getDouble("bmi"));
                    myhealth.setSleepTime(rs.getDouble("sleeptime"));

                    health.add(myhealth);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return health;
    }

    //テーブルの中から指定したIDのレコードを返す
    public MyHealth getRecord(int id){

        MyHealth myhealth = new MyHealth();

        try (Connection con = ds.getConnection();
                PreparedStatement ps = con
                        .prepareStatement("SELECT * FROM HEALTH WHERE ID=" + id)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    myhealth.setId(rs.getInt("id"));
                    myhealth.setDate(rs.getString("date"));
                    myhealth.setHeight(rs.getDouble("height"));
                    myhealth.setWeight(rs.getDouble("weight"));
                    myhealth.setBmi(rs.getDouble("bmi"));
                    myhealth.setSleepTime(rs.getDouble("sleeptime"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myhealth;
    }
}
