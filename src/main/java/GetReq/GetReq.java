package GetReq;

/**
 * Created by Mario.Hu on 28/12/2016.
 * 获取数据流的封装
 */
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public enum GetReq {
    INSTANCE;

    private HttpServletRequest get_from;

    GetReq(){
    }

    public String toString(HttpServletRequest httpServletRequest){
        this.get_from = httpServletRequest;
        return getorgo();
    }

    private String getorgo(){
        String from = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(get_from.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            from = sb.toString();  //把接收的信息转换成string类型
        } catch (IOException e) {
            e.printStackTrace();
        }
        return from;
    }

}
