package com.adiener.java_househelper_capstone_backend.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "liste_spesa")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ListaSpesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;

    private LocalDate dataCreazione;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    @JoinTable(name = "liste_spesa_prodotti",
            joinColumns = @JoinColumn(name = "lista_spesa_id"),
            inverseJoinColumns = @JoinColumn(name = "prodotti_id"))
    private List<Prodotto> prodotti = new java.util.ArrayList<>();

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    @JoinColumn(name = "user_user_id")
    @JsonManagedReference
    private User user;


}
