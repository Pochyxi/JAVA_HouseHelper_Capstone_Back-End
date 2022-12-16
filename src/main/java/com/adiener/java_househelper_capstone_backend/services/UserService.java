package com.adiener.java_househelper_capstone_backend.services;


import com.adiener.java_househelper_capstone_backend.Entities.Role;
import com.adiener.java_househelper_capstone_backend.Entities.User;
import com.adiener.java_househelper_capstone_backend.RequestModels.UserRequest;
import com.adiener.java_househelper_capstone_backend.ResponseModels.UserResponse;
import com.adiener.java_househelper_capstone_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    // GET BY ID
    public User getById( Long id ) {
        return userRepository.findById( id ).isPresent() ? userRepository.findById( id ).get() : null;

    }

    // GET BY USERNAME
    public Optional<User> findByUsername( String username ) {

        return userRepository.findByUsername( username );
    }

    // GET BY USERNAME CONTAINS
    public List<User> findByUsernameContains( String username ) {
        return userRepository.getUserByUsernameContains( username );
    }

    // GET BY EMAIL
    public User getByEmail( String email ) {
        Optional<User> userF = userRepository.findUserByEmail( email );

        return userF.orElse( null );

    }

    // GET ALL
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // GET ALL PAGEABLE
    public Page<User> getAllPaginate( Pageable p ) {
        return userRepository.findAll( p );
    }

    // CREATE
    public User save( User u ) {
        String psw = u.getPassword();
        u.setPassword( encoder.encode( psw ) );
        return userRepository.save( u );
    }

    // CREATE AND SAVE
    public UserResponse createAndSave( UserRequest userRequest ) throws Exception {
        User u = new User();
        u.setNomeCompleto( userRequest.getNomeCompleto() );
        u.setEmail( userRequest.getEmail() );
        u.setUsername( userRequest.getUsername() );
        u.setPassword( encoder.encode( userRequest.getPassword() ) );

        Set<Role> roles = new HashSet<>();
        roles.add( roleService.getById( 1L ) );
        u.setRoles( roles );

        userRepository.save( u );
        return UserResponse.parseUser( u );
    }

    // UPDATE
    public void update( User u ) {
        userRepository.save( u );
    }

    // UPDATE AND SAVE
    public UserResponse updateResponse( UserRequest userRequest, Long id ) {
        Optional<User> userFind = userRepository.findById( id );

        if( userFind.isPresent() ) {
            User u = new User();
            u.setId( userFind.get().getId() );
            u.setNomeCompleto( userRequest.getNomeCompleto() == null ? userFind.get().getNomeCompleto()
                    : userRequest.getNomeCompleto() );
            u.setEmail( userRequest.getEmail() == null ? userFind.get().getEmail() : userRequest.getEmail() );
            u.setUsername( userRequest.getUsername() == null ? userFind.get().getUsername() :
                    userRequest.getUsername() );
            u.setPassword( userFind.get().getPassword() );
            u.setRoles( userFind.get().getRoles() );
            u.setActive( userFind.get().getActive() );
            u.setBollette( userFind.get().getBollette() );
            u.setListeSpesa( userFind.get().getListeSpesa() );
            u.setProdotti( userFind.get().getProdotti() );

            userRepository.save( u );
            return UserResponse.parseUser( userFind.get() );
        } else {
            return null;
        }


    }

    // DELETE
    public void delete( Long id ) throws Exception {
        Optional<User> u = userRepository.findById( id );
        if( u.isPresent() ) {
            userRepository.delete( u.get() );
        } else {
            throw new Exception( "Utente non trovato" );
        }
    }


}