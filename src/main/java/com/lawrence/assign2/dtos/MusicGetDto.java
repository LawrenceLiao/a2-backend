package com.lawrence.assign2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicGetDto {
    private String title;
    private String artist;
    private String year;
    private String imgUrl;
}
