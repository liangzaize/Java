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
        DataSave dataSave = dbControll.searchAccount(a.getType(),a.getFa());
        if (dataSave != null) {
            String name = a.getType();
            String pathname = "/Users/Mario.Hu/Documents/" + name + ".txt";
            File file = new File(pathname);
            dataSave.setPhoto(file2String(file,"utf-8"));
            String jsonObject = gson.toJson(dataSave);
            resp.setCharacterEncoding("utf-8"); //编码
            req.getSession().setAttribute(req.getSession().getId(),name);
            MySessionContext.AddSession(req.getSession());
            resp.addHeader("Set-cookie",req.getSession().getId());
            PrintWriter out = resp.getWriter(); //发送
            out.print(jsonObject);
            out.flush();
            out.close();
        }
    }

    public static String file2String(File file, String encoding) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            if (encoding == null || "".equals(encoding.trim())) {
                reader = new InputStreamReader(new FileInputStream(file), encoding);
            } else {
                reader = new InputStreamReader(new FileInputStream(file));
            }
            //将输入流写入输出流
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            int n = 0;
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
        //返回转换结果
        if (writer != null)
            return writer.toString();
        else return null;
    }
}
