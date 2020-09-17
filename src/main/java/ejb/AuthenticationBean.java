package ejb;

import models.Staff;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class AuthenticationBean {

    @PersistenceContext
    private EntityManager em;

    public Staff authenticate(String email, String password) throws Exception{
        if(StringUtils.isBlank(email) || StringUtils.isBlank(password))
            throw new Exception("Wrong email or password");

        String hql = "SELECT S FROM Staff S WHERE S.email = :email AND S.password = :password";

        try {
            Staff user = (Staff)this.em.createQuery(hql)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            return user;
        }
        catch (NoResultException ex){
            throw new Exception("Wrong email or password");
        }
    }
}
