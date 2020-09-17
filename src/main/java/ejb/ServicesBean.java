package ejb;
import models.ServiceType;
import models.Services;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;

@Stateless
@Remote
public class ServicesBean {

    @PersistenceContext
    private EntityManager em;

    Services services = new Services();

    /**
     * save new services
     * @param amount
     * @param email
     * @param password
     * @param type_id
     * @throws Exception
     */
    public void create(String amount, String email, String password, String type_id) throws Exception{

        int servicestype_id = Integer.parseInt(type_id);

        try {
            this.services.setAmount(amount);
            this.services.setEmail(email);
            this.services.setPassword(password);

            ServiceType serviceType = this.em.getReference(ServiceType.class, servicestype_id);
            this.services.setService(serviceType);

            this.em.merge(this.services);
        }catch (EntityExistsException ex){
            throw new Exception("The entity user already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance services is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }catch (EntityNotFoundException ex){
            throw new Exception("User services not found");
        }
    }
}
