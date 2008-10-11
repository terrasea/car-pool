<DIV id="Menu" class="Menu">
	<p><a href="<%=response.encodeURL("welcome.jsp")%>"> <img class="logo" border="0" src="images/Car Pool 6 75.bmp" width="263" height="158"> </a></p> <br />

	<p>Log in via OpenId:</p>
	<FORM class="login" id="openid_identifier" name="openid_identifier" onsubmit="return yav.performCheck(openid_identifier, oid_login_rules, 'classic');" action="<%=response.encodeURL("openidlogin")%>" method="post">
		<INPUT type="hidden" name="openid_signin" value="true"/>
			<table class="login" border="0">
		   		<tr><td><img src="images/login-bg.gif" width="16" height="16"/>OpenId:</td><td><INPUT type="text" name="openid_url" id="openid_url" size="18"/></td></tr>
		   		<tr><td>&nbsp;</td><td><INPUT type="submit" value="Login"/></td></tr>
			</table>
	</FORM>
	<p>Want to know more about OpenId? <a href="http://openid.net/">Click here.</a></p><br /><br />
 
	<p>Or Log in via your username and password:</p>
	<form class="login" id="passwordlogin" name="passwordlogin" onsubmit="return yav.performCheck(passwordlogin, norm_login_rules, 'classic');" action="<%=response.encodeURL("login")%>" method="post">
		<input type="hidden" name="normal_signin" value="true">
		<table class="login">
			<tr><td>Username:</td><td><input type="text" id="username" name="username" size="18"/></td></tr>
			<tr><td>Password:</td><td><input type="password" id="userpass" name="userpass" size="18"/></td></tr>
			<tr><td>&nbsp;</td><td><input type="submit" value="Login"/></td></tr>
		</table>
	</form> <br />
	<p>Not registered and don't want to use OpenId, then <a href="<%=response.encodeURL("register.jsp")%>">register here</a>.</p>
</DIV>
