package models;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name="getAllCustomers", query="SELECT c FROM Customer c WHERE c.status <> :status"),
        @NamedQuery(name="getCustomerByFirstName", query="SELECT c FROM Customer c WHERE c.firstNames = :firstNames AND c.status <> :status"),
        @NamedQuery(name="getCustomerByLastName", query="SELECT c FROM Customer c WHERE c.lastName LIKE:lastName AND c.status <> :status"),
        @NamedQuery(name="getCustomerByStatus", query="SELECT c FROM Customer c WHERE c.status = :status")
})
@Table(name = "customer", indexes = {
        @Index(name = "i_customer_firstname", columnList = "firstNames"),
        @Index(name = "i_customer_lastname", columnList = "lastName"),
        @Index(name = "i_customer_status", columnList = "status"),
        @Index(name = "i_customer_idnumber", columnList = "idNumber")
})
@Data
public class Customer {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 30)
    private String id;
    @Column(length = 50)
    private String firstNames;
    @Column(length = 50)
    private String lastName;
    @Column(length = 30)
    private String idNumber;
    @Column(length = 30)
    private String mobileNumber;
    @Column(length = 60)
    private String status;
    @Column
    private OffsetDateTime dateCreated;
    @Version
    @Column
    private long version;
}
