<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<jsp:include page="./template/common.jsp" />

<script type="text/javascript">

$(document).ready(function() {
	var date = new Date();
	console.log(date);
});

</script>

<title>INDEX</title>

</head>
<body>
	<jsp:include page="./template/top.jsp" />
	<jsp:include page="./template/left.jsp" />
	<div style="min-height:80vh;" class="mt-4">
		<h1>INDEX</h1>
		<a href="./board">JPA BOARD SAMPLE</a>
	</div>
	<jsp:include page="./template/bottom.jsp" />	
</body>
</html>