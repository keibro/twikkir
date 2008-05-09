package com.sidus.confluence.twikkir;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.setup.bandana.ConfluenceBandanaContext;
import com.opensymphony.util.TextUtils;
import com.thoughtworks.xstream.XStream;

public class TwikkirManager
{	
	private BandanaManager bandanaManager;
	private XStream xstream;
	
	public void clearAll()
	{
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_POSTS + ".admin", null);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_USERS, null);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_LEADERS + ".admin", null);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_FOLLOWERS + ".admin", null);
		
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_POSTS + ".test", null);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_LEADERS + ".test", null);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_FOLLOWERS + ".test", null);
	}
	
	public void postTwikkir(Twikkir twikkir)
	{
		String username = twikkir.getUsername();
		SortedSet twikkirSet = getTwikkirs(username);
		twikkirSet.add(twikkir);
		
		String xml = getXStream().toXML(twikkirSet);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_POSTS + "." + username, xml);
	}
	
	public SortedSet getTwikkirs(String username)
	{
		String xmlString = (String) bandanaManager.getValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_POSTS + "." + username);
		if(TextUtils.stringSet(xmlString))
		{
			return (TreeSet) getXStream().fromXML(xmlString);
		}
		else
		{
			return new TreeSet();
		}	
	}
	
	public SortedSet getTwikkirs(String username, Date date)
	{
		SortedSet posts = (TreeSet) getTwikkirs(username);
		Twikkir dateTwikkir = new Twikkir(null, null, date);
		posts = posts.tailSet(dateTwikkir);
		return posts;
	}

	public Set getTwikkirUsers()
	{
		String xmlString = (String) bandanaManager.getValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_USERS);
		if(TextUtils.stringSet(xmlString))
		{
			return (TreeSet) getXStream().fromXML(xmlString);
		}
		else
		{
			return new TreeSet();
		}
	}
	
	public void addTwikkirUser(String username)
	{
		Set userSet = getTwikkirUsers();
		userSet.add(username);
		String xml = getXStream().toXML(userSet);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_USERS, xml);
	}
	
	public TreeSet getLeaders(String username)
	{
		String xmlString = (String) bandanaManager.getValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_LEADERS + "." + username);
		if(TextUtils.stringSet(xmlString))
		{
			return (TreeSet) getXStream().fromXML(xmlString);
		}
		else
		{
			return new TreeSet();
		}
	}
	
	public Set addLeader(String username, String leader)
	{
		SortedSet leaderSet = (TreeSet) getLeaders(username);
		leaderSet.add(leader);
		String xml = getXStream().toXML(leaderSet);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_LEADERS + "." + username, xml);
		return leaderSet;
	}
	
	public Set addLeaders(String username, Set leaders)
	{
		SortedSet leaderSet = (TreeSet) getLeaders(username);
		leaderSet.addAll(leaders);
		String xml = getXStream().toXML(leaderSet);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_LEADERS + "." + username, xml);
		return leaderSet;
	}
	
	public Set removeLeader(String username, String removeLeader)
	{
		SortedSet leaderSet = (TreeSet) getLeaders(username);
		leaderSet.remove(removeLeader);
		String xml = getXStream().toXML(leaderSet);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_LEADERS + "." + username, xml);
		return leaderSet;
	}
	
	public TreeSet getFollowers(String username)
	{
		String xmlString = (String) bandanaManager.getValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_FOLLOWERS + "." + username);
		if(TextUtils.stringSet(xmlString))
		{
			return (TreeSet) getXStream().fromXML(xmlString);
		}
		else
		{
			return new TreeSet();
		}
	}
	
	public void addToFollowers(String username, Set followers)
	{
		SortedSet followerSet = (TreeSet) getFollowers(username);
		followerSet.addAll(followers);
		String xml = getXStream().toXML(followerSet);
		bandanaManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR_FOLLOWERS + "." + username, xml);
	}
	
	private XStream getXStream()
	{
		if(xstream == null)
		{
			xstream = new XStream();
			xstream.setClassLoader(getClass().getClassLoader());
		}
		
		return xstream;
	}
	
	public void setBandanaManager(BandanaManager bandanManager)
	{
		this.bandanaManager = bandanManager;
	}
}
