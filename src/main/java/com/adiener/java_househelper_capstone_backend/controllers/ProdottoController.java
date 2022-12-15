package com.adiener.java_househelper_capstone_backend.controllers;

import com.adiener.java_househelper_capstone_backend.Entities.Prodotto;
import com.adiener.java_househelper_capstone_backend.RequestModels.ProdottoRequest;
import com.adiener.java_househelper_capstone_backend.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prodotto")
@CrossOrigin(origins = "*")
public class ProdottoController {
    @Autowired
    ProdottoService prodottoService;

    // GET ALL
    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Prodotto>> getAll() {
        return new ResponseEntity<>(prodottoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Prodotto>> getAllByUser(@PathVariable Long id) {
        return new ResponseEntity<>(prodottoService.getByUserId(id), HttpStatus.OK);
    }

    // GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Prodotto> get(@PathVariable("id") Long id ) throws Exception {

        return new ResponseEntity<>(
                prodottoService.getById( id ),
                HttpStatus.OK
        );
    }

    // CREATE
    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Prodotto> create( @RequestBody ProdottoRequest prodotto ) {



            return new ResponseEntity<>( prodottoService.save( prodotto ), HttpStatus.OK ) ;
    }

    //UPDATE
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void update( @RequestBody ProdottoRequest prodottoRequest ) {

        try {
            prodottoService.updateRequest( prodottoRequest );

        } catch( Exception e ) {

            System.out.println( e.getMessage() );

        }
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void deleteById(  @PathVariable Long id) {
            prodottoService.delete( id );
    }
}
