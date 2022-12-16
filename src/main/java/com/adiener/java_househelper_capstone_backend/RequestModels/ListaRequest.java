package com.adiener.java_househelper_capstone_backend.RequestModels;

import com.adiener.java_househelper_capstone_backend.Entities.ListaSpesa;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ListaRequest {
    private Long id;
    private String nome;
    private Long userId;
    private LocalDate dataCreazione;

    public static ListaSpesa requestForSave(ListaRequest listaRequest) {
        return ListaSpesa.builder()
                .nome( listaRequest.getNome() )
                .dataCreazione( LocalDate.now() )
                .build();
    }

    public static ListaSpesa requestForUpdate(ListaRequest listaRequest, ListaSpesa listaSpesa) {
        return ListaSpesa.builder()
                .id( listaSpesa.getId() )
                .nome( listaRequest.getNome() == null ? listaSpesa.getNome() : listaRequest.getNome())
                .prodotti( listaSpesa.getProdotti() )
                .build();
    }




}
