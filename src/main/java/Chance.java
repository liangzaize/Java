import GetReq.GetReq;
import GsonChange.Result;
import GsonChange.Ying;
import Session.MySessionContext;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 接收上传的头像图片然后放到对应数据库位置保存
 * Created by Mario.Hu on 06/01/2017.
 */
public class Chance extends HttpServlet {

    private Ying a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetReq getReq = new GetReq(req);
        String te = getReq.getGet_from();
        a = gson.fromJson(te, Ying.class);
        String get_session = req.getHeader("cookie").substring(11);
        HttpSession session = MySessionContext.getSession(get_session);
        String name = session.getAttribute(session.getId()).toString();
        String pathname = "/Users/Mario.Hu/Documents/" + name + ".txt";
        File file = new File(pathname);
        PrintWriter output = new PrintWriter(file);
        output.print(a.getType());
        DBControll dbControll = new DBControll();  //new一个数据库操作的对象
        Boolean F = dbControll.put_photo(pathname, name);
        if (F) {
            Result result = new Result(true);
            String jsonObject = gson.toJson(result);    //生成json
            resp.setCharacterEncoding("utf-8"); //编码
            PrintWriter out = resp.getWriter(); //发送
            out.print(jsonObject);
            out.flush();
            out.close();
        }
    }
}
