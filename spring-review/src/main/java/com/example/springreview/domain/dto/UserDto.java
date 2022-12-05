package com.example.springreview.domain.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private String email;
}
