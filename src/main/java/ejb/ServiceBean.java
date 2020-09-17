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
     * @throws Exception
     */
    public void create(String name) throws Exception{

        try {
            this.serviceType.setName(name);

            this.em.merge(this.serviceType);
        }catch (EntityExistsException ex){
            throw new Exception(ex.getMessage());
        }catch(IllegalArgumentException ex){
            throw new Exception(ex.getMessage());
        }catch(TransactionalException ex){
            throw new Exception(ex.getMessage());
        }catch (EntityNotFoundException ex){
            throw new Exception(ex.getMessage());
        }
    }
}
