package com.sidus.confluence.twikkir;

import java.util.Date;

public class Twikkir implements Comparable
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

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Twikkir other = (Twikkir) obj;
		if (postDate == null)
		{
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		return true;
	}

	public int compareTo(Object o)
	{
		return this.getPostDate().compareTo( ((Twikkir) o).getPostDate() );
	}
	
	
}
