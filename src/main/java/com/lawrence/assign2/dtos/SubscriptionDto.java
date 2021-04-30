package com.lawrence.assign2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {
    private String title;
    private String artist;
    private String email;
    private String year;
    private String imgUrl;
}
