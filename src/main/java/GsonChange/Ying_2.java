package GsonChange;

import java.util.ArrayList;

/**
 * 接收两个数组，发送两个数组
 * Created by Mario.Hu on 01/01/2017.
 * json:{"Type":ArrayList[],"Port":ArrayList[]}
 */
public class Ying_2 {

    private ArrayList Type;
    private ArrayList Port;


    public Ying_2(ArrayList type, ArrayList port){
        super();
        this.Type = type;
        this.Port = port;
    }

    public ArrayList getType() {
        return Type;
    }

    public ArrayList getPort() {
        return Port;
    }
}
