<%@ include file="include.jsp" %>
<html>
<head>
    <title><fmt:message key="errorPage.title"/></title>
</head>
<body>

<h2><fmt:message key="errorPage.header"/></h2>

<p><fmt:message key="errorPage.subheader"/></p>
<ul>
    <li><fmt:message key="errorPage.list1"/></li>

    <li><fmt:message key="errorPage.list2"/></li>

    <li><fmt:message key="errorPage.list3"/></li>

    <li><fmt:message key="errorPage.list4"/></li>
</ul>
<a href="Controller?command=main_upload">
    <h4><fmt:message key="errorPage.ref1"/></h4>
</a>
<a href="http://localhost:8080/api/">
    <h4><fmt:message key="errorPage.ref2"/></h4>
</a>
</body>
</html>
