package com.sidus.confluence.twikkir.action;

import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.opensymphony.util.TextUtils;
import com.opensymphony.webwork.ServletActionContext;
import com.sidus.confluence.twikkir.TwikkirManager;

public class ViewTwikkirListAction extends ConfluenceActionSupport
{
	private TwikkirManager twikkirManager;
	
	private SortedSet twikkirPosts;
	private String lastUpdate;
	private SortedSet leaders;
	private String username;
	
	public String execute() throws Exception
	{
		ServletActionContext.getResponse().setContentType("text/xml");
		Date lastUpdateTime;
		if(!TextUtils.stringSet(lastUpdate))
		{
			lastUpdateTime = new Date(System.currentTimeMillis());
		}
		else
		{
			lastUpdateTime = new Date(new Long(lastUpdate).longValue());
		}
		
		leaders = twikkirManager.getLeaders(username);
		for (Iterator iterator = leaders.iterator(); iterator.hasNext();)
		{
			String leader = (String) iterator.next();
			twikkirPosts.addAll(twikkirManager.getTwikkirs(leader, lastUpdateTime));
		}
		return SUCCESS;
	}

	public void setTwikkirManager(TwikkirManager twikkirManager)
	{
		this.twikkirManager = twikkirManager;
	}

	public SortedSet getTwikkirPosts()
	{
		return twikkirPosts;
	}

	public void setLastUpdate(String lastUpdate)
	{
		this.lastUpdate = lastUpdate;
	}
}
