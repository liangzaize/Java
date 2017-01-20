package GsonChange;

/**
 * Created by Mario.Hu on 28/12/2016.
 * 分解json
 */
public class Ying {

    private String Type;
    private String Fa;
    private int Count;

    public Ying(String type, String fa, int a){
        super();
        this.Type = type;
        this.Fa = fa;
        this.Count = a;
    }

    public String getType() {
        return Type;
    }

    public String getFa() {
        return Fa;
    }

    public int getCount() {
        return Count;
    }
}
