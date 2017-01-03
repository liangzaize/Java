/**
 * Created by Mario.Hu on 28/12/2016.
 * 数据库内的操作
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBControll {

    private String out = "";

    /***
     * 问数据库拿型号对应的所有厂商
     * @param size 硬件的型号
     * @return 这个型号有什么厂商生产的
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

    /***
     * 问数据库拿出对应的硬件参数网址
     * @param size 硬件的型号
     * @param company 硬件的厂商
     * @return 对应具体的参数网址
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

    /***
     * 查看数据库用户名是否已经存在，如果存在的话就返回false
     * @param acc 账号
     * @return 如果账号不存在就返回false，否则返回true
     */
    private Boolean foundAccount(String acc){
        Boolean si = true;
        String SQL = "select account from userinfo where account = ?";
        Connection connection = null;
        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1,acc);
            ResultSet rSet = pstmt.executeQuery();
            if(rSet.next()){    //判断结果集是否有效
                si = false;
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            DBConection.closeConnection(connection);
        }
        return si;
    }

    /***
     * 数据库注册操作
     * @param acc 账号
     * @param pass 密码
     * @return ture的话就是注册成功，false就是失败了
     */
    public Boolean putAccount(String acc, String pass){
        Boolean chao = false;
        Boolean chaos = foundAccount(acc);
        if (chaos) {
            String SQL = "insert into userinfo values(? , ? , ? , ? , ?)";
            Connection connection = null;

            try {
                connection = DBConection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setInt(1,0);
                preparedStatement.setString(2,acc);
                preparedStatement.setString(3,pass);
                preparedStatement.setString(4,"null");
                preparedStatement.setString(5,"懵懂菜鸟");
                Integer a = preparedStatement.executeUpdate();
                chao = true;
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBConection.closeConnection(connection);
            }
        }
        return chao;
    }
}
