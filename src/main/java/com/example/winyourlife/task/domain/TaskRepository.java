package com.example.winyourlife.task.domain;

import com.example.winyourlife.userinfo.domain.UserInfo;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByUser(UserInfo user);
    //    List<Task> findAllByUserAndActiveTrue(UserInfo user);
}
