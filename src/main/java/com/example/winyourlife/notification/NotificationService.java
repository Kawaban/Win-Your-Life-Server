package com.example.winyourlife.notification;

import com.example.winyourlife.notification.dto.FriendRequestCreate;
import com.example.winyourlife.notification.dto.FriendRequestResponse;
import com.example.winyourlife.notification.dto.NotificationResponse;
import java.util.List;

public interface NotificationService {
    void addFriendRequest(FriendRequestCreate friendRequest);

    void acceptFriendRequest(FriendRequestResponse friendRequestResponse);

    void declineFriendRequest(FriendRequestResponse friendRequestResponse);

    List<NotificationResponse> getNotifications();
}
