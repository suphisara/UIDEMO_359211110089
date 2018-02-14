package sample;

import dbUtil.dbConnection;

import java.sql.*;

public class loginModel {
    Connection connection;

    public loginModel() {
        try {
            this.connection = dbConnection.getConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        if (this.connection == null){
            System.exit(1);
        }
    }
    public boolean isDatabaseConnection(){
        return this.connection != null;
    }
    public  boolean isLogin(String user, String pass) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "select * from user where username = ? and password = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,user);
            pr.setString(2,pass);

            rs = pr.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        }catch ( SQLDataException ex) {
            ex.printStackTrace();
            return false;
        }finally {
            pr.close();
            rs.close();
        }

    }



}
