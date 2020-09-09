package controller;

import database.HibernateHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Accounts;
import models.Department;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/accounts/create"})
public class AccountsCreate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        boolean accountsSaved = false;
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String expenditure = request.getParameter("expenditure");
        String revenue = request.getParameter("revenue");

        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Accounts accounts = new Accounts();
        accounts.setMonth(month);
        accounts.setYear(year);
        accounts.setExpenditure(expenditure);
        accounts.setRevenue(revenue);
        session.save(accounts);
        tx.commit();

        if(accounts != null)
            accountsSaved = true;

        session.close();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        if (accountsSaved)
            json.put("saved", true);
        else
            json.put("saved", false);

        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        response.getWriter().println(data);
    }

}
