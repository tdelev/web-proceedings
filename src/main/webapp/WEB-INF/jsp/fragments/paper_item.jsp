<div class="paper-item">	
	<h4 class="list-group-item-heading">
		<i class="fa fa-angle-right"></i> <a href="${pageContext.request.contextPath}/${paper.conference.year}/paper/${paper.id}/${paper.titleSlug}">${paper.title}</a>
	</h4>
	<div class="paper-authors">
	<c:forEach var="pa" items="${paper.paperAuthors}" varStatus="loop">
	<span class="list-group-item-text">
		<a href="${pageContext.request.contextPath}/authors/${pa.author.id}/${pa.author.slug}">${pa.author.firstName}
					${pa.author.lastName}</a>
	</span>
		<c:choose>
			<c:when test="${paper.authorsSize == 2}">
				<c:if test="${loop.first}"><span> and </span></c:if>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${loop.count < paper.authorsSize - 1}"><span class="span-coma">, </span></c:when>
					<c:otherwise>
						<c:if test="${paper.authorsSize != 1 && loop.count == paper.authorsSize - 1}"> and </c:if>
					</c:otherwise>	
				</c:choose>		
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<span class="pages">pp. ${paper.pages}</span>
	</div>
</div>