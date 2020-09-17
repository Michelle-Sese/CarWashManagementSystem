package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.AuthenticationBean;
import models.Staff;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("admin/login")
public class LoginAction extends HttpServlet {

    @EJB
    private AuthenticationBean authenticationBean;

    /**
     * Perform login
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String authenticationMsg = "";
        boolean authenticated = true;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Staff staff = new Staff();

        try {
            staff = this.authenticationBean.authenticate(email, password);
            authenticationMsg = "Login successful";
        }catch (Exception ex){
            ex.printStackTrace();
            authenticationMsg = ex.getMessage();
            authenticated = false;
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            if(authenticated) {
                HttpSession session = request.getSession(true);
                session.setAttribute("name", staff.getfName() +" "+staff.getlName());
                session.setAttribute("email", staff.getEmail());
                json.put("authenticated", true);
            }
            else
                json.put("authenticated", false);

            json.put("authMsg", authenticationMsg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }


    /**
     * Checks if any user is logged in
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String name =(String)session.getAttribute("name");
        String email = (String)session.getAttribute("email");

        boolean loggedIn = false;

        if(name != null  && email != null) {
            loggedIn = true;
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("name", name);
        json.put("email", email);
        json.put("loggedIn", loggedIn);
        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        response.getWriter().println(data);
    }
}
