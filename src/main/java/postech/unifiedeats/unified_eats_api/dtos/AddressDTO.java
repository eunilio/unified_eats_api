package postech.unifiedeats.unified_eats_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(name = "Address", description = "Endereço do usuário")
public record AddressDTO(

        @Schema(description = "CEP no formato 12345-678", example = "03300-000")
        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        String zipCode,

        @Schema(description = "Nome da rua, avenida ou logradouro", example = "Rua das Flores")
        @NotBlank(message = "Rua é obrigatória")
        @Size(min = 3, max = 150, message = "Rua deve ter entre 3 e 150 caracteres")
        String street,

        @Schema(description = "Número do endereço (ou 's/n')", example = "123")
        @NotBlank(message = "Número é obrigatório")
        @Size(max = 20, message = "Número deve ter no máximo 20 caracteres")
        String number,

        @Schema(description = "Complemento do endereço (opcional)", example = "Apto 12")
        @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
        String complement,

        @Schema(description = "Bairro", example = "Vila Carrão")
        @NotBlank(message = "Bairro é obrigatório")
        @Size(min = 2, max = 100, message = "Bairro deve ter entre 2 e 100 caracteres")
        String district,

        @Schema(description = "Cidade", example = "São Paulo")
        @NotBlank(message = "Cidade é obrigatória")
        @Size(min = 2, max = 100, message = "Cidade deve ter entre 2 e 100 caracteres")
        String city,

        @Schema(description = "Estado (UF)", example = "SP")
        @NotBlank(message = "Estado é obrigatória")
        @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
        String state
) {
}
