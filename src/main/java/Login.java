import GetReq.GetReq;
import GsonChange.GsonTurn;
import Session.AccountException;
import Session.MySessionContext;
import com.google.gson.Gson;
import sun.misc.BASE64Encoder;

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

    private GsonTurn a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getReq = new GetReq().toString(req);
        a = gson.fromJson(getReq, GsonTurn.class);
        String err = "";
        GsonTurn dataSave;    //放入账号密码得到用户的各种资料
        try {
            DBControll db = new DBControll();
            dataSave = db.searchAccount(a.getType(),a.getFa());
            String id = Integer.toString(db.findID(a.getType()));
            String pathname = "/Users/Mario.Hu/Desktop/aa/target/aa/pict/" + id + ".jpg";
            String name = a.getType();
            dataSave.setType(file2String(pathname));   //把图片数据替换到dataSave中
            err = gson.toJson(dataSave);
            req.getSession().setAttribute(req.getSession().getId(),name);   //登陆后如果没有session则新建一个
            MySessionContext.AddSession(req.getSession());  //把该对象放进自建的管理器中
            resp.addHeader("Set-cookie",req.getSession().getId());  //把sessionid放进cookie中发送给客户端
        } catch (AccountException e) {
            GsonTurn gsonTurn = new GsonTurn(e.getMessage());
            err = gson.toJson(gsonTurn);
        } finally {
            resp.setCharacterEncoding("utf-8"); //编码
            PrintWriter out = resp.getWriter(); //发送
            out.print(err);
            out.flush();
            out.close();
        }
    }

    public static String file2String(String file) {
        InputStream in;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
}
