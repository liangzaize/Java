package GsonChange;

import java.util.ArrayList;

/**
 * json格式：{"Title":String,"Name":String,"Number":int,"Account":String}
 * Created by Mario.Hu on 13/01/2017.
 */
public class Ying_4 {

    private ArrayList Title;
    private ArrayList Name;
    private ArrayList Number;
    private String Account;
    private ArrayList Posttime;
    private Boolean c;

    public Ying_4 (ArrayList title, ArrayList name, ArrayList number, String account,ArrayList t, Boolean ca){
        this.Title = title;
        this.Name = name;
        this.Number = number;
        this.Account = account;
        this.Posttime = t;
        this.c = ca;
    }

    public void setAccount(String account) {
        Account = account;
    }
}
