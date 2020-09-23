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
public class StaffBean {

    @PersistenceContext
    private EntityManager em;

    Staff staff = new Staff();

    /**
     * save new staff
     * @param fname
     * @param lname
     * @param email
     * @param password
     * @param dept
     * @throws Exception
     */
    public void create(String fname, String lname, String email, String password, String dept) throws Exception{

        int staffDept = Integer.parseInt(dept);

        try {
            this.staff.setfName(fname);
            this.staff.setlName(lname);
            this.staff.setEmail(email);
            this.staff.setPassword(password);

            Department department = this.em.getReference(Department.class, staffDept);
            this.staff.setDepartment(department);

            this.em.merge(this.staff);
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

    // Read

    @SuppressWarnings({"unchecked"})
    public List<Staff> list(Staff filter) throws Exception{
        String hql = "SELECT a FROM Staff a WHERE a.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(String.valueOf(filter.getId())))
                hql += " AND i.idNo like '%" + StringUtils.trim(String.valueOf(filter.getId())) + "%'";

            if (StringUtils.isNotBlank(filter.getfName()))
                hql += " AND i.Expenditure like '%" + StringUtils.trim(filter.getfName()) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }


    // Delete
    public void delete(int StaffId) throws Exception{
        if (StaffId == 0)
            throw new Exception("Invalid Staff Id..");

        em.remove(em.find(Staff.class, StaffId));
    }










}
