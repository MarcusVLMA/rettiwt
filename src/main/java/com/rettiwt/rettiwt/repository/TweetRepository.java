package com.rettiwt.rettiwt.repository;

import com.rettiwt.rettiwt.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long>{

}
