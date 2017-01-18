import GetReq.GetReq;
import GsonChange.Yao;
import GsonChange.Ying_4;
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

    private Yao a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetReq getReq = new GetReq(req);
        String te = getReq.getGet_from();
        Ying_4 y;
        a = gson.fromJson(te, Yao.class);
        DBControll dbControll = new DBControll();  //new一个数据库操作的对象
        if (a.getType().equals("hukangze")){
            if (req.isRequestedSessionIdFromCookie()){
                String get_session = req.getHeader("cookie").substring(11); //获取http头部cookie的值，并且除去sessionid=这几个字符
                HttpSession session = MySessionContext.getSession(get_session); //重新获取该id对应的session对象
                String name = session.getAttribute(session.getId()).toString(); //获取该session对象保存的用户名
                y = dbControll.getTalk(a.getCount());
                y.setAccount(name);
            } else {
                y = dbControll.getTalk(a.getCount());
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
