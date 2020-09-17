package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.ServiceBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/service")
public class ServiceAction extends HttpServlet {

    @EJB
    private ServiceBean serviceBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String msg = "";
        boolean serviceCreated = true;

        String name = request.getParameter("name");

        try {
            this.serviceBean.create(name);
            msg = "service created";
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
           serviceCreated = false;
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            if(serviceCreated) {
                json.put("serviceCreated", true);
            }
            else
                json.put("serviceCreated", false);

            json.put("msg", msg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }



}
