/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.util;

/**
 *
 * @author JAVA_LM
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConecta {

    private final String host;
    private final String port;
    private final String url;
    private final String db;
    private final String username;
    private final String password;

    public SqlConecta() {
        this.host = "localhost";
        this.port = "3306";
        this.db = "trabajofinal";
        this.url = String.format("jdbc:mysql://%s:%s/%s", this.host, this.port, this.db);        
        this.username = "root";
        this.password = "123456";
    }

    public Connection connection() {
        Connection cn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection(this.url, this.username, this.password);
            
        } catch (SQLException sqle) {
          sqle.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ERROR Class NF"+cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.out.println("ERROR Init Exc"+ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR Illegal acc ex"+iae.getMessage());
        }
        
        return cn;
    }
}
