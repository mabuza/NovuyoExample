package dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import lombok.Data;
import utils.enums.EntityStatus;
import utils.utilities.GenerateKey;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;

@Data
public class CustomerDTO {
    private String id;
    private String firstNames;
    private String lastName;
    private String idNumber;
    private String mobileNumber;
    private String status;
    private OffsetDateTime dateCreated;

    public CustomerDTO(){}

    @JsonCreator
    public CustomerDTO(
            @JsonProperty("id") String id,
            @JsonProperty("firstNames") String firstNames,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("idNumber") String idNumber,
            @JsonProperty("mobileNumber") String mobileNumber,
            @JsonProperty("status") String status,
            @JsonProperty("dateCreated") OffsetDateTime dateCreated
    ){
        this.id = Optional.ofNullable(id).orElseGet(GenerateKey::generateEntityId);
        this.firstNames = firstNames;
        this.lastName = Preconditions.checkNotNull(lastName, "Last name mising");
        this.idNumber = idNumber;
        this.mobileNumber = Preconditions.checkNotNull(mobileNumber, "Mobile number missing");
        this.status = Optional.ofNullable(status).orElseGet(EntityStatus.PENDING_APPROVAL::name);
        this.dateCreated = Optional.ofNullable(dateCreated).orElseGet(OffsetDateTime::now);
    }
}
