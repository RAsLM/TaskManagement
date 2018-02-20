package com.rasl.repositories;

import com.rasl.pojo.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 19.02.2018.
 */

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
}
