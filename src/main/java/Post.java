import GetReq.GetReq;
import GsonChange.GsonTurn;
import Session.MySessionContext;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 保存接收到的帖子内容
 * Created by Mario.Hu on 16/01/2017.
 */
public class Post extends HttpServlet {

    private GsonTurn a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getReq = new GetReq().toString(req);
        a = gson.fromJson(getReq, GsonTurn.class);
        String get_session = req.getHeader("cookie").substring(11); //获取http头部cookie的值，并且除去sessionid=这几个字符
        HttpSession session = MySessionContext.getSession(get_session); //重新获取该id对应的session对象
        String name = session.getAttribute(session.getId()).toString(); //获取该session对象保存的用户名
        long millis = System.currentTimeMillis();
        DBControll db = new DBControll();
        Boolean r = db.posttalk(a.getType(),a.getFa(),name,millis/1000);
        GsonTurn result = new GsonTurn(r);
        String json = gson.toJson(result);
        resp.setCharacterEncoding("utf-8"); //编码
        PrintWriter out = resp.getWriter(); //发送
        out.print(json);
        out.flush();
        out.close();
    }
}
