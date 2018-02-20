package com.rasl.repositories;

import com.rasl.pojo.Statuses;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 20.02.2018.
 */
public interface StatusesRepository extends JpaRepository<Statuses, Integer> {
}
