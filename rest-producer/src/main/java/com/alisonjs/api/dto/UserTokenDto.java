package com.alisonjs.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserTokenDto implements Serializable {

    private String token;

    @JsonProperty("expire_in")
    private Long expireIn;

    @JsonProperty("token_provider")
    private String tokenProvider;
}
