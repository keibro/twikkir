package com.sidus.confluence.twikkir;

import java.util.List;

import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.setup.bandana.ConfluenceBandanaContext;

public class TwikkirManager
{
	private BandanaManager bandanManager;
	private List twikkirList;
	
	public void setBandanManager(BandanaManager bandanManager)
	{
		this.bandanManager = bandanManager;
	}
	
	public void postTwikkir(Twikkir twikkir)
	{
		bandanManager.setValue(new ConfluenceBandanaContext(), Constants.BANDANA_CTX_KEY_TWIKKIR, twikkir);
	}
	
	public List getTwikkirList()
	{
		return twikkirList;
	}
}
