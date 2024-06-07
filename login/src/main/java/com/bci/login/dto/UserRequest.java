package com.bci.login.dto;

import com.bci.login.model.Phone;
import com.bci.login.validation.ValidEmail;
import com.bci.login.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String name;

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;

    private List<Phone> phones;

}
