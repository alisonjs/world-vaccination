package com.alisonjs.security.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserToken {

    private String token;

    private Long expireIn;

    private String tokenProvider;

}
