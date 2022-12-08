package com.adiener.java_househelper_capstone_backend.services;

import com.adiener.java_househelper_capstone_backend.Entities.Bolletta;
import com.adiener.java_househelper_capstone_backend.Entities.ListaSpesa;
import com.adiener.java_househelper_capstone_backend.Entities.PostIt;
import com.adiener.java_househelper_capstone_backend.Entities.User;
import com.adiener.java_househelper_capstone_backend.RequestModels.PostItRequest;
import com.adiener.java_househelper_capstone_backend.repositories.PostItRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostItService {

    @Autowired
    PostItRepository postItRepository;

    @Autowired
    UserService userService;


    public PostIt getById( Long id ) throws Exception {
        Optional<PostIt> prodotto = postItRepository.findById( id );
        if( prodotto.isEmpty() )
            throw new Exception( "Lista not available" );
        return prodotto.get();
    }

    public List<PostIt> getAll() {

        return postItRepository.findAll();
    }

    public void save( PostIt postIt ) {

        postItRepository.save( postIt );
    }

    public PostIt requestSave( PostItRequest request ) throws Exception {
        User userF = userService.getById( request.getUserId() );

        PostIt newPostIt = PostIt.builder()
                .contenuto( request.getContenuto() )
                .scadenza( LocalDate.parse( request.getScadenza() ) )
                .stato( false )
                .user( userF )
                .build();

        return postItRepository.save( newPostIt );
    }

    public List<PostIt> getListaSpesaByUserId( Long userId ) {
        return postItRepository.findPostItByUserId( userId );
    }

    public void update( PostIt postIt ) {

        postItRepository.save( postIt );
    }

    public PostIt requestUpdate( PostItRequest request, Long id ) {
        Optional<PostIt> postItF = postItRepository.findById( id );

            if( postItF.isPresent()) {
                PostIt postItNew = PostIt.builder()
                        .id( postItF.get().getId() )
                        .contenuto( request.getContenuto() == null ? postItF.get().getContenuto() :
                                request.getContenuto() )
                        .scadenza( request.getScadenza() == null ? postItF.get().getScadenza() :
                                LocalDate.parse( request.getScadenza() ) )
                        .stato( request.getStato() )
                        .user( request.getUserId() == null ? postItF.get().getUser() :
                                userService.getById( request.getUserId() ) )
                        .build();

                save( postItNew );
                return postItNew;
            }
        return null;
    }

    public void delete( Long id ) {
        Optional<PostIt> postIt = postItRepository.findById( id );

        postIt.ifPresent( post -> postItRepository.delete( post ) );
    }

    public List<PostIt> getPostitByDataScadenzaMinore( String dataInizio, String dataFine, Long userId ) {

        return postItRepository.findPostItByScadenzaRange( LocalDate.parse( dataInizio ), LocalDate.parse( dataFine ),
                userId );
    }
}
