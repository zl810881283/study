<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/font-awesome.css">
    <script src="/js/jquery-2.2.3.js"></script>
</head>
<body>
<form action="/management/image" id="upload-form" enctype="multipart/form-data" method="post">
    <div class="form-group">
        <label for="choose-image">选择图片</label>
        <input id="choose-image" type="file" name="image" class="form-control">
    </div>
    <button class="btn btn-success" >submit</button>
</form>

</body>
</html>