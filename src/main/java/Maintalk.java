import GetReq.GetReq;
import GsonChange.GsonTurn;
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

    private GsonTurn a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getReq = GetReq.INSTANCE.toString(req);
        a = gson.fromJson(getReq, GsonTurn.class);
        GsonTurn ying_5 = DBControll.INSTANCE.getTalk(a.getType(),a.getFa(),a.getCount());
        String jsonObject = gson.toJson(ying_5);
        resp.setCharacterEncoding("utf-8"); //编码
        PrintWriter out = resp.getWriter(); //发送
        out.print(jsonObject);
        out.flush();
        out.close();
    }
}
