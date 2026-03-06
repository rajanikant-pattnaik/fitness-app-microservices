package com.fitness.activityservice.service;

import com.fitness.activityservice.ActivityRepositry;
import com.fitness.activityservice.dto.ActivityRequests;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepositry activityRepositry;
    private final UserValidationService userValidationService;
    public ActivityResponse trackActivity(ActivityRequests request) {

        Boolean flag=userValidationService.validateUser(request.getUserId());
        if(flag==false){
            throw new RuntimeException("Invalid User");
        }
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = activityRepositry.save(activity);
        return mapToResponse(savedActivity);
    }
    public List<ActivityResponse> getResponseByUserId(String userId){
        List<Activity> activities=activityRepositry.findByUserId(userId);

        List<ActivityResponse> activityResponses= new ArrayList<>();
        for (int i = 0; i < activities.size(); i++) {
           ActivityResponse newActivityResponse=mapToResponse(activities.get(i));
           activityResponses.add(newActivityResponse);
        }
//        return mapToResponse(activity);

        return activityResponses;
    }

    private ActivityResponse mapToResponse(Activity activity){
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
}
