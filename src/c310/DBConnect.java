/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c310;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sadman
 */
class DBConnect {
    public DBConnect() throws SQLException{
        Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/Person","test","1234");
        System.out.println("Done");
    }
}
