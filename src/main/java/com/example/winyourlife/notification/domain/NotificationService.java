package com.example.winyourlife.notification.domain;

import com.example.winyourlife.infrastructure.exception.ApplicationEntityNotFoundException;
import com.example.winyourlife.notification.dto.FriendRequestCreate;
import com.example.winyourlife.notification.dto.FriendRequestResponse;
import com.example.winyourlife.notification.dto.NotificationResponse;
import com.example.winyourlife.userinfo.UserInfoService;
import java.util.List;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
record NotificationService(
        Authentication authentication,
        UserInfoService userInfoService,
        FriendRequestRepository friendRequestRepository,
        NotificationRepository notificationRepository)
        implements com.example.winyourlife.notification.NotificationService {

    @Override
    public void addFriendRequest(FriendRequestCreate friendRequest) throws ApplicationEntityNotFoundException {
        val user = (UserDetails) authentication.getPrincipal();
        val sender = userInfoService.getUserInfoByEmail(user.getUsername());
        val recipient = userInfoService.getUserInfoByEmail(friendRequest.emailRecipient());
        friendRequestRepository.save(
                FriendRequest.builder().sender(sender).receiver(recipient).build());
        val notification = Notification.builder()
                .type(NotificationType.FRIEND_REQUEST)
                .emailSender(user.getUsername())
                .emailRecipient(friendRequest.emailRecipient())
                .build();
        notificationRepository.save(notification);
    }

    @Override
    public void acceptFriendRequest(FriendRequestResponse friendRequestResponse) {
        val user = (UserDetails) authentication.getPrincipal();
        val friendRequest = friendRequestRepository
                .findById(friendRequestResponse.id())
                .orElseThrow(ApplicationEntityNotFoundException::new);

        userInfoService.addFriend(friendRequest.getSender(), friendRequest.getReceiver());
        val notification = Notification.builder()
                .type(NotificationType.FRIEND_REQUEST_ACCEPTED)
                .emailSender(user.getUsername())
                .emailRecipient(friendRequest.getSender().getEmail())
                .build();
        notificationRepository.save(notification);
        friendRequestRepository.delete(friendRequest);
    }

    @Override
    public void declineFriendRequest(FriendRequestResponse friendRequestResponse) {
        val user = (UserDetails) authentication.getPrincipal();
        val friendRequest = friendRequestRepository
                .findById(friendRequestResponse.id())
                .orElseThrow(ApplicationEntityNotFoundException::new);
        val notification = Notification.builder()
                .type(NotificationType.FRIEND_REQUEST_DECLINED)
                .emailSender(user.getUsername())
                .emailRecipient(friendRequest.getSender().getEmail())
                .build();
        notificationRepository.save(notification);
        friendRequestRepository.delete(friendRequest);
    }

    @Override
    public List<NotificationResponse> getNotifications() {
        val user = (UserDetails) authentication.getPrincipal();
        val notifications = notificationRepository.findAllByEmailRecipient(user.getUsername());
        val result = notifications.stream()
                .filter(notification ->
                        !notification.isRead() || notification.getType() == NotificationType.FRIEND_REQUEST)
                .map(notification -> new NotificationResponse(
                        notification.getType().name(), notification.getEmailSender(), notification.getUuid()))
                .toList();
        notifications.forEach(notification -> notification.setRead(true));
        return result;
    }
}
