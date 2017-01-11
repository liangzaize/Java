package GsonChange;

/**
 * Created by Mario.Hu on 28/12/2016.
 * 分解json，两个string类型
 */
public class Ying {

    private String Type;
    private String Fa;

    public Ying(String type, String fa){
        super();
        this.Type = type;
        this.Fa = fa;
    }

    public String getType() {
        return Type;
    }

    public String getFa() {
        return Fa;
    }
}
