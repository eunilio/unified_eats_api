package postech.unifiedeats.unified_eats_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import postech.unifiedeats.unified_eats_api.enums.UserType;

@Schema(name = "LoginResponse", description = "Dados retornados após autenticação bem-sucedida")
public record LoginResponseDTO(

        @Schema(description = "Nome completo do usuário", example = "João Silva")
        String name,

        @Schema(description = "Tipo do usuário (OWNER ou CUSTOMER)", example = "CUSTOMER")
        UserType userType) {
}
