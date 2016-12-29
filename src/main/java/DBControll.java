import GsonChange.Xianka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBControll {
    private Xianka xianka = null;
    private String SQL ="";

    public Xianka requsetxianka(String size){
        SQL = "select company from xianka where size = ?";
        Connection connection = null;
        PreparedStatement pstmt;
        try {
            connection = DBConection.getConnection();
            pstmt = connection.prepareStatement(SQL);
            //这里的意思将用户名和密码填到SQL语句的问号处
            pstmt.setString(1, size);
            ResultSet rSet = pstmt.executeQuery();//得到数据库的查询结果,一个数据集
            //判断结果集是否有效
            if(rSet.next()){
                xianka = new Xianka();
                xianka.setCompany(rSet.getString("company"));
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBConection.closeConnection(connection);
        }
        return xianka;
    }

}
