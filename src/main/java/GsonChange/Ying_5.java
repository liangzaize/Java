package GsonChange;

import java.util.ArrayList;

/**
 * json格式：{"Title":String,"Text":Array,"Name":Array,"Level":Array,"c":boolean}
 * Created by Mario.Hu on 19/01/2017.
 */
public class Ying_5 {
    private String Title;
    private ArrayList Text;
    private ArrayList Name;
    private ArrayList Level;
    private Boolean c;

    public Ying_5(String a,ArrayList e, ArrayList b,ArrayList c,Boolean d){
        this.Text = e;
        this.Title = a;
        this.Name = b;
        this.Level = c;
        this.c = d;
    }
}
