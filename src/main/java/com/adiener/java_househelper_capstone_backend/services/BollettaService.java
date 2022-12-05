package com.adiener.java_househelper_capstone_backend.services;

import com.adiener.java_househelper_capstone_backend.Entities.Bolletta;
import com.adiener.java_househelper_capstone_backend.RequestModels.BollettaRequest;
import com.adiener.java_househelper_capstone_backend.repositories.BollettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BollettaService {

    @Autowired
    BollettaRepository bollettaRepository;

    @Autowired
    UserService userService;

    public Bolletta getById( Long id ) throws Exception {
        Optional<Bolletta> bolletta = bollettaRepository.findById( id );

        if( bolletta.isEmpty() )
            throw new Exception( "Bolletta not available" );
        return bolletta.get();
    }

    public List<Bolletta> getAll() {

        return bollettaRepository.findAll();
    }

    public List<Bolletta> getBollettaByUserId( Long userId ) {
        return bollettaRepository.findBollettaByUserId( userId );
    }

    public void save( Bolletta bolletta ) {

        bollettaRepository.save( bolletta );
    }

    public Bolletta saveRequest( BollettaRequest request ) {

        System.out.println(request.getPeriodoFine());

        return bollettaRepository.save(
                Bolletta.builder()
                        .fornitura( request.getFornitura() )
                        .user( userService.getById( request.getUserId() ) )
                        .numero( request.getNumero() )
                        .totale( request.getTotale() )
                        .emissione( LocalDate.parse( request.getEmissione() ) )
                        .periodoInizio( LocalDate.parse( request.getPeriodoInizio() ) )
                        .periodoFine(LocalDate.parse( request.getPeriodoFine() ) )
                        .scadenza( LocalDate.parse( request.getScadenza() ) )
                        .build()
        );
    }

    public Bolletta update(Bolletta bolletta) {
       return bollettaRepository.save( bolletta );
    }

    public Bolletta updateRequest(BollettaRequest request) {
        Optional<Bolletta> bolletta = bollettaRepository.findById( request.getId() );

        if ( bolletta.isPresent() ) {
            Bolletta bollettaNew = Bolletta.builder()
                    .id( bolletta.get().getId() )
                    .fornitura( request.getFornitura() == null ? bolletta.get().getFornitura() : request.getFornitura() )
                    .user( request.getUserId() == null ? bolletta.get().getUser() : userService.getById( request.getUserId() ) )
                    .numero( request.getNumero() == null ?bolletta.get().getNumero() : request.getNumero() )
                    .totale( request.getTotale() == 0 ? bolletta.get().getTotale() : request.getTotale() )
                    .emissione( request.getEmissione() == null ? bolletta.get().getEmissione() : LocalDate.parse( request.getEmissione() ) )
                    .periodoInizio( request.getPeriodoFine() == null ? bolletta.get().getPeriodoFine() : LocalDate.parse( request.getPeriodoFine() ) )
                    .scadenza( request.getScadenza() == null ? bolletta.get().getScadenza() :
                            LocalDate.parse( request.getScadenza()) )
                    .build();

            return bollettaRepository.save( bollettaNew );
        }
        return null;
    }

    public void delete( Long id ) {
        Optional<Bolletta> lista = bollettaRepository.findById( id );
        lista.ifPresent( list -> bollettaRepository.delete( list ) );

    }

    //RICERCA BOLLETTE PER DATA DI EMISSIONE RANGE
    public List<Bolletta> getBollettaByEmissioneRange( String inizio, String fine, Long userId ) {
        return bollettaRepository.findBollettaByEmissioneRange( LocalDate.parse( inizio ), LocalDate.parse( fine ),
                userId );
    }

    // RICERCA BOLLETTE PER NUMERO FATTURA
    public List<Bolletta> getBollettaByNumeroFattura( Long numero, Long userId ) {
        return bollettaRepository.findBollettaByNumero( numero, userId );
    }

    // RICERCA BOLLETTE PER DATA DI SCADENZA MINORE DI DATA
    public List<Bolletta> getBollettaByDataScadenzaMinore(String dataInizio, String dataFine, Long userId ) {

        return bollettaRepository.findBollettaByScadenzaRange( LocalDate.parse( dataInizio ), LocalDate.parse( dataFine ),
                userId );
    }
}
