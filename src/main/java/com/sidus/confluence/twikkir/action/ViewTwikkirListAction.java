package com.sidus.confluence.twikkir.action;

import java.util.List;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.sidus.confluence.twikkir.TwikkirManager;

public class ViewTwikkirListAction extends ConfluenceActionSupport
{
	private TwikkirManager twikkirManager;
	
	private List twikkirList;
	
	public String execute() throws Exception
	{
		twikkirList = twikkirManager.getTwikkirList();
		return SUCCESS;
	}

	public void setTwikkirManager(TwikkirManager twikkirManager)
	{
		this.twikkirManager = twikkirManager;
	}

	public List getTwikkirList()
	{
		return twikkirList;
	}

	public void setTwikkirList(List twikkirList)
	{
		this.twikkirList = twikkirList;
	}
}
