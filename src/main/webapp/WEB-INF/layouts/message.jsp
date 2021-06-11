<%@ include file="/WEB-INF/layouts/include.jsp" %>
<c:if test="${not empty messageBody}">
	<c:choose>
		<c:when test="${not empty messageType}">
			<c:choose>
				<c:when test="${messageType eq 'error'}">
					<i class="fa fa-exclamation-circle text-danger strong" aria-hidden="true"></i>
					<span class="ml-2 text-danger strong">${messageBody}</span>
				</c:when>
				<c:when test="${messageType eq 'success'}">
					<i class="fa fa-check-circle text-success strong" aria-hidden="true"></i>
					<span class="ml-2 text-success strong">${messageBody}</span>
				</c:when>
				<c:otherwise>
					<i class="fa fa-info-circle text-info strong" aria-hidden="true"></i>
					<span class="ml-2 text-info strong">${messageBody}</span>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<i class="fa fa-info-circle text-info strong" aria-hidden="true"></i>
			<span class="ml-2 text-info strong">${messageBody}</span>
		</c:otherwise>
	</c:choose>
</c:if>
<c:if test="${empty messageBody}">
	<%
	try {
		String message = (String)session.getAttribute("message");
		if (message != null && message.length() > 0) 
		{
			out.print("<div class='strong'>" + message + "</div>");
		}
	}
	finally { 
		session.removeAttribute("message");
	}
	%>
</c:if>