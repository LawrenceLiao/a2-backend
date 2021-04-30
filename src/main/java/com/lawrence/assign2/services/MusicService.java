package com.lawrence.assign2.services;

import com.lawrence.assign2.dtos.MusicGetDto;
import com.lawrence.assign2.entities.Music;
import com.lawrence.assign2.repositories.ArtistRepository;
import com.lawrence.assign2.repositories.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;

    public List<MusicGetDto> fetchListOfMusic(MusicGetDto musicGetDto) {
        List<Music> list = new ArrayList<>();
        if(musicGetDto.getTitle()!=null && musicGetDto.getArtist()==null && musicGetDto.getYear()==null){
            list = musicRepository.getListByTitle(musicGetDto);
        }else if (musicGetDto.getTitle()==null && musicGetDto.getArtist()!=null && musicGetDto.getYear()==null) {
            list = musicRepository.getListByArtist(musicGetDto);
        }else if (musicGetDto.getTitle()==null && musicGetDto.getArtist()==null && musicGetDto.getYear()!=null) {
            list = musicRepository.getListByIYear(musicGetDto);
        }else if (musicGetDto.getTitle()!=null && musicGetDto.getArtist()!=null && musicGetDto.getYear()!=null) {
            list = musicRepository.getListByInfo(musicGetDto);
        }
        List<MusicGetDto> listOfMusic = new ArrayList<>();
        for (Music music : list) {
            String imgUrl = artistRepository.getArtistByName(music.getArtist()).getImgUrl();
            MusicGetDto mDto = MusicGetDto.builder()
                    .title(music.getTitle())
                    .artist(music.getArtist())
                    .year(music.getYear())
                    .imgUrl(imgUrl)
                    .build();
            listOfMusic.add(mDto);
        }
        return listOfMusic;
    }
}
