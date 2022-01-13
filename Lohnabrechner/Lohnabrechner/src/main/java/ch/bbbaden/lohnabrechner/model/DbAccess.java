package ch.bbbaden.lohnabrechner.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Michael Schneider <michael.schneider@bbbaden.ch>
 */
public class DbAccess {

    private final static String NEWS_DB;
    private static Connection connection;

    static {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        File path_ = new File(new File(path,"WEB-INF"),"news.sqlite.db");
        NEWS_DB = path_.toString();
    }

    private static void connect() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:" + NEWS_DB;
                connection = DriverManager.getConnection(url);
            } catch (SQLException | ClassNotFoundException e) {
                Logger.getLogger(DbAccess.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public static Connection getConnection() {
        connect();
        return connection;
    }

    public static boolean tableExists(String tableName) {
        connect();
        boolean exists = false;
        try {
            final DatabaseMetaData meta = connection.getMetaData();
            final ResultSet tables = meta.getTables(null, null, tableName, null);
            exists = tables.next();

        } catch (SQLException e) {
            Logger.getLogger(DbAccess.class.getName()).log(Level.SEVERE, null, e);
        }
        return exists;
    }
}
