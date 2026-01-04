package postech.unifiedeats.unified_eats_api.dtos;

import postech.unifiedeats.unified_eats_api.enums.UserType;

public record LoginResponseDTO(String name, UserType userType) {
}
