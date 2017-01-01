package GsonChange;

/**
 * Created by Mario.Hu on 28/12/2016.
 * 用来分解接收到的json串格式
 */
public class Ying {

    private String Type;
    private String Fa;

    public Ying(String type, String fa){
        super();
        this.Type = type;
        this.Fa = fa;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getType() {
        return Type;
    }

    public void setFa(String fa) {
        Fa = fa;
    }

    public String getFa() {
        return Fa;
    }
}
