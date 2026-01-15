package postech.unifiedeats.unified_eats_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "LoginRequest", description = "Dados necessários para autenticação do usuário")
public record LoginRequestDTO (

        @Schema(description = "Login do usuário", example = "joaosilva")
        @NotBlank(message = "Login é obrigatório")
        String login,

        @Schema(description = "Senha do usuário", example = "123456")
        @NotBlank(message = "Senha é obrigatória")
        String password
){
}
