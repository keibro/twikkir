package com.sidus.confluence.twikkir.action;

import java.util.Date;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.opensymphony.webwork.ServletActionContext;
import com.sidus.confluence.twikkir.Twikkir;
import com.sidus.confluence.twikkir.TwikkirManager;

public class PostItAction extends ConfluenceActionSupport 
{
	private TwikkirManager twikkirManager;
	
	private String username;
	private String twikkirPost;
	private String postStatus;
	
	public String execute() throws Exception
	{
		ServletActionContext.getResponse().setContentType("text/xml");
		Twikkir twikkir = new Twikkir(username, twikkirPost, new Date());
		twikkirManager.postTwikkir(twikkir);
		twikkirManager.addTwikkirUser(username);
		postStatus = "Posted";
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

	public String getPostStatus()
	{
		return postStatus;
	}

	public void setPostStatus(String postStatus)
	{
		this.postStatus = postStatus;
	}
}
