/**
 * Created by Mario.Hu on 28/12/2016.
 * 与数据库建建立连接
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {

    public static Connection getConnection(){
        Connection connection = null;
        String USER = "root";
        String PASSWORD = "hukangze";
        String DB_URL = "jdbc:mysql://localhost:3306/good?verifyServerCertificate=false&useSSL=true";
        String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("数据库连接异常");
            e.printStackTrace();
        }
        return connection;
    }
    public  static void closeConnection(Connection connection){

        if(connection != null){
            try {
                connection.close(); // 关闭数据库连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
