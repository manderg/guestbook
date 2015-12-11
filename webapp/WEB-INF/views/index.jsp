<%@page import="com.hanains.guestbook2.dao.guestDao"%>
<%@page import="com.hanains.guestbook2.vo.guestVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- 순서도 중요하다. 이게 불러오고 여기 내용을 bootstrap에서 불러다 쓰기 때문 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">


</style>


</head>
<body>

  <h2 class="text-center"><방명록></h2>
  <p class="text-center">하고싶은 말을 써주세요</p>       
  
<div class="container bg-info " >
	<c:forEach items="${list}" var="vo" varStatus="status">
  	<div class="row-fluid text-center">
    	<div class="span1"> </div>
    	<div class="span1"> ${vo.name }</div>
   
    	<span class="span7 text-left" style="background-color:lavenderblush;">${vo.message } </span>
   
 
    	<div class="span2"> ${vo.reg_date }</div>    	
    	<div class="span1">
    	<form action="/guestbook2/deleteform" method="post">
    	<input type="hidden" name="no" value="${vo.no}">
    	<input type="submit" class="btn btn-warning btn-xs" value="삭제"></form>
    	</div>    	
  	</div>
   	</c:forEach>
</div>
<br>

<div class="container" style="background-color:lavenderblush;">
  <h2><방명록 남기기></h2> 
  <form role="form" action="/guestbook2/insert" method="post">
    <div class="form-group col-sm-4">
      <label for="usr">Name:</label>
      <input type="text" class="form-control" name="name">
    </div>
    <div class="form-group col-sm-8">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" name="password">
    </div>
	<div class="form-group">
      <label for="comment">Comment:</label>
      <textarea class="form-control" rows="5" name="message"></textarea>
    </div>    
    <input type="submit" class="btn btn-info" value="작성">
  </form>
</div>



</body>
</html>