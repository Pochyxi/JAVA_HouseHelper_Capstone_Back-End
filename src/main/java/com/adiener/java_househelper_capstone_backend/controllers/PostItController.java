package com.adiener.java_househelper_capstone_backend.controllers;

import com.adiener.java_househelper_capstone_backend.Entities.Bolletta;
import com.adiener.java_househelper_capstone_backend.Entities.ListaSpesa;
import com.adiener.java_househelper_capstone_backend.Entities.PostIt;
import com.adiener.java_househelper_capstone_backend.RequestModels.ListaRequest;
import com.adiener.java_househelper_capstone_backend.RequestModels.PostItRequest;
import com.adiener.java_househelper_capstone_backend.services.PostItService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postit")
@CrossOrigin(origins = "*")
public class PostItController {

    @Autowired
    PostItService postItService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<PostIt>> getAll() {
        return new ResponseEntity<>(postItService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PostIt> get(@PathVariable("id") Long id ) throws Exception {

        return new ResponseEntity<>(
                postItService.getById( id ),
                HttpStatus.OK
        );
    }

    @GetMapping("/userId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<PostIt>> getPostItByUserId(@PathVariable("userId") Long userId) throws Exception {

        return new ResponseEntity<>(
                postItService.getListaSpesaByUserId( userId ),
                HttpStatus.OK
        );
    }

    @GetMapping("/scadenza-maggiore/{dataInizio}/scadenza-minore/{dataFine}/userId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<PostIt>> getByScadenzaMinore( @PathVariable("dataInizio") String dataInizio,
                                                               @PathVariable("dataFine") String dataFine,
                                                               @PathVariable("userId") Long userId) {

        return new ResponseEntity<>( postItService.getPostitByDataScadenzaMinore( dataInizio, dataFine, userId ),
                HttpStatus.OK);
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PostIt> create( @RequestBody PostItRequest postItRequest ) throws Exception {
        return new ResponseEntity<>( postItService.requestSave( postItRequest ), HttpStatus.OK ) ;
    }

    //UPDATE
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PostIt> update( @RequestBody PostItRequest postItRequest, @PathVariable Long id ) throws Exception {

            return new ResponseEntity<>( postItService.requestUpdate( postItRequest, id ), HttpStatus.OK );
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void deleteById(  @PathVariable Long id) {
        postItService.delete( id );
    }
}
