package controller;

import database.HibernateHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Department;
import models.ExpenseType;
import models.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/staff/create"})
public class StaffCreate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        boolean staffSaved = false;
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");

        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
       Department department = (Department) session.createQuery("FROM Department ET WHERE ET.id = :id")
                .setParameter("name",lname)
               .setParameter("name", fname)
                .getSingleResult();


        Transaction tx = session.getTransaction();
        tx.begin();
        Staff staff = new Staff();
        staff.setfName(fname);
        staff.setlName(lname);
        session.save(staff);
        tx.commit();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
            json.put("saved", true);
        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        response.getWriter().println(data);
    }

}
