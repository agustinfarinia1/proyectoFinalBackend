package com.farinia.proyectoFinal.models.Usuario;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private String username;
    private String password;
    private String email;
}
