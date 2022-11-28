package com.adiener.java_househelper_capstone_backend.services;

import com.adiener.java_househelper_capstone_backend.Entities.Prodotto;
import com.adiener.java_househelper_capstone_backend.RequestModels.ProdottoRequest;
import com.adiener.java_househelper_capstone_backend.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {
    @Autowired
    ProdottoRepository prodottoRepository;

    public Prodotto getById( Long id ) throws Exception {
        Optional<Prodotto> prodotto = prodottoRepository.findById( id );
        if( prodotto.isEmpty() )
            throw new Exception( "lista not available" );
        return prodotto.get();
    }

    public List<Prodotto> getAll() {

        return prodottoRepository.findAll();
    }

    public Prodotto save( ProdottoRequest prodotto ) {

        return prodottoRepository.save( ProdottoRequest.requestForSave( prodotto ) );
    }

    public void update( Prodotto prodotto ) {

        prodottoRepository.save( prodotto );
    }

    public void updateRequest( ProdottoRequest prodottoRequest ) {
        Optional<Prodotto> prodottoF = prodottoRepository.findById( prodottoRequest.getId());

        prodottoF.ifPresent( pro -> {prodottoRepository.save( ProdottoRequest.requestForUpdate( prodottoRequest,
                pro ) ); } );

    }

    public void delete( Long id ) {
        Optional<Prodotto> prodottoFind = prodottoRepository.findById( id );
        prodottoFind.ifPresent( prodotto -> prodottoRepository.delete( prodotto ) );

    }

}
