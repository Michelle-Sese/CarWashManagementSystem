package ejb;
import models.ServiceType;
import models.Services;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.TransactionalException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
