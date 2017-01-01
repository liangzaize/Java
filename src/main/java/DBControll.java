/**
 * Created by Mario.Hu on 28/12/2016.
 * 数据库内的操作
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBControll {

    private String out ="";

    /**
     * 问数据库拿型号对应的所有厂商
     */
    public String requsetxianka(String size){
        String SQL = "select company from xianka where size = ?";
        Connection connection = null;
        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, size);   //这里的意思将用户名和密码填到SQL语句的问号处
            ResultSet rSet = pstmt.executeQuery();  //得到数据库的查询结果,一个数据集
            if(rSet.next()){    //判断结果集是否有效
                out = rSet.getString("company");
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            DBConection.closeConnection(connection);
        }
        return out;
    }

    /**
     * 问数据库拿出对应的硬件参数网址
     */
    public String requseturl(String size, String company){
        String SQL = "select url from hardurl where size = ? and company = ?";
        Connection connection = null;
        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, size);
            pstmt.setString(2, company);
            ResultSet rSet = pstmt.executeQuery();
            if(rSet.next()){    //判断结果集是否有效
                out = rSet.getString("url");
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            DBConection.closeConnection(connection);
        }
        return out;
    }
}
