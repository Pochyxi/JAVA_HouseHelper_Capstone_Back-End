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

    @Autowired
    UserService userService;

    public Prodotto getById( Long id ) throws Exception {
        Optional<Prodotto> prodotto = prodottoRepository.findById( id );
        if( prodotto.isEmpty() )
            throw new Exception( "lista not available" );
        return prodotto.get();
    }

    public List<Prodotto> getByUserId(Long id) {
        return prodottoRepository.findProdottoByUserId( id );
    }

    public List<Prodotto> getAll() {

        return prodottoRepository.findAll();
    }

    public Prodotto save( ProdottoRequest prodotto ) {
        Prodotto prodottoNew = ProdottoRequest.requestForSave( prodotto );
        prodottoNew.setUser( userService.getById( prodotto.getUserId() ) );

        return prodottoRepository.save( prodottoNew );
    }

    public void update( Prodotto prodotto ) {

        prodottoRepository.save( prodotto );
    }

    public void updateRequest( ProdottoRequest prodottoRequest ) {
        Optional<Prodotto> prodottoF = prodottoRepository.findById( prodottoRequest.getId());

        prodottoF.ifPresent( pro -> {
            Prodotto prodottoNew = ProdottoRequest.requestForUpdate( prodottoRequest,
                    pro);
            prodottoNew.setUser( prodottoRequest.getUserId() == 0 ? pro.getUser() :
                    userService.getById( prodottoRequest.getUserId() ) );
            prodottoRepository.save( prodottoNew );
        } );

    }

    public void delete( Long id ) {
        Optional<Prodotto> prodottoFind = prodottoRepository.findById( id );
        prodottoFind.ifPresent( prodotto -> prodottoRepository.delete( prodotto ) );

    }

}
