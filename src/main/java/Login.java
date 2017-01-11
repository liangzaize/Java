import GetReq.GetReq;
import GsonChange.Ying;
import GsonChange.DataSave;
import Session.MySessionContext;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.sun.xml.internal.stream.Entity.ScannedEntity.DEFAULT_BUFFER_SIZE;

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
        DataSave dataSave = dbControll.searchAccount(a.getType(),a.getFa());    //放入账号密码得到用户的各种资料
        if (dataSave != null) {
            String name = a.getType();
            String pathname = "/Users/Mario.Hu/Documents/" + name + ".txt";
            File file = new File(pathname);
            dataSave.setPhoto(file2String(file));   //把图片数据替换到dataSave中
            String jsonObject = gson.toJson(dataSave);
            resp.setCharacterEncoding("utf-8"); //编码
            req.getSession().setAttribute(req.getSession().getId(),name);   //登陆后如果没有session则新建一个
            MySessionContext.AddSession(req.getSession());  //把该对象放进自建的管理器中
            resp.addHeader("Set-cookie",req.getSession().getId());  //把sessionid放进cookie中发送给客户端
            PrintWriter out = resp.getWriter(); //发送
            out.print(jsonObject);
            out.flush();
            out.close();
        }
    }

    public static String file2String(File file) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            //将输入流写入输出流
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            int n;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return writer.toString();
    }
}
