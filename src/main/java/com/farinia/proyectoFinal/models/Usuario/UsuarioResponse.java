package com.farinia.proyectoFinal.models.Usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponse {
    private String username;
    private String email;
    private String token;
}
