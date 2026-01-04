package postech.unifiedeats.unified_eats_api.dtos;

import postech.unifiedeats.unified_eats_api.enums.UserType;

public record UserResponseDTO(
        String name,
        String email,
        String login,
        AddressDTO addressDTO,
        UserType type
) {
}
