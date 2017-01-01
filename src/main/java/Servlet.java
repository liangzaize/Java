/**
 * Created by Mario.Hu on 28/12/2016.
 * 地址:"localhost/a"，接收硬件型号的接口
 */

import GetReq.GetReq;
import GsonChange.Ying;
import GsonChange.Ying_1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Servlet extends javax.servlet.http.HttpServlet {

    private Ying a;
    private Gson gson = new Gson();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        GetReq getReq = new GetReq(request);    //用来读取输入流的类
        String params = getReq.getGet_from();
        try {
            a = gson.fromJson(params, Ying.class);  //利用Ying的类来解析送来的型号
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        DBControll dbControll = new DBControll();  //new一个数据库操作的对象
        String xianka = dbControll.requsetxianka(a.getType());  //拿出Type中的数据然后交给数据库操作，之后从数据库中取出对应数据
        DBtoArray dBtoArray = new DBtoArray();  //这是把数据调整成我们需要的格式
        dBtoArray.setDb(xianka);
        ArrayList g = dBtoArray.getDb();    //调整完后就是一个ArrayList类型
        Ying_1 ying_1 = new Ying_1(g);  //放在即将发出的结构中
        String jsonObject = gson.toJson(ying_1);    //生成json
        response.setCharacterEncoding("utf-8"); //编码
        PrintWriter out = response.getWriter(); //发送
        out.print(jsonObject);
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

    }
}
