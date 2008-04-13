var request = null;

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
				request = nul;
			}
		}
	}
	
	if (request == null)
	{
		alert("Error creating request object!");
	}
}

function updateList()
{
	if(request.readyState == 4)
	{
		var list =  request.resonseText;
		document.getElementById("twikkirlist").innerHTML = list;
	}
}

function updateTwikkirList()
{
	createRequest();
	var url = "/plugin/twikkir/viewtwikkirlist.action";
	request.open("GET", url, true);
	request.onreadystatechange = updateList;
	request.send();
}

function postit()
{
	var twikkir = document.getElementById("twikkirpost").value;
	alert("Posting : " + twikkir);
	createRequest();
	var url = "/plugin/twikkir/postit.action";
	request.open("POST", url, true);
	request.onreadystatechange = updateTwikkirList;
	request.send(twikkir);
}

function getCharsLeft()
{
	var charsLeft = 140 - document.getElementById("twikkirpost").value.length;
	document.getElementById("charsleft").innerHTML = charsLeft;
}

setInterval ( "updateTwikkirList()", 10000 );