package ejb;

import models.Accounts;
import models.Department;
import models.Staff;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;

@Stateless
@Remote
public class AccountsBean {

    @PersistenceContext
    private EntityManager em;

    Accounts accounts = new Accounts();

    /**
     * save new accounts
     * @param expenditure
     * @param revenue
     * @param email
     * @param password
     * @throws Exception
     */
    public void create(String month, String expenditure, String email, String password, String revenue) throws Exception{

        try {
            this.accounts.setMonth(month);
            this.accounts.setExpenditure(expenditure);
            this.accounts.setEmail(email);
            this.accounts.setPassword(password);
            this.accounts.setRevenue(revenue);


            this.em.merge(this.accounts);
        }catch (EntityExistsException ex){
            throw new Exception("The entity user already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance accounts is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }catch (EntityNotFoundException ex){
            throw new Exception("User accounts not found");
        }
    }
}
