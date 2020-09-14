package login;



import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("register")
public class UserAction extends HttpServlet {

    @EJB
    private UserBean userBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String errorMsg = "";
        boolean success = true;

        User user = new User();
        try {

            BeanUtils.populate(user, request.getParameterMap());

            userBean.createAccount(user);

        }catch (Exception ex){
            ex.printStackTrace();
            errorMsg = ex.getMessage();
            success = false;

        }

        RequestDispatcher dispatcher;

        if (success) {
            request.setAttribute("pageMsg", "Sign In");
            dispatcher = request.getRequestDispatcher("login.jsp");

        }else {
            request.setAttribute("errorMsg", errorMsg);
            request.setAttribute("pageMsg", "Error");
            dispatcher = request.getRequestDispatcher("index.jsp");

        }

        request.setAttribute("success", success);
        dispatcher.forward(request, response);

    }
}
