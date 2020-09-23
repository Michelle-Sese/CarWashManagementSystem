package ejb;

import models.Accounts;
import models.Department;
import models.Staff;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Stateless
@Remote
public class AccountsBean<rowsUpdated> {

    @PersistenceContext
    private EntityManager em;

    Accounts accounts = new Accounts();

    public AccountsBean() throws SQLException {
    }

    /**
     * save new accounts
     * @param expenditure
     * @param revenue
     * @throws Exception
     */

    //create

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

    //Load
    public Accounts load(int accountsId) throws Exception{
        if (accountsId == 0)
            return new Accounts();

        Accounts accounts= em.find(Accounts.class, accountsId);

        if (accounts == null)
            return new Accounts();

        return accounts;

    }

    // Read

    @SuppressWarnings({"unchecked"})
    public List<Accounts> list(Accounts filter) throws Exception{
        String hql = "SELECT a FROM Accounts a WHERE a.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(String.valueOf(filter.getId())))
                hql += " AND i.idNo like '%" + StringUtils.trim(String.valueOf(filter.getId())) + "%'";

            if (StringUtils.isNotBlank(filter.getExpenditure()))
                hql += " AND i.Expenditure like '%" + StringUtils.trim(filter.getExpenditure()) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }



    //Update
    public Accounts editEvent(Accounts accounts, int accountsId) throws Exception {
        if (accountsId == 0)
            throw new Exception();

        this.accounts = this.findAccounts(accountsId);
        if (accounts == null)
            throw new Exception();

        return em.merge(accounts);
    }

    public Accounts findAccounts(int accountsId) throws Exception{
        if (accountsId == 0)
            throw new Exception();

        this.accounts = em.find(Accounts.class, accountsId);

        if (accounts == null)
            throw new Exception();

        return accounts;
    }



 // Delete
    public void delete(int accountsId) throws Exception{
        if (accountsId == 0)
            throw new Exception("Invalid Accounts Id..");

        em.remove(em.find(Accounts.class, accountsId));
    }

}
