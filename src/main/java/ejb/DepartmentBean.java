package ejb;

import models.Department;
import models.Staff;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;

@Stateless
@Remote
public class DepartmentBean {

    @PersistenceContext
    private EntityManager em;

    Department department = new Department();

    /**
     * save new department
     * @param name
     * @throws Exception
     */
    public void create(String name) throws Exception{

        try {
            this.department.setName(name);
            this.em.merge(this.department);
        }catch (EntityExistsException ex){
            throw new Exception("The entity department already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance department is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }
    }
}
