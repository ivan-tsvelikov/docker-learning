package com.ivan.repository;

import com.ivan.entity.Fibonacci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FibRepository extends JpaRepository<Fibonacci, Integer> {

    @Query(value = "select ind from fib", nativeQuery = true)
    List<String> findAllIndexes();
}
