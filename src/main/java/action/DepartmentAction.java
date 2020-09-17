package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.DepartmentBean;
import ejb.StaffBean;
import models.Department;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/departments")
public class DepartmentAction extends HttpServlet {

    @EJB
    private DepartmentBean departmentBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String msg = "";
        boolean departmentCreated = true;

        String name = request.getParameter("name");



        try {
            this.departmentBean.create(name);
            msg = "department created";
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
            departmentCreated = false;
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            if(departmentCreated) {
                json.put("departmentCreated", true);
            }
            else
                json.put("departmentCreated", false);

            json.put("msg", msg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }



}
