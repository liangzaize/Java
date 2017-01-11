package GsonChange;

/**
 * 分解json，一个string，一个int
 * Created by Mario.Hu on 09/01/2017.
 */
public class Yao {

    private String Type;
    private int Count;

    public Yao (String type, int count){
        super();
        this.Type = type;
        this.Count = count;
    }

    public int getCount() {
        return Count;
    }

    public String getType() {
        return Type;
    }
}
