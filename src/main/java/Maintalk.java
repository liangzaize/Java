import GetReq.GetReq;
import GsonChange.Ying;
import GsonChange.Ying_5;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 接收唯一的时间戳与名字，发送信息
 * Created by Mario.Hu on 19/01/2017.
 */
public class Maintalk extends HttpServlet {

    private Ying a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetReq getReq = new GetReq(req);
        String te = getReq.getGet_from();
        a = gson.fromJson(te, Ying.class);
        DBControll dbControll = new DBControll();  //new一个数据库操作的对象
        Ying_5 ying_5 = dbControll.getTalk(a.getType(),a.getFa(),a.getCount());
        String jsonObject = gson.toJson(ying_5);
        resp.setCharacterEncoding("utf-8"); //编码
        PrintWriter out = resp.getWriter(); //发送
        out.print(jsonObject);
        out.flush();
        out.close();
    }
}
