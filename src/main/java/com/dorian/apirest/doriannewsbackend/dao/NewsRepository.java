package com.dorian.apirest.doriannewsbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dorian.apirest.doriannewsbackend.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

}
