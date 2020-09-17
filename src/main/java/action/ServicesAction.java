package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.ServicesBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/servicesaction")
public class ServicesAction extends HttpServlet {

    @EJB
    private ServicesBean servicesBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String msg = "";
        boolean servicesCreated = true;

        String amount = request.getParameter("amount");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String type_id = request.getParameter("type_id");

        try {
            this.servicesBean.create(amount, email, password, type_id);
            msg = "services created";
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
            servicesCreated = false;
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            if(servicesCreated) {
                json.put("servicesCreated", true);
            }
            else
                json.put("servicesCreated", false);

            json.put("msg", msg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }



}
