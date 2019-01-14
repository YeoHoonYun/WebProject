<%--
  Created by IntelliJ IDEA.
  User: cjswo
  Date: 2019-01-14
  Time: 오후 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 페이지</title>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="/">Home</a></li>
        <li role="presentation"><a href="/join/">Sign Up</a></li>
    </ul>
</div>
<br />
<div class="container">
    <form method="post" action="/">
        <div class="form-group">
            <label>아이디</label>
            <input type="title" class="form-control" name="userid" placeholder="아이디">
        </div>
        <div class="form-group">
            <label>패스워드</label>
            <textarea class="form-control" name="passwd"placeholder="패스워드"></textarea>
        </div>
        <div class="form-group">
            <label>이메일</label>
            <textarea class="form-control" name="email" placeholder="이메일"></textarea>
        </div>
        <button type="submit" class="btn btn-default">가입</button>
    </form>
</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script type="text/javascript" src="http:code.jquery.com/jquery-1.8.3.min.js"></script>
</body>
</html>