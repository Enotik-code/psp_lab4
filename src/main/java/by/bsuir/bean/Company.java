package by.bsuir.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tax_amount")
    private int taxAmount;

    @Column(name = "profit")
    private int profit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    private TaxationSystem taxationSystem;

    public Company(String name, City city, TaxationSystem taxationSystem, int profit, int taxAmount) {
        this.name = name;
        this.taxAmount = taxAmount;
        this.city = city;
        this.taxationSystem = taxationSystem;
        this.profit = profit;
    }

}
