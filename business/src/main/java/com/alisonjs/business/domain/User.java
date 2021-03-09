package com.alisonjs.business.domain;

import com.alisonjs.business.domain.types.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {

	private long version;

	private Long id;

	private String email;

	private String username;

	private String password;

	private UserRole role;

}
