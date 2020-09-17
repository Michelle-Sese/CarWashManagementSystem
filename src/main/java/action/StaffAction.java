package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.StaffBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users")
public class StaffAction extends HttpServlet {

    @EJB
    private StaffBean staffBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String msg = "";
        boolean staffCreated = true;

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String dept = request.getParameter("dept");

        try {
            this.staffBean.create(fname, lname, email, password, dept);
            msg = "staff created";
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
            staffCreated = false;
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            if(staffCreated) {
                json.put("staffCreated", true);
            }
            else
                json.put("staffCreated", false);

            json.put("msg", msg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }



}
