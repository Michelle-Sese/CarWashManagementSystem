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


        String accountsMonthYear = "18-09-2020";
        String[] parts = accountsMonthYear.split("T");
        String  serviceMonth = parts[0];
        String serviceYear = parts[1];
        String newaccountsMonthYear= serviceMonth+" "+serviceYear;

        String pattern = "dd-MM-YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(newaccountsMonthYear);


        try {
            this.accounts.setExpenditure(expenditure);
            this.accounts.setRevenue(revenue);
            this.accounts.setDateOfAccounts(date);


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
