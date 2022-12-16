package com.adiener.java_househelper_capstone_backend.RequestModels;


import com.adiener.java_househelper_capstone_backend.Entities.Prodotto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProdottoRequest {
    private Long id;
    private Long userId;
    private String nome;
    private Double prezzo;

    public static Prodotto requestForSave( ProdottoRequest prodottoRequest) {

        return Prodotto.builder()
                .nome( prodottoRequest.getNome() )
                .prezzo( prodottoRequest.getPrezzo() )
                .build();
    }

    public static Prodotto requestForUpdate( ProdottoRequest prodottoRequest, Prodotto prodotto) {
        return Prodotto.builder()
                .id( prodottoRequest.getId() )
                .nome( prodottoRequest.getNome() == null ? prodotto.getNome() : prodottoRequest.getNome() )
                .prezzo( prodottoRequest.getPrezzo() == null ? prodotto.getPrezzo() : prodottoRequest.getPrezzo() )
                .listeSpesa( prodotto.getListeSpesa() )
                .user( prodotto.getUser() )
                .build();
    }
}
