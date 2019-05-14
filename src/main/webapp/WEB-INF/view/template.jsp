<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<jsp:include page="meta.jsp"></jsp:include>
		<title>${ title }</title>
		<jsp:include page="css.jsp"></jsp:include>
	</head>
	<body>
		<div class="page-wrapper">
			<jsp:include page="header.jsp"></jsp:include>
			
			<div class="content-wrap">
				<h2 class="page-title">${ pageTitle }</h2>
				<h1>Hello ${ user }!!!!</h1>
			</div>
			
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
		<jsp:include page="js.jsp"></jsp:include>
	</body>
</html>