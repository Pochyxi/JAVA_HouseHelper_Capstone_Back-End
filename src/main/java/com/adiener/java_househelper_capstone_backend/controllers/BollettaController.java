package com.adiener.java_househelper_capstone_backend.controllers;

import com.adiener.java_househelper_capstone_backend.Entities.Bolletta;
import com.adiener.java_househelper_capstone_backend.Entities.ListaSpesa;
import com.adiener.java_househelper_capstone_backend.RequestModels.BollettaRequest;
import com.adiener.java_househelper_capstone_backend.RequestModels.ListaRequest;
import com.adiener.java_househelper_capstone_backend.services.BollettaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bolletta")
@CrossOrigin(origins = "*")
public class BollettaController {

    @Autowired
    BollettaService bollettaService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Bolletta>> getAll() {
        return new ResponseEntity<>( bollettaService.getAll(), HttpStatus.OK );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Bolletta> get( @PathVariable("id") Long id ) throws Exception {

        return new ResponseEntity<>(
                bollettaService.getById( id ),
                HttpStatus.OK
        );
    }

    @GetMapping("/userId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Bolletta>> getBollettaByUserId( @PathVariable("userId") Long userId ) throws Exception {

        return new ResponseEntity<>(
                bollettaService.getBollettaByUserId( userId ),
                HttpStatus.OK
        );
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Bolletta> create( @RequestBody BollettaRequest request ) {
        return new ResponseEntity<>( bollettaService.saveRequest( request ), HttpStatus.OK );
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Bolletta> update( @RequestBody BollettaRequest request ) {

        return new ResponseEntity<>( bollettaService.updateRequest( request ), HttpStatus.OK );
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void deleteById( @PathVariable Long id ) {
        bollettaService.delete( id );
    }


    // RICERCA BOLLETTE PER RANGE DI EMISSIONE PER USERID
    @GetMapping("/emissione-range/{inizio}/{fine}/userId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Bolletta>> getByEmissioneRange(@PathVariable("inizio") String inizio,
                                                              @PathVariable String fine,
                                                              @PathVariable("userId") Long userId) {

        return new ResponseEntity<>( bollettaService.getBollettaByEmissioneRange( inizio, fine, userId ), HttpStatus.OK);
    }

    // RICERCA BOLLETTE PER NUMERO FATTURA PER USER ID
    @GetMapping("/numero-fattura/{numero}/userId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Bolletta>> getByNumeroFattura(@PathVariable("numero") Long numero,
                                                             @PathVariable("userId") Long userId) {

        return new ResponseEntity<>( bollettaService.getBollettaByNumeroFattura( numero, userId ), HttpStatus.OK );
    }

    @GetMapping("/scadenza-maggiore/{dataInizio}/scadenza-minore/{dataFine}/userId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Bolletta>> getByScadenzaMinore(@PathVariable("dataInizio") String dataInizio,
                                                              @PathVariable("dataFine") String dataFine,
                                                              @PathVariable("userId") Long userId) {

        return new ResponseEntity<>( bollettaService.getBollettaByDataScadenzaMinore( dataInizio, dataFine, userId ),
                HttpStatus.OK);
    }
}
