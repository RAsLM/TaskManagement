package com.rasl.repositories;

import com.rasl.pojo.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 20.02.2018.
 */
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
