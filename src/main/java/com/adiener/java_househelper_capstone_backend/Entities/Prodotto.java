package com.adiener.java_househelper_capstone_backend.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prodotti")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;

    private Double prezzo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    @JoinTable(name = "liste_spesa_prodotti",
            joinColumns = @JoinColumn(name = "prodotti_id"),
            inverseJoinColumns = @JoinColumn(name = "lista_spesa_id"))
    @JsonBackReference
    private List<ListaSpesa> listeSpesa = new ArrayList<>();
}
