package com.rasl.repositories;

import com.rasl.pojo.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 19.02.2018.
 */

public interface TaskRepository extends JpaRepository<Task, Integer> {
}