package com.adiener.java_househelper_capstone_backend.RequestModels;

import com.adiener.java_househelper_capstone_backend.Entities.PostIt;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PostItRequest {

    private Long id;
    private String contenuto;
    private String scadenza;
    private Boolean stato;
    private Long userId;


}
