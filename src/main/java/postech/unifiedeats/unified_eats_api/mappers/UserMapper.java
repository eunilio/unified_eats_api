package postech.unifiedeats.unified_eats_api.mappers;

import org.springframework.stereotype.Component;
import postech.unifiedeats.unified_eats_api.dtos.AddressDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserResponseDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserUpdateRequestDTO;
import postech.unifiedeats.unified_eats_api.entities.Address;
import postech.unifiedeats.unified_eats_api.entities.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO userRequestDTO, LocalDateTime localDateTime) {
        var user = new User();
        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());
        user.setLogin(userRequestDTO.login());
        user.setPassword(userRequestDTO.password());
        user.setLastUpdated(localDateTime);
        user.setType(userRequestDTO.type());

        user.setAddress(toAddressEntity(userRequestDTO.addressDTO(), null));

        return user;
    }

    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(user.getName(), user.getEmail(), user.getLogin(), toAddressDTO(user.getAddress()), user.getType());
    }

    public List<UserResponseDTO> toResponseList(List<User> users) {
        return users.stream().map(this::toResponse).toList();
    }

    public User toUpdateEntity(UserUpdateRequestDTO dto, User user, LocalDateTime localDateTime) {
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setLogin(dto.login());
        user.setLastUpdated(localDateTime);
        user.setType(dto.type());

        user.setAddress(toAddressEntity(dto.addressDTO(), user.getAddress()));

        return user;
    }

    private Address toAddressEntity(AddressDTO dto, Address address) {
        if (address == null) address = new Address();

        address.setZipCode(dto.zipCode());
        address.setStreet(dto.street());
        address.setNumber(dto.number());
        address.setComplement(dto.complement());
        address.setDistrict(dto.district());
        address.setCity(dto.city());
        address.setState(dto.state());

        return address;
    }

    private AddressDTO toAddressDTO(Address address) {
        return new AddressDTO(address.getZipCode(), address.getStreet(), address.getNumber(), address.getComplement(), address.getDistrict(), address.getCity(), address.getState());
    }
}
