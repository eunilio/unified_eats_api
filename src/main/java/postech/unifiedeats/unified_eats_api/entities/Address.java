package postech.unifiedeats.unified_eats_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
    private String zipCode;        // cep
    private String street;         // logradouro
    private String number;         // numero
    private String complement;     // complemento
    private String district;       // bairro
    private String city;           // cidade
    private String state;          // uf
}
