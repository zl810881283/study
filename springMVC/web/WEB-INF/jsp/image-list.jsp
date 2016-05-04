<%--
  Created by IntelliJ IDEA.
  User: zl
  Date: 2016/5/3
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Image List</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/font-awesome.css">
    <script src="/js/jquery-2.2.3.js"></script>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <td>#</td>
        <td>主体数量</td>
        <td>操作</td>
        <td>图片</td>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${images}" var="image">
        <tr>
            <td><a href="/management/image/${image.id}">${image.id}</a></td>
            <td>${image.subjectsSize}</td>
            <td>
                <a href="/management/image/${image.id}" class=" btn btn-warning btn-modify" data-id="${image.id}">修改</a>
                <button class="btn btn-danger btn-delete" data-id="${image.id}">删除</button>
            </td>
            <td class="preview-lg">
                <div class="preview-lg img-preview-wrapper">
                    <div class="img-center"><img
                            src="/rest/image/resize/${image.id}">
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    jQuery(function ($) {
        $('.btn-delete').click(function () {
            $.ajax({
                type: "DELETE",
                url: "/rest/image/" + $(this).data('id'),
                success: function () {
                    alert("删除成功");
                    location.reload();
                }
            });


        });
    });
</script>
</body>
</html>
