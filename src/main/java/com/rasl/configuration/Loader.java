package com.rasl.configuration;

import com.rasl.pojo.Status;
import com.rasl.pojo.Tag;
import com.rasl.pojo.Task;
import com.rasl.services.StatusService;
import com.rasl.services.TagService;
import com.rasl.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadData();
    }

    public void loadData(){
        for (int i = 0; i < 10; i++) {
            tagService.save(new Tag("tag-" + i));
            statusService.save(new Status("status-"+i));
        }

        List<Tag> tags = tagService.list();
        List<Status> statuses = statusService.list();

        for (int i = 0; i < 10; i++) {
            int random = 0 + (int) (Math.random() * 9);
            taskService.save(new Task("Task-"+i, tags.get(random), statuses.get(random)));
        }

        List<Task> tasks = taskService.list();

        for (int i = 0; i < 10; i++) {
            int random = 0 + (int) (Math.random() * 9);
            taskService.save(new Task("Task-"+i, tags.get(random),tasks.get(random), statuses.get(random)));
        }
    }
}
