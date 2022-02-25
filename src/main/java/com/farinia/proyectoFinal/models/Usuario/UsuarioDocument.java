package com.farinia.proyectoFinal.models.Usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDocument {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
}
