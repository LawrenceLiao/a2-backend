package com.lawrence.assign2.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostDto {
    private String email;
    private String username;
    private String password;
}
