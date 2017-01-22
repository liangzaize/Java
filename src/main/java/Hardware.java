/**
 * Created by Mario.Hu on 28/12/2016.
 * 获取具体厂商，返回具体硬件参数的接口
 */
import GetReq.GetReq;
import GsonChange.GsonTurn;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Hardware extends javax.servlet.http.HttpServlet {

    private GsonTurn a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetReq getReq = new GetReq(req);
        Zol zol = new Zol();
        String te = getReq.getGet_from();
        a = gson.fromJson(te, GsonTurn.class);
        DBControll dbControll = new DBControll();  //new一个数据库操作的对象
        String xianka = dbControll.requseturl(a.getType(),a.getFa());   //把账号和密码放进数据库
        zol.Zol_Get(xianka);    //把地址传去抓代码
        ArrayList a = zol.getOutZol();  //详细信息
        ArrayList b = zol.getOutZol_1();    //类别信息
        GsonTurn ying_2 = new GsonTurn(a,b);
        String jsonObject = gson.toJson(ying_2);    //生成json
        resp.setCharacterEncoding("utf-8"); //编码
        PrintWriter out = resp.getWriter(); //发送
        out.print(jsonObject);
        out.flush();
        out.close();
    }
}
