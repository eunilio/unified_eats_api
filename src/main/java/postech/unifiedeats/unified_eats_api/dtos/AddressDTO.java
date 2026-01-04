package postech.unifiedeats.unified_eats_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(

        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        String zipCode,

        @NotBlank(message = "Rua é obrigatória")
        @Size(min = 3, max = 150, message = "Rua deve ter entre 3 e 150 caracteres")
        String street,

        @NotBlank(message = "Número é obrigatório")
        @Size(max = 20, message = "Número deve ter no máximo 20 caracteres")
        String number,

        @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
        String complement,

        @NotBlank(message = "Bairro é obrigatório")
        @Size(min = 2, max = 100, message = "Bairro deve ter entre 2 e 100 caracteres")
        String district,

        @NotBlank(message = "Cidade é obrigatória")
        @Size(min = 2, max = 100, message = "Cidade deve ter entre 2 e 100 caracteres")
        String city,

        @NotBlank(message = "Estado é obrigatória")
        @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
        String state
) {
}
