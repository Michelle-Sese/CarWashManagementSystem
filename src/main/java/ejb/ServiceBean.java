package ejb;

import models.ServiceType;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;

@Stateless
@Remote
public class ServiceBean {

    @PersistenceContext
    private EntityManager em;

    ServiceType serviceType = new ServiceType();

    /**
     * save new serviceType
     * @param name
     * @param email
     * @param password
     * @throws Exception
     */
    public void create(String name, String email, String password) throws Exception{

        try {
            this.serviceType.setName(name);
            this.serviceType.setEmail(email);
            this.serviceType.setPassword(password);


            this.em.merge(this.serviceType);
        }catch (EntityExistsException ex){
            throw new Exception("The entity user already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance service is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }catch (EntityNotFoundException ex){
            throw new Exception("User service not found");
        }
    }
}
