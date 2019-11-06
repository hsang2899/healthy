package com.healthy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto implements DTOEntity  {
    @JsonProperty(value = "account_id")
    private Long accountId;

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "status")
    private String status;
}
