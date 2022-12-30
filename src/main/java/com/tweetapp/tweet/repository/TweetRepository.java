package com.tweetapp.tweet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.tweet.entity.Tweet;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, String> {

	public List<Tweet> findByEmail(String email);
}
