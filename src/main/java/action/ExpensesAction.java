package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.ExpensesBean;
import ejb.StaffBean;
import models.Expenses;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/expenseaction")
public class ExpensesAction extends HttpServlet {

    @EJB
    private ExpensesBean expensesBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String msg = "";
        boolean expensesCreated = true;

        String amount = request.getParameter("amount");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String type_id = request.getParameter("type_id");

        try {
            this.expensesBean.create(amount, email, password, type_id);
            msg = "expenses created";
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
            expensesCreated = false;
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            if(expensesCreated) {
                json.put("expensesCreated", true);
            }
            else
                json.put("expensesCreated", false);

            json.put("msg", msg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }



}
