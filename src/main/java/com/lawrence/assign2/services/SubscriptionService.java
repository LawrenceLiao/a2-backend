package com.lawrence.assign2.services;

import com.lawrence.assign2.dtos.SubscriptionDto;
import com.lawrence.assign2.entities.Subscription;
import com.lawrence.assign2.repositories.ArtistRepository;
import com.lawrence.assign2.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ArtistRepository artistRepository;

    public List<SubscriptionDto> fetchListOfSubscription(String email) {
        List<Subscription> list = subscriptionRepository.findListByEmail(email);
        List<SubscriptionDto> returnedList = new ArrayList<>();

        for(Subscription s : list) {
            String imgUrl = artistRepository.getArtistByName(s.getArtist()).getImgUrl();
            SubscriptionDto sDto = SubscriptionDto.builder()
                    .title(s.getTitle())
                    .artist(s.getArtist())
                    .year(s.getYear())
                    .imgUrl(imgUrl)
                    .build();
            returnedList.add(sDto);
        }
        return returnedList;
    }

    public void subscribe(SubscriptionDto subscriptionDto) {
        Subscription newSubscription = Subscription.builder()
                .title(subscriptionDto.getTitle())
                .artist(subscriptionDto.getArtist())
                .year(subscriptionDto.getYear())
                .email(subscriptionDto.getEmail())
                .build();
        subscriptionRepository.add(newSubscription);
    }

    public void unsubscribe(SubscriptionDto subscriptionDto) {
        Subscription deletedSubscription = Subscription.builder()
                .title(subscriptionDto.getTitle())
                .artist(subscriptionDto.getArtist())
                .year(subscriptionDto.getYear())
                .email(subscriptionDto.getEmail())
                .build();
        subscriptionRepository.delete(deletedSubscription);
    }
}
