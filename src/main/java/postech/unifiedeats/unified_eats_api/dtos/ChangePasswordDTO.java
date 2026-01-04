package postech.unifiedeats.unified_eats_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordDTO(

        @NotBlank(message = "Senha atual é obrigatória")
        String oldPassword,

        @NotBlank(message = "Nova senha é obrigatória")
        @Size(min=6, message = "Nova senha deve ter no mínimo 6 caracteres")
        String newPassword) {
}
