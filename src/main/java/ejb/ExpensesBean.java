package ejb;

import models.ExpenseType;
import models.Expenses;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;

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
}
