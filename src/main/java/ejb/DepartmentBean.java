package ejb;

import models.Accounts;
import models.Department;
import models.Staff;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;
import java.util.List;

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

    //Load
    public Department load(int departmentId) throws Exception{
        if (departmentId == 0)
            return new Department();

        Department department= em.find(Department.class, departmentId);

        if (department == null)
            return new Department();

        return department;

    }


    // Read

    @SuppressWarnings({"unchecked"})
    public List<Department> list(Department filter) throws Exception{
        String hql = "SELECT a FROM Department a WHERE a.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(String.valueOf(filter.getId())))
                hql += " AND i.idNo like '%" + StringUtils.trim(String.valueOf(filter.getId())) + "%'";

            if (StringUtils.isNotBlank(filter.getName()))
                hql += " AND i.Expenditure like '%" + StringUtils.trim(filter.getName()) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }


    // Delete
    public void delete(int departmentId) throws Exception{
        if (departmentId == 0)
            throw new Exception("Invalid Department Id..");

        em.remove(em.find(Accounts.class, departmentId));
    }









}
