package controller;

import database.HibernateHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.ServiceType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/services_type/create"})
public class Service_typeCreate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        boolean serviceSaved = false;
        String name = request.getParameter("name");

        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        ServiceType service_type= new ServiceType();
        service_type.setName(name);
        session.save(service_type);
        tx.commit();

        if(service_type != null)
            serviceSaved = true;

        session.close();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        if (serviceSaved)
            json.put("saved", true);
        else
            json.put("saved", false);

        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        response.getWriter().println(data);
    }

}
