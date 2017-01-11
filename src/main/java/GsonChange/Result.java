package GsonChange;

/**
 * json:{"Port":boolean}
 * Created by Mario.Hu on 03/01/2017.
 * 只返回成功或不成功的标志
 */
public class Result {

    private boolean Port;

    public Result(boolean port){
        super();
        this.Port = port;
    }
}
