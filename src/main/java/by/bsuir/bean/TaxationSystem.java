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
@Table(name = "type")
public class Type implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id")
    private int id;

    @Column(name = "type_name", unique=true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "type", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Swimmer> swimmers;
}
