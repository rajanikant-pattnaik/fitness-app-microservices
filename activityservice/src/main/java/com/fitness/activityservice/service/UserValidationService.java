package com.fitness.activityservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class UserValidationService {
    private final WebClient userServiceWebClient;
    public boolean validateUser(String userid){
        try{
            return userServiceWebClient.get().uri("/api/users/{userid}/validate",userid).retrieve().bodyToMono(Boolean.class).block();
        }
        catch(WebClientResponseException e){
          e.printStackTrace();
        }
      return false;
    }
}
