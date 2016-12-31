package GetReq;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetReq {

    private HttpServletRequest get_from;
    private String from;

    public GetReq (HttpServletRequest req){
        super();
        this.get_from = req;
    }

    public String getGet_from() {
        getorgo();
        return from;
    }

    public void setGet_from(HttpServletRequest get_from) {
        this.get_from = get_from;
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
            this.from = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}