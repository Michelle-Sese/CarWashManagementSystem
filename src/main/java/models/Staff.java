package models;

import javax.persistence.*;

@Entity
@Table(name="staff")
public class Staff{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="fname", columnDefinition = "VARCHAR(24)")
    private String fName;

    @Column(name="lname", columnDefinition = "VARCHAR(24)")
    private  String lName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dept_id", nullable = false, referencedColumnName = "id")
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
