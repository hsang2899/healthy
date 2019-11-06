package com.healthy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileCreateDto implements DTOEntity {

  @JsonProperty(value = "full_name")
  private String fullName;

  @JsonProperty(value = "gender")
  private String gender;

  @JsonProperty(value = "date_of_birth")
  private Long dateOfBirth;

  @JsonProperty(value = "phone")
  private String phone;

  @JsonProperty(value = "weight")
  private Float weight;

  @JsonProperty(value = "height")
  private Float height;

  @JsonProperty(value = "avatar_url")
  private String avatarUrl;
}
