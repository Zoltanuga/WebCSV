<%@ include file="include.jsp" %>

<html>
<head>
    <title><fmt:message key="listPersonsPage.title"/></title>
</head>
<body>
<a href="http://localhost:8080/api/">
    <h4><fmt:message key="listPersonsPage.ref"/></h4></a>

<form action="Controller" method="post">
    <p><select name="sort">
        <option value="sort_by_name"><fmt:message key="listPersonsPage.sort.option1"/></option>
        <option value="sort_by_surname"><fmt:message key="listPersonsPage.sort.option2"/></option>
        <option value="sort_by_login"><fmt:message key="listPersonsPage.sort.option3"/></option>
        <option value="sort_by_email"><fmt:message key="listPersonsPage.sort.option4"/></option>
        <option value="sort_by_phone_number"><fmt:message key="listPersonsPage.sort.option5"/></option>
    </select>
        <input type="hidden" name="command" value="sort_persons"/>
        <input type="submit" value="sort"></p>
</form>
<table border="1" width="100%" cellpadding="0" cellspacing="2" bgcolor="#f0f8ff">
    <tr>
        <th colspan=5><fmt:message key="listPersonsPage.table.header"/></th>
    </tr>
    <tr>
        <th width="20%"><fmt:message key="listPersonsPage.table.column1"/></th>
        <th width="20%"><fmt:message key="listPersonsPage.table.column2"/></th>
        <th width="20%"><fmt:message key="listPersonsPage.table.column3"/></th>
        <th width="20%"><fmt:message key="listPersonsPage.table.column4"/></th>
        <th width="20%"><fmt:message key="listPersonsPage.table.column5"/></th>
    </tr>
    <tr>
        <c:forEach items="${requestScope.personsSubList}" var="person">
    <tr align="top-left">
        <td>${person.name}</td>
        <td>${person.surname}</td>
        <td>${person.login}</td>
        <td>${person.email}</td>
        <td>${person.phoneNumber}</td>
    </tr>
    </c:forEach>
</table>
<i><fmt:message key="listPersonsPage.text1"/> <c:out value="${pageNumber}"/></i>

<form action="Controller" method="post">
    <c:choose>
        <c:when test="${sessionScope.isLastPage}">
            <input type="submit" disabled name="next table" value="next page"/>
        </c:when>
        <c:otherwise>
            <input type="submit" name="next table" value="next page"/>
        </c:otherwise>
    </c:choose>
    <c:set var="pageNumber" scope="session" value="${sessionScope.pageNumber + 1}"/>
    <input type="hidden" name="command" value="list_persons"/>
</form>
<form action="Controller" method="post">
    <p><select name="linesPerPage">
        <option value="5"><fmt:message key="listPersonsPage.split.option1"/></option>
        <option value="10"><fmt:message key="listPersonsPage.split.option2"/></option>
        <option value="15"><fmt:message key="listPersonsPage.split.option3"/></option>
        <option value="20"><fmt:message key="listPersonsPage.split.option4"/></option>
        <option value="30"><fmt:message key="listPersonsPage.split.option5"/></option>
        <option value="60"><fmt:message key="listPersonsPage.split.option6"/></option>
    </select>
        <input type="submit" name="next table" value="submit"/>
        <input type="hidden" name="command" value="split_page"/>
</form>
<a href="http://localhost:8080/api/">
    <h4><fmt:message key="listPersonsPage.ref"/></h4>
</a>
</body>
</html>
