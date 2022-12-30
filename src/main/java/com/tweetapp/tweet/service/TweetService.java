package com.tweetapp.tweet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweet.entity.Tweet;
import com.tweetapp.tweet.repository.TweetRepository;

@Service
public class TweetService {

	@Autowired
	private TweetRepository tweetRepository;

	public List<Tweet> getAllTweets() {
		return StreamSupport.stream(tweetRepository.findAll().spliterator(), false)
							.collect(Collectors.toList());
	}

	public List<Tweet> getAllTweetsOfUser(String email) {

		return StreamSupport.stream(tweetRepository.findAll().spliterator(), false)
				.filter(t -> t.getEmail().equals(email))
				.collect(Collectors.toList());
	}

	public void postTweet(Tweet tweet) {
		tweet.setDate(new Date());
		tweet.setReplies(new ArrayList<>());
		tweetRepository.save(tweet);
	}

	public void updateTweet(String id, String message) {

		Tweet tweet = tweetRepository.findById(id).get();
		tweet.setTweetBody(message);
		tweet.setDate(new Date());
		tweetRepository.save(tweet);

	}

	public boolean deleteTweet(String username, String id) {
		
		Optional<Tweet> tweet = tweetRepository.findById(id);
		if(tweet.isPresent() && tweet.get().getEmail().equals(username)) {
			tweetRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public void likeTweet(String id) {
		Tweet tweet = tweetRepository.findById(id).get();
		tweet.setLikes(tweet.getLikes() + 1);
		tweetRepository.save(tweet);
	}

	public void replyTweet(String userName, String id, Tweet reply) {
		reply.setDate(new Date());
		reply.setReplies(new ArrayList<>());
		reply.setEmail(userName);
		reply = tweetRepository.save(reply);
		Tweet tweet = tweetRepository.findById(id).get();
		List<Tweet> replies = tweet.getReplies();
		if (replies != null) {
			replies.add(reply);
		} else {
			replies = new ArrayList<>();
			replies.add(reply);
		}
		tweet.setReplies(replies);
		tweetRepository.save(tweet);
		tweetRepository.deleteById(reply.getId());

	}

}
