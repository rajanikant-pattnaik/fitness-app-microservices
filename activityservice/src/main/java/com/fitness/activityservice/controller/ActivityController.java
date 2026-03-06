package com.fitness.activityservice.controller;

import com.fitness.activityservice.dto.ActivityRequests;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@AllArgsConstructor
public class ActivityController {

    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse>trackActivity(@RequestBody ActivityRequests request){

        return ResponseEntity.ok(activityService.trackActivity(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ActivityResponse>>getDataforUserId(@PathVariable String userId){
        return ResponseEntity.ok(activityService.getResponseByUserId(userId));
    }
}
