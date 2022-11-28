package com.adiener.java_househelper_capstone_backend.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
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

    String nome;



    @ManyToMany
    @JoinTable(name = "liste_spesa_prodotti",
            joinColumns = @JoinColumn(name = "lista_spesa_id"),
            inverseJoinColumns = @JoinColumn(name = "prodotti_id"))
    List<Prodotto> prodotti = new java.util.ArrayList<>();

}