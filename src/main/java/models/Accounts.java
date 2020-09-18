package models;

import javax.persistence.*;
import java.util.Date;


@Entity
    @Table(name="accounts")
    public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="expenditure", columnDefinition = "INT(11)")
    private String  Expenditure;


    @Column(name="revenue", columnDefinition = "INT(11)")
    private String  Revenue;


    @Column(name = "time_of_accounts", nullable = false, columnDefinition = "DATETIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timeOfaccounts;

    public Date getTimeOfaccounts() {
        return timeOfaccounts;
    }

    public void setTimeOfaccounts(Date timeOfaccounts) {
        this.timeOfaccounts = timeOfaccounts;
    }

    public Accounts() { }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpenditure() {
        return Expenditure;
    }

    public void setExpenditure(String expenditure) {
        Expenditure = expenditure;
    }

    public String getRevenue() {
        return Revenue;
    }

    public void setRevenue(String revenue) {
        Revenue = revenue;
    }
}

