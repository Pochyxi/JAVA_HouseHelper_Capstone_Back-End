package com.adiener.java_househelper_capstone_backend.RequestModels;


import com.adiener.java_househelper_capstone_backend.Entities.Fornitura;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BollettaRequest {

    private Long id;
    private Fornitura fornitura;
    private Long userId;
    private Long numero;
    private Double totale;

    private String emissione;
    private String periodoInizio;
    private String periodoFine;
    private String scadenza;

}
