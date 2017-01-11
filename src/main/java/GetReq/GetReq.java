package GetReq;

/**
 * Created by Mario.Hu on 28/12/2016.
 * 获取数据流的封装
 */
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetReq {

    private HttpServletRequest get_from;
    private String from;

    public GetReq (HttpServletRequest req){
        super();
        this.get_from = req;    //从客户端接收到的HttpServletRequest数据放在这
    }

    public String getGet_from() {
        getorgo();
        return from;
    }

    private void getorgo(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(get_from.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            this.from = sb.toString();  //把接收的信息转换成string类型
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
