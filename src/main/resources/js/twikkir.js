

var processingImage = new Image();
processingImage.src = "/download/resources/com.sidus.confluence.twikkir/img/processing.gif";
var restImage = new Image();
restImage.src = "/download/resources/com.sidus.confluence.twikkir/img/resting.gif";

var numleadertwikkirs = 0;
var maxPostLen = 140;

/*
 *************************************************************** Utility methods ****
 */
function createRequest()
{
	try
	{	
		request = new XMLHttpRequest();
	}
	catch (trymicrosoft)
	{
		try
		{
			request = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (othermicrosoft)
		{
			try
			{
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (failed)
			{
				request = null;
			}
		}
	}
	
	if (request == null)
	{
		alert("Error creating request object!");
	}
	
	return request;
}

function getCharsLeft()
{
	var twikkirpost = document.getElementById("twikkirpost")
	var charsTyped = twikkirpost.value.length;
	if(charsTyped >= maxPostLen + 1)
	{
		alert("Maximum character limit reached!");
		twikkirpost.value = twikkirpost.value.substring(0, maxPostLen);
	}
	else
	{
		var charsLeft = maxPostLen - document.getElementById("twikkirpost").value.length;
		document.getElementById("charsleft").innerHTML = charsLeft;
	}
}

function getTagContent(response, tagName)
{
	return response.getElementsByTagName(tagName)[0].firstChild.data
}

function getTagContents(response, tagName)
{
	var tagContents = response.getElementsByTagName(tagName);
	return tagContents;
}

/*
 *************************************************************** Twikkir methods ****
 */
function addTwikkirElement(fullname, username, post, date, profilePic)
{
	var twikkirlist = document.getElementById('leadertwikkirs');
	var newtwikkirdiv = document.createElement('div');
	var lasttwikkirdiv;
	if(numleadertwikkirs > 0)
	{
		var previoustwikkirid = numleadertwikkirs - 1;
		lasttwikkirdiv = document.getElementById('twikkir_div_'+(previoustwikkirid));
	}
	var divId = 'twikkir_div_'+numleadertwikkirs;
	newtwikkirdiv.setAttribute('id', divId);
	newtwikkirdiv.setAttribute('style', 'display: none');
	newtwikkirdiv.setAttribute('class', 'twikkirpost');
	newtwikkirdiv.innerHTML = '<table><tr><td valign="top"><img src="' + profilePic + '" width="48" height="48"/><td><td>' + post + '<br /><span class="smalltext">said</span> ' + fullname + ' <span class="smalltext">on '+ date + '</span>';
	if(lasttwikkirdiv == null)
	{
		twikkirlist.appendChild(newtwikkirdiv);
	}
	else
	{
		twikkirlist.insertBefore(newtwikkirdiv, lasttwikkirdiv);
	}
	numleadertwikkirs++;
	$jq('#' + divId).show("slow");
}

/*
 * Post a twikkir message
 */
function postit()
{
	var postStatusImg = document.getElementById("postStatusImg");
	postStatusImg.src = processingImage.src;
	var twikkir = document.getElementById("twikkirpost").value;
	var username = document.getElementById("currentUsername").value;
	var request = createRequest();
	var url = "/plugin/twikkir/postit.action";
	request.open("POST", url, true);
	request.onreadystatechange = function()
	{
		if(request.readyState == 4)
		{
			resp = request.responseXML;
			document.getElementById("poststatus").innerHTML = getTagContent(resp, "poststatus");
			postStatusImg.src = restImage.src;
			document.getElementById("twikkirpost").value = null;
			document.getElementById("charsleft").innerHTML = maxPostLen;
			$jq("#poststatus").fadeIn(2000).fadeOut(3000);
			
		}
	};
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send("twikkirPost="+escape(twikkir)+"&username="+escape(username));
}

/*
 *************************************************************** Follower/Leader Methods ****
 */
 
 function removeLeader(username, removeLeaderName, fullName, userPicPath, contextPath)
 {
 	var request = createRequest();
	var url = "/plugin/twikkir/removeleader.action";
	request.open("POST", url, true);
	request.onreadystatechange = function()
	{
		if(request.readyState == 4)
		{
			$jq("#leader_" + removeLeaderName).fadeOut(2000);
			var potential_leaders = document.getElementById('potential_leaders');
  			var newdiv = document.createElement('div');
  			var divId = "potential_leader_" + removeLeaderName;
  			newdiv.setAttribute('id', divId);
  			newdiv.setAttribute('style', 'display: none');
			newdiv.setAttribute('class', 'person');
  			newdiv.innerHTML = '<table><tr><td><img src="'+ userPicPath + '" width="48" height="48"/></td><td>' + fullName + '<br /><a class="addremovelink" href="javascript:addLeader(\'' + username + '\', \'' + removeLeaderName + '\', \'' + fullName + '\', \'' + userPicPath + '\', \'' + contextPath +'\');"><img width="24" height="24" title="Start listening to ' + fullName +'" src="' + contextPath + '/download/resources/com.sidus.confluence.twikkir/img/add.png"></a></td></tr></table>'
  			potential_leaders.appendChild(newdiv);
			$jq('#' + divId).show("slow");
		}
	};
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send("username="+escape(username)+"&removeLeader="+escape(removeLeaderName));
 }
 
 function addLeader(username, addLeaderName, fullName, userPicPath, contextPath)
 {
	var request = createRequest();
	var url = "/plugin/twikkir/addleader.action";
	request.open("POST", url, true);
	request.onreadystatechange = function()
	{
		if(request.readyState == 4)
		{
			$jq("#potential_leader_" + addLeaderName).fadeOut(2000);
			var leader_list = document.getElementById('leader_list');
  			var newdiv = document.createElement('div');
  			var divId = "leader_" + escape(addLeaderName);
  			newdiv.setAttribute('id', divId);
  			newdiv.setAttribute('style', 'display: none');
			newdiv.setAttribute('class', 'person');
  			newdiv.innerHTML = '<table><tr><td><img src="'+ userPicPath + '" width="48" height="48"/></td><td>' + fullName + '<br /><a class="addremovelink" href="javascript:removeLeader(\'' + username + '\', \'' + addLeaderName + '\', \'' + fullName + '\', \'' + userPicPath + '\', \'' + contextPath +'\');"><img width="24" height="24" title="Start listening to ' + fullName +'" src="' + contextPath + '/download/resources/com.sidus.confluence.twikkir/img/remove.png"></a></td></tr></table>'
  			leader_list.appendChild(newdiv);
			$jq('#' + divId).show("slow");
		}
	};
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send("newLeader="+escape(addLeaderName)+"&username="+escape(username));
}

function getLatestTwikkirs()
{
	var request = createRequest();
	var currentUsername = $jq("#currentUsername").val();
	var lastUpdateDate = $jq("#lastUpdateDate").val();
	var url = "/plugin/twikkir/getlatesttwikkirs.action";
	request.open("POST", url, true);
	request.onreadystatechange = function()
	{
		if(request.readyState == 4)
		{
			response = request.responseXML;
			if(response != null)
			{
				tagContents = getTagContents(response, 'twikkir');
				for (var i=0; i<tagContents.length; i++)
				{
					var username = tagContents[i].childNodes[1].firstChild.nodeValue;
					var post = tagContents[i].childNodes[3].firstChild.nodeValue;
					var date = tagContents[i].childNodes[5].firstChild.nodeValue;
					var profilePic = tagContents[i].childNodes[7].firstChild.nodeValue;
					var fullname = tagContents[i].childNodes[9].firstChild.nodeValue;
					addTwikkirElement(fullname, username, post, date, profilePic);
				}
				
				// In order to allow any newly 'followed' user messages appear
				// only set the time if we have displayed at least one twikkir
				// All twikkirs will be displayed on page reload anyway.
				var twikkirpost = document.getElementById("twikkir_div_0");
				if(twikkirpost != null)
				{
					document.getElementById('lastUpdateDate').value = new Date().getTime();			
				}
			}
		}
	};
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send("lastUpdateDate="+escape(lastUpdateDate)+"&username="+escape(currentUsername));
}


/*
 *************************************************************** Timers ****
 */
 
setInterval ( "getLatestTwikkirs()", 5000 );

