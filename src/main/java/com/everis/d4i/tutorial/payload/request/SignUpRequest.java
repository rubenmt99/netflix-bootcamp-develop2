package com.everis.d4i.tutorial.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
