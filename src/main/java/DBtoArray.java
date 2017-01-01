/**
 * Created by Mario.Hu on 28/12/2016.
 * 把字符串分解成数组
 */
import java.util.ArrayList;

public class DBtoArray {

    private String db;
    private int i,j = 0;
    private String[] port = new String[20];
    private ArrayList al =new ArrayList();

    public void setDb(String db) {
        this.db = db;
    }

    public ArrayList getDb() {
        SizeToCompany();
        return al;
    }

    private void SizeToCompany(){
        while(db.charAt(i) != '#' ){
            port[j] = "";
            while(db.charAt(i) != '&' && db.charAt(i) != '#') {
                port[j] += db.charAt(i);
                i++;
            }
            if(db.charAt(i) != '#'){
                i++;
            }
            j++;
        }
        i = 0;
        while(port[i]!= null){
            al.add(port[i]);
            i++;
        }
    }
}
