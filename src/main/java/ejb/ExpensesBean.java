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
     * @param email
     * @param password
     * @param type_id
     * @throws Exception
     */
    public void create(String amount, String email, String password, String type_id) throws Exception{

        int expensestype_id = Integer.parseInt(type_id);

        try {
            this.expenses.setAmount(amount);
            this.expenses.setEmail(email);
            this.expenses.setPassword(password);

            ExpenseType expenseType = this.em.getReference(ExpenseType.class, expensestype_id);
            this.expenses.setExpense(expenseType);

            this.em.merge(this.expenses);
        }catch (EntityExistsException ex){
            throw new Exception("The entity user already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance expenses is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }catch (EntityNotFoundException ex){
            throw new Exception("User expenses not found");
        }
    }
}
