package postech.unifiedeats.unified_eats_api.mappers;

import org.springframework.stereotype.Component;
import postech.unifiedeats.unified_eats_api.dtos.AddressDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserResponseDTO;
import postech.unifiedeats.unified_eats_api.entities.Address;
import postech.unifiedeats.unified_eats_api.entities.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO userRequestDTO) {
        var user = new User();
        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());
        user.setLogin(userRequestDTO.login());
        user.setPassword(userRequestDTO.password());
        user.setLastUpdated(LocalDateTime.now());
        user.setType(userRequestDTO.type());

        var address = new Address();
        address.setZipCode(userRequestDTO.addressDTO().zipCode());
        address.setStreet(userRequestDTO.addressDTO().street());
        address.setNumber(userRequestDTO.addressDTO().number());
        address.setComplement(userRequestDTO.addressDTO().complement());
        address.setDistrict(userRequestDTO.addressDTO().district());
        address.setCity(userRequestDTO.addressDTO().city());
        address.setState(userRequestDTO.addressDTO().state());

        user.setAddress(address);

        return user;
    }

    public UserResponseDTO toResponse(User user) {
        AddressDTO addressDTO = new AddressDTO(user.getAddress().getZipCode(), user.getAddress().getStreet(), user.getAddress().getNumber(), user.getAddress().getComplement(), user.getAddress().getDistrict(), user.getAddress().getCity(), user.getAddress().getState());

        return new UserResponseDTO(user.getName(), user.getEmail(), user.getLogin(), addressDTO, user.getType());
    }

    public List<UserResponseDTO> toResponseList(List<User> users) {
        return users.stream().map(this::toResponse).toList();
    }
}
