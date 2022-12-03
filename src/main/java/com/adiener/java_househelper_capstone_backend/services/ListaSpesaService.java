package com.adiener.java_househelper_capstone_backend.services;

import com.adiener.java_househelper_capstone_backend.Entities.ListaSpesa;
import com.adiener.java_househelper_capstone_backend.Entities.Prodotto;
import com.adiener.java_househelper_capstone_backend.RequestModels.ListaRequest;
import com.adiener.java_househelper_capstone_backend.repositories.ListaSpesaRepository;
import com.adiener.java_househelper_capstone_backend.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaSpesaService {
    @Autowired
    ListaSpesaRepository listaSpesaRepository;

    @Autowired
    ProdottoService prodottoService;

    @Autowired
    ProdottoRepository prodottoRepository;

    @Autowired
    UserService userService;


    public ListaSpesa getById( Long id ) throws Exception {
        Optional<ListaSpesa> prodotto = listaSpesaRepository.findById( id );
        if( prodotto.isEmpty() )
            throw new Exception( "Lista not available" );
        return prodotto.get();
    }

    public List<ListaSpesa> getAll() {

        return listaSpesaRepository.findAll();
    }

    public List<ListaSpesa> getListaSpesaByUserId( Long userId ) {
        return listaSpesaRepository.findListaSpesaByUserId( userId );
    }

    public void save( ListaSpesa lista ) {

        listaSpesaRepository.save( lista );
    }



    public ListaSpesa saveRequest( ListaRequest listaRequest ) throws Exception {
        ListaSpesa newList = ListaRequest.requestForSave( listaRequest );
        newList.setUser( userService.getById( listaRequest.getUserId() ) );

        return listaSpesaRepository.save( newList );
    }

    public void update( ListaSpesa lista ) {

        listaSpesaRepository.save( lista );
    }

    public void updateRequest( ListaRequest listaRequest ) {
        Optional<ListaSpesa> listaSpesa = listaSpesaRepository.findById( listaRequest.getId() );

        listaSpesa.ifPresent( list -> {
            ListaSpesa newList = ListaRequest.requestForUpdate( listaRequest, list );

            try {
                newList.setUser( listaRequest.getUserId() == 0 ? list.getUser() :
                        userService.getById( listaRequest.getUserId() ) );
            } catch( Exception e ) {
                System.out.println( e.getMessage() );
            }

            listaSpesaRepository.save( newList );
        } );
    }

    public void delete( Long id ) {
        Optional<ListaSpesa> lista = listaSpesaRepository.findById( id );
        lista.ifPresent( list -> listaSpesaRepository.delete( list ) );

    }

    public ListaSpesa addProduct( Long idLista, Long idProdotto ) throws Exception {
        Optional<ListaSpesa> listaSpesaF = listaSpesaRepository.findById( idLista );
        Prodotto prodottoF = prodottoService.getById( idProdotto );

        if( listaSpesaF.isPresent() && prodottoF != null ) {

            List<Prodotto> listProd = listaSpesaF.get().getProdotti();
            listProd.add( prodottoF );

            listaSpesaF.get().setProdotti( listProd );

            listaSpesaRepository.save( listaSpesaF.get() );

            return listaSpesaF.get();
        }
        return null;
    }


    public ListaSpesa deleteProduct( Long idLista, Long idProdotto ) throws Exception {
        Optional<ListaSpesa> listaSpesaF = listaSpesaRepository.findById( idLista );

        Prodotto prodottoF = prodottoService.getById( idProdotto );


        if( listaSpesaF.isPresent() && prodottoF != null ) {
            List<Prodotto> listProd = listaSpesaF.get().getProdotti();
            listProd.remove( prodottoF );

            listaSpesaF.get().setProdotti( listProd );


            try {
                save( listaSpesaF.get() );
            } catch( Exception e ) {
                System.out.println( e.getMessage() );
            }


            return listaSpesaF.get();
        }
        return null;
    }
}
