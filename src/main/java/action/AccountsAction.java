package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.AccountsBean;
import ejb.StaffBean;
import models.Accounts;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/accounts")
public class AccountsAction extends HttpServlet {

    @EJB
    private AccountsBean accountsBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String msg = "";
        boolean accountsCreated = true;


        String expenditure = request.getParameter("expenditure");
        String revenue = request.getParameter("revenue");


        try {
            this.accountsBean.create(expenditure, revenue);
            msg = "accounts created";
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
            accountsCreated = false;
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            if(accountsCreated) {
                json.put("accountsCreated", true);
            }
            else
                json.put("accountsCreated", false);

            json.put("msg", msg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }



}
