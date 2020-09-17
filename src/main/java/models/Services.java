package models;

import javax.persistence.*;

@Entity
@Table(name="services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TIMESTAMP(CURRENT_TIMESTAMP)")
    private  String time;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private  String amount;
    @Column(name="email", columnDefinition = "VARCHAR(24)")
    private  String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="password", columnDefinition = "VARCHAR(24)")
    private  String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id", nullable = false, referencedColumnName = "id")
    private ServiceType service;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ServiceType getService() {
        return service;
    }

    public void setService(ServiceType service) {
        this.service = service;
    }
}

