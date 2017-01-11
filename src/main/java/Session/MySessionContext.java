package Session;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 利用哈希Map自建一个session管理器
 * Created by Mario.Hu on 06/01/2017.
 */
public class MySessionContext {

    private static HashMap mymap = new HashMap();   //设置一个静态的HashMap
    public static synchronized void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }
    public static synchronized void DelSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
        }
    }
    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null) {
            return null;
        }
        return (HttpSession) mymap.get(session_id);
    }
}
