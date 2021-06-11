<%@ include file="/WEB-INF/layouts/include.jsp" %>
<c:if test="${sessionScope.authenticated != null}">
    <div class="float-right">
    	<a href="<c:url value='/logout' />">Logout</a>
    </div>
</c:if>
