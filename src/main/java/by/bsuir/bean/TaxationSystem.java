package by.bsuir.bean;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "taxationSystem")
public class TaxationSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id")
    private int id;

    @Column(name = "type_name", unique=true)
    private String name;

    @Column(name = "percent", unique = true)
    private int percent;

    @Column(name = "min_profit", unique = true)
    private int minimalProfit;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taxationSystem", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Company> companies;
}
