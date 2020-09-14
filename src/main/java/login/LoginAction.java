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


@WebServlet("login")
public class LoginAction extends HttpServlet {

    @EJB
    private UserBean userBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        String errorMsg = "";
        boolean success = true;


       User user = new User();

        try {

            BeanUtils.populate(user, request.getParameterMap());

            user= userBean.authenticate(user);


//        User user = new User();
//        try {
//            BeanUtils.populate(user, request.getParameterMap());
//            user = userBean.authenticate(user);

        }catch (Exception ex){
            ex.printStackTrace();
            errorMsg = ex.getMessage();
            success = false;
        }

        RequestDispatcher dispatcher;

        if (success) {
            request.getSession(true).setAttribute("loggedInUser", user);

            request.setAttribute("pageMsg", "Welcome " + user.getName());
            dispatcher = request.getRequestDispatcher("home.jsp");
        }else {
            request.setAttribute("errorMsg", errorMsg);
            request.setAttribute("pageMsg", "Error");
            dispatcher = request.getRequestDispatcher("login.jsp");

        }

        request.setAttribute("success", success);
        dispatcher.forward(request, response);
    }

}
