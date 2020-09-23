package ejb;

import models.Accounts;
import models.ExpenseType;
import models.Expenses;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;
import java.util.List;

@Stateless
@Remote
public class ExpensesBean {

    @PersistenceContext
    private EntityManager em;

    Expenses expenses = new Expenses();

    /**
     * save new expenses
     * @param amount
     * @param type_id
     * @throws Exception
     */
    public void create(String amount, String type_id) throws Exception{

        int expenseTypeId = Integer.parseInt(type_id);
        double expenseAmount=Double.parseDouble(amount);

        try {
            this.expenses.setAmount(expenseAmount);

            ExpenseType expenseType = this.em.getReference(ExpenseType.class, expenseTypeId);
            this.expenses.setExpense(expenseType);

            this.em.merge(this.expenses);
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
    public Expenses load(int expensesId) throws Exception{
        if (expensesId == 0)
            return new Expenses();

        Expenses expenses= em.find(Expenses.class, expensesId);

        if (expenses == null)
            return new Expenses();

        return expenses;

    }


    // Read

    @SuppressWarnings({"unchecked"})
    public List<Expenses> list(Expenses filter) throws Exception{
        String hql = "SELECT a FROM Expenses a WHERE a.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(String.valueOf(filter.getId())))
                hql += " AND i.idNo like '%" + StringUtils.trim(String.valueOf(filter.getId())) + "%'";

            if (StringUtils.isNotBlank(String.valueOf(filter.getAmount())))
                hql += " AND i.Expenditure like '%" + StringUtils.trim(String.valueOf(filter.getAmount())) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }









}
