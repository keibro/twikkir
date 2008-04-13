package com.sidus.confluence.twikkir.action;

import java.util.Date;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.sidus.confluence.twikkir.Twikkir;
import com.sidus.confluence.twikkir.TwikkirManager;

public class PostItAction extends ConfluenceActionSupport 
{
	private TwikkirManager twikkirManager;
	
	private String username;
	private String twikkirPost;
	
	public String execute() throws Exception
	{
		Twikkir twikkir = new Twikkir(username, twikkirPost, new Date());
		twikkirManager.postTwikkir(twikkir);
		return SUCCESS;
	}

	public void setTwikkirManager(TwikkirManager twikkirManager)
	{
		this.twikkirManager = twikkirManager;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getTwikkirPost()
	{
		return twikkirPost;
	}

	public void setTwikkirPost(String twikkirPost)
	{
		this.twikkirPost = twikkirPost;
	}
}
