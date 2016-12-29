import GsonChange.Xianka;
import GsonChange.Ying;
import GsonChange.Ying_1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Servlet extends javax.servlet.http.HttpServlet {

    private Ying a;
    private Gson gson = new Gson();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        StringBuilder sb = new StringBuilder("");
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        String params = sb.toString();
        try {
            a = gson.fromJson(params, Ying.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        DBControll dbControll = new DBControll();
        Xianka xianka = dbControll.requsetxianka(a.getType());
        DBtoArray dBtoArray = new DBtoArray();
        dBtoArray.setDb(xianka.getCompany());
        ArrayList g = dBtoArray.getDb();
        Ying_1 ying_1 = new Ying_1(g);
        String jsonObject = gson.toJson(ying_1);
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

    }
}
