package ejb;

import models.ExpenseType;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;

@Stateless
@Remote
public class ExpenseTypeBean {

    @PersistenceContext
    private EntityManager em;

    ExpenseType expenseType = new ExpenseType();

    /**
     * save new expenseType
     * @param name
     * @throws Exception
     */
    public void create(String name) throws Exception{

        try {
            this.expenseType.setName(name);


            this.em.merge(this.expenseType);
        }catch (EntityExistsException ex){
            throw new Exception("The entity department already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance department is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }
    }
}
