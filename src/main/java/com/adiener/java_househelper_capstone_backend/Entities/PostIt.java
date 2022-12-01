package com.adiener.java_househelper_capstone_backend.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "post_it")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostIt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String contenuto;

    private LocalDate scadenza;

    private Boolean stato = false;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    @JoinColumn(name = "user_user_id")
    @JsonManagedReference
    private User user;

}
