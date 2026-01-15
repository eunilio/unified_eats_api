package postech.unifiedeats.unified_eats_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "ChangePasswordRequest", description = "Dados necessários para troca de senha do usuário")
public record ChangePasswordDTO(

        @Schema(description = "Senha atual do usuário", example = "123456")
        @NotBlank(message = "Senha atual é obrigatória")
        String oldPassword,

        @Schema(description = "Nova senha do usuário (mínimo 6 caracteres)", example = "novaSenha123")
        @NotBlank(message = "Nova senha é obrigatória")
        @Size(min = 6, message = "Nova senha deve ter no mínimo 6 caracteres")
        String newPassword) {
}
