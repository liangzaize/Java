package GsonChange;

import java.util.ArrayList;

/**
 * json格式：{"Title":String,"Summarize":String,"Image":String,"c":boolean}
 * Created by Mario.Hu on 09/01/2017.
 */
public class Ying_3 {

    private ArrayList Title;
    private ArrayList Summarize;
    private ArrayList Image;
    private Boolean c;

    public Ying_3(ArrayList title,ArrayList summarize,ArrayList image,Boolean in){
        super();
        this.Title = title;
        this.Summarize = summarize;
        this.Image = image;
        this.c = in;
    }

}
