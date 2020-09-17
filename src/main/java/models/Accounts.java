package models;

import javax.persistence.*;


@Entity
    @Table(name="accounts")
    public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="month", columnDefinition = "VARCHAR(24)")
    private String Month;

    @Column(name="year", columnDefinition = "INT(11)")
    private String Year;

    @Column(name="expenditure", columnDefinition = "INT(11)")
    private String  Expenditure;


    @Column(name="revenue", columnDefinition = "INT(11)")
    private String  Revenue;
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

        public Accounts() { }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
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

