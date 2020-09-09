package models;

import javax.persistence.*;

@Entity
@Table(name="expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private String amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id", nullable = false, referencedColumnName = "id")
    private ExpenseType expense;

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

    public ExpenseType getExpense() {
        return expense;
    }

    public void setExpense(ExpenseType expense) {
        this.expense = expense;
    }
}
