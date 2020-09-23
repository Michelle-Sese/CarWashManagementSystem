package ejb;

import models.Accounts;
import models.ServiceType;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;
import java.util.List;

@Stateless
@Remote
public class ServiceBean {

    @PersistenceContext
    private EntityManager em;

    ServiceType serviceType = new ServiceType();

    /**
     * save new serviceType
     * @param name
     * @throws Exception
     */
    public void create(String name) throws Exception{

        try {
            this.serviceType.setName(name);

            this.em.merge(this.serviceType);
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
    public List<ServiceType> list(ServiceType filter) throws Exception{
        String hql = "SELECT a FROM ServiceType a WHERE a.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(String.valueOf(filter.getId())))
                hql += " AND i.idNo like '%" + StringUtils.trim(String.valueOf(filter.getId())) + "%'";

            if (StringUtils.isNotBlank(filter.getName()))
                hql += " AND i.Expenditure like '%" + StringUtils.trim(filter.getName()) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }

    // Delete
    public void delete(int ServiceTypeId) throws Exception{
        if (ServiceTypeId == 0)
            throw new Exception("Invalid ServiceType Id..");

        em.remove(em.find(ServiceType.class, ServiceTypeId));
    }








}
