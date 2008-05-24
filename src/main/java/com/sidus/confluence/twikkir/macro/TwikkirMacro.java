package com.sidus.confluence.twikkir.macro;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.renderer.PageContext;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;
import com.atlassian.renderer.v2.RenderMode;
import com.sidus.confluence.twikkir.TwikkirManager;

public class TwikkirMacro extends BaseMacro
{
	private static final String MACRO_TEMPLATE = "/templates/sidus/twikkir/twikkir.vm";
	
	private static final String PARAM_CURRENT_USERNAME = "currentUsername";
	private static final String PARAM_USERS = "users";
	private static final String PARAM_LEADERS = "leaders";
	
	private TwikkirManager twikkirManager;
	
	public boolean isInline()
	{
		return false;
	}

	public boolean hasBody()
	{
		return false;
	}

	public RenderMode getBodyRenderMode()
	{
		return RenderMode.NO_RENDER;
	}

    /**
     * This method returns XHTML to be displayed on the final page.
     */
    public String execute(Map params, String body, RenderContext renderContext) throws MacroException
    {
        String currentUsername = AuthenticatedUserThreadLocal.getUsername();
        
        PageContext pageContext = (PageContext) renderContext;
        Page page = (Page) pageContext.getEntity();
        String pageId = page.getIdAsString();

        SortedSet twikkirUsers = (SortedSet) twikkirManager.getTwikkirUsers();
        SortedSet leaders = twikkirManager.getLeaders(currentUsername);
        // Only show users who are not leaders already
        twikkirUsers.removeAll(leaders);
        
        SortedSet myRecentTwikkirs = twikkirManager.getTwikkirs(currentUsername);
        
        Map velocityContextMap = MacroUtils.defaultVelocityContext();
		velocityContextMap.put(PARAM_CURRENT_USERNAME, currentUsername);
		velocityContextMap.put(PARAM_USERS, twikkirUsers);
		velocityContextMap.put(PARAM_LEADERS, leaders);
		velocityContextMap.put("pageId", pageId);
		velocityContextMap.put("myrecenttwikkirs", myRecentTwikkirs);
    	return VelocityUtils.getRenderedTemplate(MACRO_TEMPLATE, velocityContextMap);
    }

	public void setTwikkirManager(TwikkirManager twikkirManager)
	{
		this.twikkirManager = twikkirManager;
	}
}