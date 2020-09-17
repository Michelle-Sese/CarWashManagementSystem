package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.ExpenseTypeBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/expenses")
public class ExpenseAction extends HttpServlet {

    @EJB
    private ExpenseTypeBean expenseTypeBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String msg = "";
        boolean expensetypeCreated = true;

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");


        try {
            this.expenseTypeBean.create(name, email, password );
            msg = "expensetype created";
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
            expensetypeCreated = false;
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            if(expensetypeCreated) {
                json.put("expensetypeCreated", true);
            }
            else
                json.put("expensetypeCreated", false);

            json.put("msg", msg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }



}
