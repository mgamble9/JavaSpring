package com.gamble.courseSignup.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.courseSignup.models.Course;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

	List<Course> findBySemesterContaining(String semester);

}
