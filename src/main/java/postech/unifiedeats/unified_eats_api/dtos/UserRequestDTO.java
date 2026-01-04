package postech.unifiedeats.unified_eats_api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import postech.unifiedeats.unified_eats_api.enums.UserType;

public record UserRequestDTO (

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 150, message = "Nome deve ter entre 3 e 150 caracteres")
        String name,

        @NotBlank(message = "Email é obrigatório")
        @Email(message="Email inválido")
        @Size(max = 150, message = "Email deve ter no máximo 150 caracteres")
        String email,

        @NotBlank(message = "Login é obrigatório")
        @Size(min = 4, max = 100, message = "Login deve ter entre 4 e 100 caracteres")
        String login,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password,

        @NotNull(message = "Endereço é obrigatório")
        @Valid
        AddressDTO addressDTO,

        @NotNull(message = "Tipo de usuário é obrigatório")
        UserType type
){
}
