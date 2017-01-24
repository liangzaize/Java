import GetReq.GetReq;
import GsonChange.GsonTurn;
import Session.AccountException;
import Session.MySessionContext;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Mario.Hu on 03/01/2017.
 * 用来接收登录/注册的账号和密码
 */
public class Sign extends javax.servlet.http.HttpServlet{

    private GsonTurn a;
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getReq = GetReq.INSTANCE.toString(req);
        a = gson.fromJson(getReq, GsonTurn.class);
        String err;
        GsonTurn result;
        try {
            DBControll.INSTANCE.putAccount(a.getType(),a.getFa());
            req.getSession().setAttribute(req.getSession().getId(),a.getType());    //为该用户新建一个session
            MySessionContext.AddSession(req.getSession());
            resp.addHeader("Set-cookie",req.getSession().getId());
            result = new GsonTurn(true);
            err= gson.toJson(result);
        } catch (AccountException e) {
            result = new GsonTurn(e.getMessage());
            err = gson.toJson(result);
        }
        resp.setCharacterEncoding("utf-8"); //编码
        PrintWriter out = resp.getWriter(); //发送
        out.print(err);
        out.flush();
        out.close();
    }
}
