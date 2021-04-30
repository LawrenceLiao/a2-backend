package com.lawrence.assign2.controllers;

import com.lawrence.assign2.dtos.MusicGetDto;
import com.lawrence.assign2.services.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping("/music")
    public ResponseEntity queryMusic(@RequestBody MusicGetDto musicGetDto){
        List<MusicGetDto> listOfMusic = musicService.fetchListOfMusic(musicGetDto);
        if(listOfMusic.size() == 0) {
            return new ResponseEntity("No result is retrieved. Please query again！",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(listOfMusic, HttpStatus.OK);
    }
}
