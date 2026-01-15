package postech.unifiedeats.unified_eats_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import postech.unifiedeats.unified_eats_api.enums.UserType;

@Schema(name = "UserCreateRequest", description = "Dados necessários para criação de um usuário")
public record UserRequestDTO (

        @Schema(description = "Nome completo do usuário", example = "João Silva")
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 150, message = "Nome deve ter entre 3 e 150 caracteres")
        String name,

        @Schema(description = "Endereço de e-mail válido", example = "joao@email.com")
        @NotBlank(message = "Email é obrigatório")
        @Email(message="Email inválido")
        @Size(max = 150, message = "Email deve ter no máximo 150 caracteres")
        String email,

        @Schema(description = "Login único do usuário", example = "joaosilva")
        @NotBlank(message = "Login é obrigatório")
        @Size(min = 4, max = 100, message = "Login deve ter entre 4 e 100 caracteres")
        String login,

        @Schema(description = "Senha do usuário (mínimo 6 caracteres)", example = "123456")
        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password,

        @Schema(description = "Endereço do usuário")
        @NotNull(message = "Endereço é obrigatório")
        @Valid
        AddressDTO addressDTO,

        @Schema(description = "Tipo de usuário: OWNER (dono) ou CUSTOMER (cliente)", example = "CUSTOMER")
        @NotNull(message = "Tipo de usuário é obrigatório")
        UserType type
){
}
