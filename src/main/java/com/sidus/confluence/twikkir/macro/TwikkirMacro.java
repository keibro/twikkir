package com.sidus.confluence.twikkir.macro;

import java.util.Map;

import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;
import com.atlassian.renderer.v2.RenderMode;

public class TwikkirMacro extends BaseMacro
{
	private static final String MACRO_TEMPLATE = "/templates/sidus/twikkir/twikkir.vm";
	
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
       	Map velocityContextMap = MacroUtils.defaultVelocityContext();
    	return VelocityUtils.getRenderedTemplate(MACRO_TEMPLATE, velocityContextMap);
    }
}