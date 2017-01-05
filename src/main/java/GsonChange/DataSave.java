package GsonChange;

/**
 * 多个不同类型返回对象
 * json:{"photo":String,"level":String,"money":int}
 * Created by Mario.Hu on 05/01/2017.
 */
public class DataSave {

    private String photo;
    private String level;
    private int money;
    private Boolean bingo;

    public DataSave (String p, String l, Integer m, Boolean b){
        super();
        this.photo = p;
        this.level = l;
        this.money = m;
        this.bingo = b;
    }

}
