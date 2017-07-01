package model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "_User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_UserSeq")
    @SequenceGenerator(name = "_UserSeq", sequenceName = "_UserSeq", allocationSize = 1)
    Integer id;
    @Basic
    String firstName;
    @Basic
    String lastName;
    @OneToMany(mappedBy = "user")
    List<Invoice> invoices;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }


    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
