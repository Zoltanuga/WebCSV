<%@ include file="include.jsp" %>
<html>
<head>
    <title><fmt:message key="main.title"/></title>
</head>
<body>
<p>

<h3><fmt:message key="main.header"/></h3>

<form action="Controller">
    <input type="submit" name="listPersons" value="go"/>
    <fmt:message key="main.menu1"/>
    <input type="hidden" name="command" value="main_list_persons"/>
</form>

<form action="Controller" method="post">
    <input type="submit" name="upload" value="go"/>
    <fmt:message key="main.menu2"/>
    <input type="hidden" name="command" value="main_upload"/>
</form>
</body>
</html>
