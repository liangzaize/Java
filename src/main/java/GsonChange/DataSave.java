package GsonChange;

/**
 * 用于登录时返回用户的信息：头像路径、登录、金钱、登录是否成功
 * json:{"photo":String,"level":String,"money":int,"bingo":boolean}
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

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
