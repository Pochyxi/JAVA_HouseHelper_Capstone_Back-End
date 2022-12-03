package com.adiener.java_househelper_capstone_backend.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "bollette")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Bolletta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Fornitura fornitura;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    private Long numero;

    private Double totale;

    private LocalDate emissione;

    private LocalDate periodoInizio;
    private LocalDate periodoFine;

    private LocalDate scadenza;

}
