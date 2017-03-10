import GetReq.GetReq;
import GsonChange.GsonTurn;
import Session.MySessionContext;
import com.google.gson.Gson;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

/**
 * 接收上传的头像图片然后放到对应数据库位置保存
 * Created by Mario.Hu on 06/01/2017.
 */
public class Chance extends HttpServlet {

    private GsonTurn a;
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getReq = GetReq.INSTANCE.toString(req);
        a = gson.fromJson(getReq, GsonTurn.class);
        String get_session = req.getHeader("cookie").substring(11); //获取http头部cookie的值，并且除去sessionid=这几个字符
        HttpSession session = MySessionContext.getSession(get_session); //重新获取该id对应的session对象
        String name = session.getAttribute(session.getId()).toString(); //获取该session对象保存的用户名
        DBControll db = new DBControll();
        String id = Integer.toString(db.findID(name));
        BASE64Decoder decoder = new BASE64Decoder();
        String pathname = "";
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(a.getType());
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            pathname = "/Users/Mario.Hu/Desktop/aa/target/aa/pict/" + id + ".jpg";//新生成的图片
            OutputStream out = new FileOutputStream(pathname);
            out.write(b);
            out.flush();
            out.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Boolean F = db.put_photo(pathname, name);   //如果放置成功了就得到true
        if (F) {
            GsonTurn result = new GsonTurn(true);
            String jsonObject = gson.toJson(result);    //生成json
            resp.setCharacterEncoding("utf-8"); //编码
            PrintWriter out = resp.getWriter(); //发送
            out.print(jsonObject);
            out.flush();
            out.close();
        }
    }
}
