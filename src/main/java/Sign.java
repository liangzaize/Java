import GetReq.GetReq;
import GsonChange.Result;
import GsonChange.Ying;
import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;

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

    private Ying a;
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetReq getReq = new GetReq(req);
        String te = getReq.getGet_from();
        a = gson.fromJson(te, Ying.class);
        DBControll dbControll = new DBControll();  //new一个数据库操作的对象
        Boolean returnSign = dbControll.putAccount(a.getType(),a.getFa());
        Result result = new Result(returnSign);
        String json = gson.toJson(result);
        resp.setCharacterEncoding("utf-8"); //编码
        PrintWriter out = resp.getWriter(); //发送
        out.print(json);
        out.flush();
        out.close();
    }
}
