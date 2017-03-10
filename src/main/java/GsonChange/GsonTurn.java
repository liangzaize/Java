package GsonChange;

import java.util.ArrayList;

/**
 * Created by Mario.Hu on 22/01/2017.
 */
public class GsonTurn {

    private boolean Port,Port1;
    private String Type,Fa,Title;
    private int Count;
    private ArrayList Head,Body,Image,Posttime;

    public GsonTurn(boolean port){
        this.Port = port;
    }

    public GsonTurn(String type){
        this.Type = type;
    }

    public GsonTurn(String type, int count){
        this.Type = type;
        this.Count = count;
    }

    public GsonTurn(String type, String fa, int count){
        this.Type = type;
        this.Fa= fa;
        this.Count = count;
    }

    public GsonTurn(ArrayList head){
        this.Head = head;
    }

    public GsonTurn(ArrayList head, ArrayList body){
        this.Head = head;
        this.Body = body;
    }

    public GsonTurn(ArrayList head, ArrayList body, ArrayList image, Boolean port){
        this.Head = head;
        this.Body = body;
        this.Image = image;
        this.Port = port;
    }

    public GsonTurn(String type, String fa, int count, Boolean port){
        this.Type = type;
        this.Fa = fa;
        this.Count = count;
        this.Port1 = port;
    }

    public GsonTurn(ArrayList head, ArrayList body, ArrayList image, String type, ArrayList posttime, Boolean port){
        this.Head = head;
        this.Body = body;
        this.Image = image;
        this.Type = type;
        this.Posttime = posttime;
        this.Port = port;
    }

    public int getCount() {
        return Count;
    }

    public String getType() {
        return Type;
    }

    public String getFa() {
        return Fa;
    }

    public void setType(String type) {
        Type = type;
    }
}
