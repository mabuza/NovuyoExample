package mapper;

import dtos.CustomerDTO;
import models.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class DTOMapper {
    private static ModelMapper mapper;

    public static ModelMapper getMapper() {
        if (mapper == null) {
            mapper = new ModelMapper();
            mapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT);
        }
        return mapper;
    }

    public static CustomerDTO toCustomerDTO(Customer customer) {
        if(customer == null) return null;
        return getMapper().map(customer, CustomerDTO.class);
    }

    public static Customer toCustomerEntity(CustomerDTO dto){
        return getMapper().map(dto, Customer.class);
    }
}
