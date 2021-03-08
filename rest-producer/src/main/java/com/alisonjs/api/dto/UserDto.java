package com.alisonjs.api.dto;

import com.alisonjs.business.domain.types.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

	private Long id;

	private String email;

	private String username;

	@Getter(onMethod = @__({ @JsonIgnore }))
	@Setter(onMethod = @__({ @JsonProperty }))
	private String password;

	private UserRole role;

}
