/**
 * Created by Mario.Hu on 28/12/2016.
 * 从中关村提取对应硬件参数的信息
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Zol {

    ArrayList outZol = new ArrayList();
    ArrayList outZol_1 = new ArrayList();

    public void Zol_Get(String urll) {

        int responsecode, num = 1, num_1 = 0;
        String line, line_1;

        try {
            URL url = new URL(urll);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();   //打开url
            responsecode = urlConnection.getResponseCode(); //获取服务器代码
            if (responsecode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "GBK"));
                while ((line = reader.readLine()) != null) {
                    if (line.contains("newPmVal_" + num)) {
                        line = line.replaceAll("\\<.*?>", "");
                        line = line.replaceAll(" ", "");
                        outZol.add(line);
                        num++;
                    }
                    if (line.contains("#s-" + num_1)) {
                        line_1 = line.replaceAll("\\<.*?>", "");
                        line_1 = line_1.replaceAll(" ", "");
                        outZol_1.add(line_1);
                        num_1++;
                    }
                }
                setOutZol(outZol);
                setOutZol_1(outZol_1);
            } else {
                System.out.println("获取不到网页的源码，服务器响应代码为：" + responsecode);
            }
        } catch (Exception e) {
            System.out.println("获取不到网页的源码,出现异常：" + e);
        }
    }

    public ArrayList getOutZol() {
        return outZol;
    }

    private void setOutZol(ArrayList set){
        this.outZol = set;
    }

    private void setOutZol_1(ArrayList set1){
        this.outZol_1 = set1;
    }

    public ArrayList getOutZol_1(){
        return outZol_1;
    }
}
