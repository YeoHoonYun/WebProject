<%--
  Created by IntelliJ IDEA.
  User: cjswo
  Date: 2019-01-07
  Time: 오후 3:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글상세 페이지</title>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<h1 class="text-center"><a href="/board/main">테스트 페이지</a></h1>
<div class="container">
    <div class="row">
    <c:choose>
    <c:when test="${empty board.title}">
        <script type="text/javascript">
            alert("ID : ${board.id}의 해당 글이 없습니다. ")
        </script>
        <div class="col-md-4"><h4>제목 : 값이 없습니다.</h4></div>
        <div class="col-md-2"><h4>ID : </h4></div>
        <div class="col-md-4"><h4>등록일 : </h4></div>
        <div class="col-md-2">
            <a class="btn btn-default pull-right" href="/board/main">목록</a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12"><h4>내용</h4></div>
    </div>
    <div class="row" style="height:50%">
        <textarea class="form-control" readonly="readonly" rows="10"  placeholder="내용"></textarea>
    </div>
        <c:redirect url = "/board/main"/>
    </c:when>
        <c:otherwise>
                <div class="col-md-3"><h4>제목 : ${board.title}</h4></div>
                <div class="col-md-2"><h4>ID : ${board.userId}</h4></div>
                <div class="col-md-3"><h4>등록일 : ${board.regdate}</h4></div>
                <div class="col-md-2"><h4>조회수 : ${board.readCount}</h4></div>
                <div class="col-md-2">
                    <a class="btn btn-default pull-right" href="/board/main">목록</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12"><h4>내용</h4></div>
            </div>
            <div class="row" style="height:50%">
                <textarea class="form-control" readonly="readonly" id="content" rows="10"  placeholder="내용">${board.content}</textarea>
            </div>
        </c:otherwise>
    </c:choose>
    <hr/>
    <a class="btn btn-default pull-left" href="/board/detail?id=${board.id-1}">이전글</a>
    <a class="btn btn-default pull-left" href="/board/detail?id=${board.id+1}">이후글</a>

    <a class="btn btn-default pull-right" href="/board/delete?id=${board.id}">삭제</a>
    <a class="btn btn-default pull-right" href="/board/update/${board.id}">수정</a>
    <a class="btn btn-default pull-right" href="/board/addWrite?id=${board.id}">답글</a>

</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script type="text/javascript" src="http:code.jquery.com/jquery-1.8.3.min.js"></script>
</body>
</html>
