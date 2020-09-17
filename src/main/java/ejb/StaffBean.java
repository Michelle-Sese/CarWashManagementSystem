package ejb;

import models.Department;
import models.Staff;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;

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
}
