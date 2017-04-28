import Session.MySessionContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 28/04/2017 22:03
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
public class Cancel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String get_session = req.getHeader("cookie").substring(11);
        MySessionContext.DelSession(MySessionContext.getSession(get_session));
    }
}
