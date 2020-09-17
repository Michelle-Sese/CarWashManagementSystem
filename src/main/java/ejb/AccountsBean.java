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
     * @param year
     * @throws Exception
     */
    public void create(String month, String year, String expenditure, String revenue) throws Exception{
//        String [] dateParts = date.split(".");
//        String month = dateParts[1];
//        String year = dateParts[2];
        try {
            this.accounts.setMonth(month);
            this.accounts.setYear(year);
            this.accounts.setExpenditure(expenditure);
            this.accounts.setRevenue(revenue);


            this.em.merge(this.accounts);
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
