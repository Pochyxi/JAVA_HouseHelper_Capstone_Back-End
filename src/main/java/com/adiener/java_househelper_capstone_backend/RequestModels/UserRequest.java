package com.adiener.java_househelper_capstone_backend.RequestModels;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRequest {

    private String nomeCompleto;
    private String email;
    private String username;
    private String password;

}
