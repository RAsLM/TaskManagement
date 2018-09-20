package com.rasl.configuration;

import com.rasl.pojo.*;
import com.rasl.services.StatusService;
import com.rasl.services.TagService;
import com.rasl.services.TaskService;
import com.rasl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    TaskService taskService;

    @Autowired
    TagService tagService;

    @Autowired
    StatusService statusService;

    @Autowired
    UserService userService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadData();
    }

    public void loadData(){

        for (int i = 0; i < 3; i++) {
            userService.save(
                    User.builder()
                            .username("roos" + i)
                            .password(new BCryptPasswordEncoder().encode("password" + i))
                            .build());
        }

        List<User> users = userService.list();

        for (int i = 0; i < 10; i++) {
            int randomUser = 0 + (int) (Math.random() * 3);
            tagService.save(
                    Tag.builder()
                            .name("tag-" + i)
                            .user(users.get(randomUser))
                            .build()
            );
            statusService.save(
                    Status.builder()
                            .name("status-" + i)
                            .user(users.get(randomUser))
                            .build()
            );
        }

        List<Tag> tags = tagService.list();
        List<Status> statuses = statusService.list();

        List<Task> tasks = taskService.list();

        for (int i = 0; i < 100; i++) {
            int random = 0 + (int) (Math.random() * 9);
            int randomUser = 0 + (int) (Math.random() * 3);
            taskService.save(
                    Task.builder()
                            .name("Task-" + i)
                            .tag(tags.get(random))
                            .status(statuses.get(random))
                            .user(users.get(randomUser))
                            .build()
            );
        }
    }
}
