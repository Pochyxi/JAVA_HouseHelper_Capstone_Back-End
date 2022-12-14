package com.adiener.java_househelper_capstone_backend;

import ch.qos.logback.classic.Logger;
import com.adiener.java_househelper_capstone_backend.Entities.Role;
import com.adiener.java_househelper_capstone_backend.Entities.RoleType;
import com.adiener.java_househelper_capstone_backend.Entities.User;
import com.adiener.java_househelper_capstone_backend.services.RoleService;
import com.adiener.java_househelper_capstone_backend.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class JavaHouseHelperCapstoneBackEndApplication implements CommandLineRunner {
    private static final Logger logger
            = ( Logger ) LoggerFactory.getLogger( JavaHouseHelperCapstoneBackEndApplication.class );
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    public static void main( String[] args ) {
        SpringApplication.run( JavaHouseHelperCapstoneBackEndApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {
        logger.info( "CONTROLLO LA PRESENZA DEI RUOLI" );
        List<Role> roles = roleService.getAll();

        if( roles.size() == 0 ) {
            logger.info( "RUOLI NON TROVATI, LI CREO" );

            Role roleUser = new Role();
            Role roleAdmin = new Role();
            roleUser.setRoleType( RoleType.ROLE_USER );
            roleAdmin.setRoleType( RoleType.ROLE_ADMIN );

            roleService.save( roleUser );
            roleService.save( roleAdmin );

            List<Role> rolesCheckAgain = roleService.getAll();

            checkAdmin( rolesCheckAgain );

        } else {
            logger.info( "RUOLI TROVATI" );
            checkAdmin( roles );
        }

    }

    public void checkAdmin( List<Role> roles ) throws Exception {
        if( roles.size() > 0 ) {

            logger.info( "Eseguo controllo presenza account amministratore iniziale" );

            Optional<User> admin = userService.findByUsername( "admin" );

            if( admin.isPresent() ) {
                logger.info( "ACCOUNT ADMIN TROVATO: " );
                logger.info( "USERNAME: " + admin.get().getUsername() );
                logger.info( "Password: admin" );

                logger.warn( "Ricordarsi di contattare la rotta /auth/login per loggarsi con le credenziali" );

            } else {
                logger.error( "ACCOUNT ADMIN NON TROVATO" );
                logger.info( "INIZIALIZZO ACCOUNT ADMIN" );


                User newAdmin = new User();
                newAdmin.setNomeCompleto( "Admin" );
                newAdmin.setEmail( "admin@gmail.com" );
                newAdmin.setUsername( "admin" );
                newAdmin.setPassword( "admin" );

                Set<Role> rolesSet = new HashSet<>();
                rolesSet.add( roleService.getById( 1L ) );
                rolesSet.add( roleService.getById( 2L ) );
                newAdmin.setRoles( rolesSet );

                try {
                    userService.save( newAdmin );
                } catch( Exception e ) {
                    logger.error( e.getMessage() );
                }

                logger.info( "USERNAME: admin" );
                logger.info( "PASSWORD: admin" );
                logger.info( "Si consiglia in futuro di disattivare questo account e crearne uno personale" );
                logger.warn( "Ricordarsi di contattare la rotta /auth/login per loggarsi con le credenziali" );
            }
        }
    }

}
