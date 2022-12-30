//package com.tweetapp.tweet.service;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.tweetapp.tweet.entity.Tweet;
//import com.tweetapp.tweet.repository.TweetRepository;
//
//@SpringBootTest(classes = TweetService.class)
//@RunWith(SpringRunner.class)
//public class TweetServiceTest {
//	
//	@Autowired
//	private TweetService tweetService;
//	
//	@MockBean
//	private TweetRepository tweetRepository;
//
//	@Test
//	public void testGetAllTweets() {
//		Mockito.when(tweetRepository.findAll()).thenReturn(prepareTweetList());
//		tweetService.getAllTweets();
//	}
//	
//	@Test
//	public void testGetAllTweetsOfUser() {
//		Mockito.when(tweetRepository.findByEmail(Mockito.anyString())).thenReturn(prepareTweetList());
//		tweetService.getAllTweetsOfUser("abcd@gmail.com");
//	}
//	
//	@Test
//	public void testPostTweet() {
//		Mockito.when(tweetRepository.save(Mockito.any())).thenReturn(prepareTweet());
//		tweetService.postTweet(prepareTweet());
//	}
//	
//	@Test
//	public void testUpdateTweet() {
//		Optional<Tweet> optional = tweetRepository.findById("1");
//		Mockito.when(tweetRepository.findById("1")).thenReturn(Optional.empty());
//		Mockito.when(optional.get()).thenReturn(prepareTweet());
//		Mockito.when(tweetRepository.save(Mockito.any())).thenReturn(prepareTweet());
//		tweetService.updateTweet("1", "updateTweet");
//	}
//	
//	@Test
//	public void testDeleteTweet() {
//		Mockito.doNothing().when(tweetRepository).deleteById(Mockito.anyString());
//		tweetService.deleteTweet("1");
//	}
//	
//	
//	
//	private List<Tweet> prepareTweetList(){
//		
//		Tweet tweet = new Tweet();
//		List<Tweet> list = new ArrayList<>();
//		tweet.setId("1");
//		tweet.setEmail("abcd@gmail.com");
//		list.add(tweet);
//		return list;
//	}
//	
//	private Tweet prepareTweet(){
//		
//		Tweet tweet = new Tweet();
//		tweet.setId("1");
//		tweet.setDate(new Date());
//		tweet.setDescription("1234");
//		tweet.setEmail("abcd@gmail.com");
//		return tweet;
//	}
//
//}
