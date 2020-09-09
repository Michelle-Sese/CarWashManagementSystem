package controller;

import database.HibernateHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Department;
import models.Services;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/services/create"})
public class ServicesCreate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        boolean servicesSaved = false;
        String amount = request.getParameter("amount");
        String time = request.getParameter("time");
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Services services = new   Services ();
        services.setAmount(amount);
        services.setTime(time);
        session.save(services);
        tx.commit();

        if(services != null)
            servicesSaved = true;

        session.close();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        if (servicesSaved)
            json.put("saved", true);
        else
            json.put("saved", false);

        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        response.getWriter().println(data);
    }

}
