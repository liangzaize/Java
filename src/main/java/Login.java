import GetReq.GetReq;
import GsonChange.Ying;
import GsonChange.DataSave;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理用户登录的类
 * Created by Mario.Hu on 05/01/2017.
 */
public class Login extends HttpServlet{

    private Ying a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetReq getReq = new GetReq(req);
        String te = getReq.getGet_from();
        a = gson.fromJson(te, Ying.class);
        DBControll dbControll = new DBControll();  //new一个数据库操作的对象
        DataSave dataSave = dbControll.searchAccount(a.getType(),a.getFa());
        if (dataSave != null) {
            String jsonObject = gson.toJson(dataSave);
            resp.setCharacterEncoding("utf-8"); //编码
            PrintWriter out = resp.getWriter(); //发送
            out.print(jsonObject);
            out.flush();
            out.close();
        }
    }

}
