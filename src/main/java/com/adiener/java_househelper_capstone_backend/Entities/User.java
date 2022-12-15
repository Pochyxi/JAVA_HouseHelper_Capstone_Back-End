package com.adiener.java_househelper_capstone_backend.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    private String nomeCompleto;

    @Column(unique= true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListaSpesa> listeSpesa = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostIt> listaPostIt = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bolletta> bollette = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prodotto> prodotti = new ArrayList<>();



    @ManyToMany // PIU UTENTI POSSONO AVERE PIU RUOLI E VICEVERSA
    @JoinTable(name = "user_roles", //Nome della tabbella che verrà creata
            joinColumns = @JoinColumn(name = "user_id"),// Crea colonna
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<Role>();

    private Boolean active = true;

    public User( String nomeCompleto, String username, String password, String email ) {
        this.email = email;
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.password = password;
    }

    public void addRole( Role r ) {

        this.roles.add( r );

    }

}
