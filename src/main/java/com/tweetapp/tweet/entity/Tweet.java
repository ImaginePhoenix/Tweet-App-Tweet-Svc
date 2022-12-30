package com.tweetapp.tweet.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash("tweet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tweet {

	@Id
	private String id;
	
	private String email;

	@NotEmpty(message = "Please type some message !")
	@Size(max = 144)
	private String tweetBody;

	private int likes;

	private List<Tweet> replies;

	private Date date;

}
