package GsonChange;

import java.util.ArrayList;

/**
 * Created by Mario.Hu on 10/03/2017.
 */
public class GsonTurn_1 {

    private String Type;
    private ArrayList Head,Body,Image,Posttime;
    private Boolean Port;

    public GsonTurn_1(String type, ArrayList head, ArrayList body, ArrayList image, ArrayList id, Boolean port){
        this.Type = type;
        this.Head = head;
        this.Body = body;
        this.Image = image;
        this.Posttime= id;
        this.Port = port;
    }
}
