import GetReq.GetReq;
import GsonChange.Yao;
import GsonChange.Ying_2;
import GsonChange.Ying_3;
import Session.MySessionContext;
import com.google.gson.Gson;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

/**
 * 接收请求发送文章列表
 * Created by Mario.Hu on 09/01/2017.
 */
public class News extends HttpServlet {

    private Yao a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetReq getReq = new GetReq(req);
        String te = getReq.getGet_from();
        a = gson.fromJson(te, Yao.class);
        DBControll dbControll = new DBControll();
        if (a.getType().equals("hukangze")){
            Ying_3 ying_3 = dbControll.get_news_summarize(a.getCount());
            String jsonObject = gson.toJson(ying_3);
            resp.setCharacterEncoding("utf-8"); //编码
            PrintWriter out = resp.getWriter(); //发送
            out.print(jsonObject);
            out.flush();
            out.close();
        }
    }
}
