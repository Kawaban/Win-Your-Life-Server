package com.example.winyourlife.notification.domain;

import com.example.winyourlife.notification.NotificationService;
import com.example.winyourlife.notification.dto.FriendRequestCreate;
import com.example.winyourlife.notification.dto.FriendRequestResponse;
import com.example.winyourlife.notification.dto.NotificationResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friend-request")
public record NotificationController(NotificationService notificationService) {

    @PostMapping("/send")
    void addFriendRequest(@Valid @RequestBody FriendRequestCreate friendRequest) {
        notificationService.addFriendRequest(friendRequest);
    }

    @PostMapping("/accept")
    void acceptFriendRequest(@Valid @RequestBody FriendRequestResponse friendRequestId) {
        notificationService.acceptFriendRequest(friendRequestId);
    }

    @PostMapping("/decline")
    void declineFriendRequest(@Valid @RequestBody FriendRequestResponse friendRequestId) {
        notificationService.declineFriendRequest(friendRequestId);
    }

    @GetMapping("/notifications")
    List<NotificationResponse> getNotifications() {
        return notificationService.getNotifications();
    }
}
