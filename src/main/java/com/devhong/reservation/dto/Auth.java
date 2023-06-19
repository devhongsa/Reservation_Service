package com.devhong.reservation.dto;

import com.devhong.reservation.model.User;
import com.devhong.reservation.type.UserType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Auth {

    @Data
    public static class SignIn{
        private String username;
        private String password;
    }

    @Data
    public static class SignUp{
        @NotBlank
        private String username;

        @NotBlank
        @Length(min = 5)
        private String password;

        @NotBlank
        private String email;

        @NotBlank
        private String userType;

        @NotBlank
        private String mobileNumber;

        @NotEmpty
        private List<String> roles;

        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .userType(UserType.valueOf(userType))
                    .mobileNumber(mobileNumber)
                    .roles(roles)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SignUpResponse{
        private String username;
        private String email;
        private String mobileNumber;

        public static SignUpResponse fromEntity(User user) {
            return SignUpResponse.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .mobileNumber(user.getMobileNumber())
                    .build();
        }
    }
}
