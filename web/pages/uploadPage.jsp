<%@ include file="include.jsp" %>

<html>
<head>
    <title><fmt:message key="uploadPage.title"/></title>
</head>
<body>
<p><fmt:message key="uploadPage.text"/></p>

<p>

<form action="Controller" enctype="multipart/form-data" method="post">
    <input type="file" name="fileI">
    <input type="submit" name="import" value="import"/>
    <input type="hidden" name="command" value="upload_file"/>
</form>

<p>

<form action="Controller" enctype="multipart/form-data" method="post">
    <input type="file" name="fileU">
    <input type="submit" name="update" value="update"/>
    <input type="hidden" name="command" value="update_file"/>
</form>
</body>
</html>
