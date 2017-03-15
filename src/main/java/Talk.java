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
 * 发送论坛列表
 * Created by Mario.Hu on 13/01/2017.
 */
public class Talk extends HttpServlet {

    private GsonTurn a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getReq = new GetReq().toString(req);
        GsonTurn y;
        a = gson.fromJson(getReq, GsonTurn.class);
        DBControll db = new DBControll();
        if (a.getType().equals("hukangze")){
            if (req.isRequestedSessionIdFromCookie()){
                String get_session = req.getHeader("cookie").substring(11); //获取http头部cookie的值，并且除去sessionid=这几个字符
                HttpSession session = MySessionContext.getSession(get_session); //重新获取该id对应的session对象
                String name = session.getAttribute(session.getId()).toString(); //获取该session对象保存的用户名
                y = db.getTalk(a.getCount());
                y.setType(name);
            } else {
                y = db.getTalk(a.getCount());
            }
            String jsonObject = gson.toJson(y);
            resp.setCharacterEncoding("utf-8"); //编码
            PrintWriter out = resp.getWriter(); //发送
            out.print(jsonObject);
            out.flush();
            out.close();
        }
    }
}
