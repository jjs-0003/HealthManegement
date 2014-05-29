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

public class HealthSearchDAO {

    private DataSource ds;

    public HealthSearchDAO() {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext
                    .lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/crud");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MyHealth> getHealth(double maxweight, double minweight, double weight) {
        ArrayList<MyHealth> health = new ArrayList<>();
        try (Connection con = ds.getConnection();



                PreparedStatement ps = con
                        .prepareStatement("SELECT * FROM HEALTH WHERE ? BETWEEN ? AND ?")) {

            ps.setString(1,"weight");
            ps.setString(2,"minweight");
            ps.setString(3,"maxweight");

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

}
