package com.adiener.java_househelper_capstone_backend.controllers;


import com.adiener.java_househelper_capstone_backend.Entities.ListaSpesa;
import com.adiener.java_househelper_capstone_backend.RequestModels.ListaRequest;
import com.adiener.java_househelper_capstone_backend.services.ListaSpesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista")
@CrossOrigin(origins = "*")
public class ListaController {
    @Autowired
    ListaSpesaService listaSpesaService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ListaSpesa>> getAll() {
        return new ResponseEntity<>(listaSpesaService.getAll(), HttpStatus.OK);
    }

    // GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ListaSpesa> get(@PathVariable("id") Long id ) throws Exception {

        return new ResponseEntity<>(
                listaSpesaService.getById( id ),
                HttpStatus.OK
        );
    }

    @GetMapping("/userId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ListaSpesa>> getListaSpesaByUserId(@PathVariable("userId") Long userId) throws Exception {

        return new ResponseEntity<>(
                listaSpesaService.getListaSpesaByUserId( userId ),
                HttpStatus.OK
        );
    }
    // CREATE
    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ListaSpesa> create( @RequestBody ListaRequest listaRequest ) throws Exception {
        return new ResponseEntity<>( listaSpesaService.saveRequest( listaRequest ), HttpStatus.OK ) ;
    }

    //UPDATE
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void update( @RequestBody ListaRequest lista ) {

        try {
            listaSpesaService.updateRequest( lista );

        } catch( Exception e ) {

            System.out.println( e.getMessage() );

        }
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void deleteById(  @PathVariable Long id) {
        listaSpesaService.delete( id );
    }

    //ADD PRODUCT
    @PutMapping("/add/lista/{idLista}/prodotto/{idProdotto}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ListaSpesa> addProduct( @PathVariable("idLista") Long idLista,
                                                  @PathVariable("idProdotto") Long idProdotto ) {

        try {
            return new ResponseEntity<>( listaSpesaService.addProduct( idLista, idProdotto ), HttpStatus.OK);

        } catch( Exception e ) {

            System.out.println( e.getMessage() );

        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //DELETE PRODUCT
    @PutMapping("/delete/lista/{idLista}/prodotto/{idProdotto}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ListaSpesa> deleteProduct( @PathVariable("idLista") Long idLista,
                                                  @PathVariable("idProdotto") Long idProdotto ) {

        try {
            return new ResponseEntity<>( listaSpesaService.deleteProduct( idLista, idProdotto ), HttpStatus.OK);

        } catch( Exception e ) {

            System.out.println( e.getMessage() );

        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
