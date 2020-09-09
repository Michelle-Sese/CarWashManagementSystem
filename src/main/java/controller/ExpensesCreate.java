package controller;

import database.HibernateHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Department;
import models.ExpenseType;
import models.Expenses;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/expenses/create"})
public class ExpensesCreate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String amount = request.getParameter("amount");
        String name = request.getParameter("name");

        Session session = HibernateHelper.getSessionFactory().openSession();
        ExpenseType expenseType = (ExpenseType) session.createQuery("FROM ExpenseType ET WHERE ET.name = :name")
                .setParameter("name",name)
                .getSingleResult();

        Transaction tx = session.getTransaction();
        tx.begin();
        Expenses expenses = new Expenses();
        expenses.setAmount(amount);
        expenses.setExpense(expenseType);
        session.save(expenses);
        tx.commit();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("saved", true);
        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        response.getWriter().println(data);
    }

}
