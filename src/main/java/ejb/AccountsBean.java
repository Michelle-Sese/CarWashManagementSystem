package ejb;

import models.Accounts;
import models.Department;
import models.Staff;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * @throws Exception
     */
    public void create( String expenditure, String revenue) throws Exception{

        // Format service time as yyyy-MM-dd HH:mm:ss
        String accountsDateTime = "2020-09-17T17:42";
        String[] parts = accountsDateTime.split("T");
        String  accountsDate = parts[0];
        String accountsTime = parts[1] + ":00";
        String newAccountsDateTime = accountsDate+" "+accountsTime;

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(newAccountsDateTime);


        try {
            this.accounts.setExpenditure(expenditure);
            this.accounts.setRevenue(revenue);
            this.accounts.setTimeOfaccounts(date);


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
