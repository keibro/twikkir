package com.sidus.confluence.twikkir;

import java.util.Date;

public class Twikkir
{
	private String username;
	private String post;
	private Date postDate;
	
	public Twikkir(String username, String post, Date postDate)
	{
		this.username = username;
		this.post = post;
		this.postDate = postDate;
	}

	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPost()
	{
		return post;
	}
	
	public void setPost(String post)
	{
		this.post = post;
	}

	public Date getPostDate()
	{
		return postDate;
	}

	public void setPostDate(Date postDate)
	{
		this.postDate = postDate;
	}
}
