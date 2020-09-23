package ejb;
import models.Accounts;
import models.ServiceType;
import models.Services;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Stateless
@Remote
public class ServicesBean {

    @PersistenceContext
    private EntityManager em;

    Services services = new Services();

    /**
     * save new services
     * @param amount
     * @param type_id
     * @throws Exception
     */
    public void create(String amount, String type_id) throws Exception{

        int servicesType = Integer.parseInt(type_id);
        double expenseAmount=Double.parseDouble(amount);


        // Format service time as yyyy-MM-dd HH:mm:ss
        String serviceDateTime = "2020-09-17T17:42";
        String[] parts = serviceDateTime.split("T");
        String  serviceDate = parts[0];
        String serviceTime = parts[1] + ":00";
        String newServiceDateTime = serviceDate+" "+serviceTime;

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(newServiceDateTime);
        try {
            this.services.setAmount(expenseAmount);
            this.services.setTimeOfService(date);

            ServiceType serviceType = this.em.getReference(ServiceType.class, servicesType);
            this.services.setService(serviceType);

            this.em.merge(this.services);
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
    public List<Services> list(Services filter) throws Exception{
        String hql = "SELECT a FROM Services a WHERE a.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(String.valueOf(filter.getId())))
                hql += " AND i.idNo like '%" + StringUtils.trim(String.valueOf(filter.getId())) + "%'";

            if (StringUtils.isNotBlank(String.valueOf(filter.getService())))
                hql += " AND i.Expenditure like '%" + StringUtils.trim(String.valueOf(filter.getService())) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }



    // Delete
    public void delete(int accountsId) throws Exception{
        if (accountsId == 0)
            throw new Exception("Invalid Accounts Id..");

        em.remove(em.find(Accounts.class, accountsId));
    }




















}
