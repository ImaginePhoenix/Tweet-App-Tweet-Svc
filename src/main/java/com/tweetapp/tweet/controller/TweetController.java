package com.tweetapp.tweet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweet.entity.Tweet;
import com.tweetapp.tweet.service.TweetService;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/tweets")
@Slf4j
public class TweetController {

	@Autowired
	private TweetService tweetService;

	@GetMapping("/all")
	public ResponseEntity<List<Tweet>> getAllTweets() {
		
		log.info("GET_ALL_TWEETS called");
		return new ResponseEntity<List<Tweet>>(tweetService.getAllTweets(), HttpStatus.OK);
	}

	@GetMapping("/{username}")
	public ResponseEntity<List<Tweet>> getAllTweetsByUserName(@PathVariable String username) {

		log.info("GET_ALL_TWEETS_BY_USERNAME called");
		return new ResponseEntity<List<Tweet>>(tweetService.getAllTweetsOfUser(username), HttpStatus.OK);
	}

	@PostMapping("/{username}/add")
	public ResponseEntity<String> postTweet(@PathVariable("username") String username, @Valid @RequestBody Tweet tweet) {
		log.info(username+" posted a tweet.");
		tweet.setEmail(username);
		tweetService.postTweet(tweet);
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}

	@PutMapping("/{username}/update/{id}")
	public void updateTweet(@PathVariable(name = "username") String username, @PathVariable(name = "id") String id,
			@RequestBody Tweet tweet) {
		log.info(username+" updated the tweet with id: "+id);
		tweetService.updateTweet(id, tweet.getTweetBody());
	}

	@DeleteMapping("/{username}/delete/{id}")
	public ResponseEntity<String> deleteTweet(@PathVariable(name = "username") String username, @PathVariable(name = "id") String id) {
		log.info(username+" deleted the tweet with id: "+id);
		if(tweetService.deleteTweet(username, id)) {
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Failed",HttpStatus.FORBIDDEN);
	}

	@PutMapping("/{username}/like/{id}")
	public void likeTweet(@PathVariable(name = "username") String username, @PathVariable(name = "id") String id) {
		log.info(username+" liked the tweet with id: "+id);
		tweetService.likeTweet(id);
	}

	@PostMapping("/{username}/reply/{id}")
	public void replyTweet(@PathVariable(name="username") String username,@PathVariable(name = "id") String id, @Valid @RequestBody Tweet tweet) {
		log.info(username+" replied to the tweet with id: "+id);
		tweetService.replyTweet(username, id, tweet);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
