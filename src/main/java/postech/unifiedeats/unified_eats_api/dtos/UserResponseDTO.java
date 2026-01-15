package postech.unifiedeats.unified_eats_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import postech.unifiedeats.unified_eats_api.enums.UserType;

@Schema(name = "UserResponse", description = "Dados públicos do usuário retornados pela API (não inclui senha)")
public record UserResponseDTO(

        @Schema(description = "Nome completo do usuário", example = "João Silva")
        String name,

        @Schema(description = "E-mail do usuário", example = "joao@email.com")
        String email,

        @Schema(description = "Login do usuário", example = "joaosilva")
        String login,

        @Schema(description = "Endereço do usuário")
        AddressDTO addressDTO,

        @Schema(description = "Tipo do usuário: OWNER (dono) ou CUSTOMER (cliente)", example = "CUSTOMER")
        UserType type
) {
}
