package ejb;

import models.Accounts;
import models.ExpenseType;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;
import java.util.List;

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

    // Read

    @SuppressWarnings({"unchecked"})
    public List<ExpenseType> list(ExpenseType filter) throws Exception{
        String hql = "SELECT a FROM ExpenseType a WHERE a.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(String.valueOf(filter.getId())))
                hql += " AND i.idNo like '%" + StringUtils.trim(String.valueOf(filter.getId())) + "%'";

            if (StringUtils.isNotBlank(filter.getName()))
                hql += " AND i.Expenditure like '%" + StringUtils.trim(filter.getName()) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }

    // Delete
    public void delete(int ExpenseTypeId) throws Exception{
        if (ExpenseTypeId == 0)
            throw new Exception("Invalid ExpenseType Id..");

        em.remove(em.find(ExpenseType.class, ExpenseTypeId));
    }

}
