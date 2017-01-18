/**
 * Created by Mario.Hu on 28/12/2016.
 * 数据库内的操作
 */

import GsonChange.*;

import java.sql.*;
import java.util.ArrayList;

public class DBControll {

    private String out = "";
    private Connection connection = null;

    /***
     * 问数据库拿型号对应的所有厂商
     * @param size 硬件的型号
     * @return 这个型号有什么厂商生产的
     */
    public String requsetxianka(String size) {
        String SQL = "select company from xianka where size = ?";

        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, size);   //这里的意思将用户名和密码填到SQL语句的问号处
            ResultSet rSet = pstmt.executeQuery();  //得到数据库的查询结果,一个数据集
            if (rSet.next()) {    //判断结果集是否有效
                out = rSet.getString("company");
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    public String requseturl(String size, String company) {
        String SQL = "select url from hardurl where size = ? and company = ?";
        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, size);
            pstmt.setString(2, company);
            ResultSet rSet = pstmt.executeQuery();
            if (rSet.next()) {    //判断结果集是否有效
                out = rSet.getString("url");
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConection.closeConnection(connection);
        }
        return out;
    }

    /***
     * 查看数据库用户名是否已经存在，如果存在的话就返回false
     * @param acc 账号
     * @return 如果账号不存在就返回false，否则返回true
     */
    private Boolean foundAccount(String acc) {
        Boolean si = true;
        String SQL = "select account from userinfo where account = ?";
        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, acc);
            ResultSet rSet = pstmt.executeQuery();
            if (rSet.next()) {    //判断结果集是否有效
                si = false;
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    public Boolean putAccount(String acc, String pass) {
        Boolean chao = false;
        Boolean chaos = foundAccount(acc);
        if (chaos) {
            String SQL = "insert into userinfo values( ? , ? , ? , ? , ? )";

            try {
                connection = DBConection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setString(1, acc);
                preparedStatement.setString(2, pass);
                preparedStatement.setString(3, "null");
                preparedStatement.setString(4, "懵懂菜鸟");
                preparedStatement.setInt(5, 0);
                Integer a = preparedStatement.executeUpdate();
                if (a > 0) {
                    chao = true;
                }
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

    /***
     * 登录的时候寻找数据库
     * @param acc 用户名
     * @param pass 密码
     * @return 登录成功的话就返回ture，否则返回false
     */
    public DataSave searchAccount(String acc, String pass) {
        String SQL = "select photo,level,money from userinfo where account = ? and password = ?";
        DataSave dataSave = null;

        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, acc);
            pstmt.setString(2, pass);
            ResultSet rSet = pstmt.executeQuery();
            if (rSet.next()) {    //判断结果集是否有效
                dataSave = new DataSave(rSet.getString("photo"), rSet.getString("level"), rSet.getInt("money"), true);
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConection.closeConnection(connection);
        }
        return dataSave;
    }

    /***
     * 放入用户的照片base64数据到文件中，数据库保存文件的url路径
     * @param image_url 头像数据文件路径
     * @param user_name 用户的名字
     * @return 如果true就是放成功，如果false就是错误的
     */
    public Boolean put_photo(String image_url, String user_name) {
        String SQL = "update userinfo set photo = ? where account = ?";
        Boolean F = false;

        try {
            connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, image_url);
            preparedStatement.setString(2, user_name);
            Integer a = preparedStatement.executeUpdate();
            if (a > 0) {
                F = true;
            }
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConection.closeConnection(connection);
        }
        return F;
    }

    /***
     * 获取新闻的列表数据
     * @param number 这一次想要获取那个范围以内的数据
     * @return 新闻的标题、摘要和图片
     */
    public Ying_3 get_news_summarize(int number){
        String SQL = "select title,summarize,photourl from news where uid = ?";
        int counts = number - 9;
        Ying_3 y = null;
        ArrayList t = new ArrayList();
        ArrayList s = new ArrayList();
        ArrayList p = new ArrayList();
        int count = getCounts("select uid from news");
        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            if (counts > count){
                return new Ying_3(null,null,null,false);
            }
            while (counts != number) {
                pstmt.setInt(1, counts);
                ResultSet rSet = pstmt.executeQuery();
                if (rSet.next()) {    //判断结果集是否有效
                    t.add(rSet.getString("title"));
                    s.add(rSet.getString("summarize"));
                    p.add(rSet.getString("photourl"));
                    counts += 1;
                }
                else {
                    break;
                }
            }

            if (counts > count){
                y = new Ying_3(t,s,p,false);
            } else {
                y = new Ying_3(t,s,p,true);
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConection.closeConnection(connection);
        }
        return y;
    }

    private Integer getCounts (String SQL){
        int number = 0;
        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
                ResultSet rSet = pstmt.executeQuery();
                while (rSet.next()) {    //判断结果集是否有效
                    number += 1;
                }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConection.closeConnection(connection);
        }
        return number;
    }

//    public String get_news(String title, String data){
//        String SQL = "select jsp from news where title = ? and summarize = ?";
//        String jsp = "";
//        try {
//            connection = DBConection.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement(SQL);
//            pstmt.setString(1, title);
//            pstmt.setString(2, data);
//            ResultSet rSet = pstmt.executeQuery();
//                if (rSet.next()) {    //判断结果集是否有效
//                    jsp = rSet.getString("jsp");
//                }
//            connection.close();
//            pstmt.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DBConection.closeConnection(connection);
//        }
//        return jsp;
//    }

    /***
     * 获取论坛列表
     * @param number 这一次要向数据库提取哪个范围的列表
     * @return 标题、名字、数字、判断是否已到底
     */
    public Ying_4 getTalk(int number){
        String SQL = "select titlename,postname,counts from talkview where id = ?";
        int counts = number - 9;
        Ying_4 y = null;
        ArrayList t = new ArrayList();
        ArrayList s = new ArrayList();
        ArrayList p = new ArrayList();
        int count = getCounts("select id from talkview");
        try {
            connection = DBConection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            if (counts > count){
                return new Ying_4(null,null,null,null,false);
            }
            while (counts != number) {
                pstmt.setInt(1, counts);
                ResultSet rSet = pstmt.executeQuery();
                if (rSet.next()) {    //判断结果集是否有效
                    t.add(rSet.getString("titlename"));
                    s.add(rSet.getString("postname"));
                    p.add(rSet.getInt("counts"));
                    counts += 1;
                }
                else {
                    break;
                }
            }
            if (counts > count){
                y = new Ying_4(t,s,p,null,false);
            } else {
                y = new Ying_4(t,s,p,null,true);
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConection.closeConnection(connection);
        }
        return y;
    }

    /***
     * 发表帖子
     * @param title 帖子的标题
     * @param con 帖子的正文
     * @param name 用户名字
     * @return 保存成功返回true
     */
    public Boolean posttalk(String title, String con, String name, long time){
        connection = DBConection.getConnection();
        Statement stmt = null;
        Boolean r = true;
        String SQL_1 = "insert into talkview(titlename,postname,posttime) values(? , ? , ?)";
        String SQL_2 = "insert into maintalk(title,text,username,talk_id) values(? , ? , ? , ?)";
        String SQL_3 = "SELECT id FROM talkview order by id DESC LIMIT 1";
        try {
            connection.setAutoCommit(false);// 更改JDBC事务的默认提交方式
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_1);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, name);
            preparedStatement.setLong(3,time);
            Integer a = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_3);
            ResultSet result = preparedStatement.executeQuery();
            Integer id = 0; // This will be the id you will add on your talk_id
            if(result.next()) {
                id = result.getInt(1);
            }

            preparedStatement = connection.prepareStatement(SQL_2);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, con);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, id);
            Integer b = preparedStatement.executeUpdate();
            connection.commit();//提交JDBC事务
            connection.setAutoCommit(true);// 恢复JDBC事务的默认提交方式
            } catch (SQLException sqle) {
            try {
                connection.rollback();//回滚JDBC事务
                stmt.close();
                connection.close();
                r = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sqle.printStackTrace();
            } finally {
            DBConection.closeConnection(connection);
        }
        return r;
    }
}
