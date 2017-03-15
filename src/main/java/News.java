import GetReq.GetReq;
import GsonChange.GsonTurn;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 接收请求发送文章列表
 * Created by Mario.Hu on 09/01/2017.
 */
public class News extends HttpServlet {

    private GsonTurn a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getReq = new GetReq().toString(req);
        a = gson.fromJson(getReq, GsonTurn.class);
        if (a.getType().equals("hukangze")){
            DBControll db = new DBControll();
            GsonTurn ying_3 = db.get_news_summarize(a.getCount());
            String jsonObject = gson.toJson(ying_3);
            resp.setCharacterEncoding("utf-8"); //编码
            PrintWriter out = resp.getWriter(); //发送
            out.print(jsonObject);
            out.flush();
            out.close();
        }
    }
}
