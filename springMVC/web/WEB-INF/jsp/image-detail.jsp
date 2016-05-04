<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="/css/cropper.css">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/font-awesome.css">
    <script src="/js/jquery-2.2.3.js"></script>
    <script src="/js/cropper.js"></script>
    <style>
        .preview-lg {
            width: 160px;
            height: 160px;
        }

        .preview-sm {
            width: 40px;
            height: 40px;
        }

        .img-center {
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            overflow: hidden;
            margin: auto;

        }

        .img-preview {
            border: 2px dashed #555555;
            border-radius: 5px;
        }

        .img-preview-wrapper {
            position: relative;
            background: #aaaaaa;
            border-radius: 5px;
        }

        .subject-form {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 ">
            <div class="img-container cropper-container">
                <img id="image"
                     src="/rest/image/${imageId}"
                     alt=""
                >
            </div>
        </div>
        <div class="col-md-4">
            <div class="preview-lg img-preview-wrapper">
                <div class="img-preview preview-lg img-center"></div>
            </div>
            <form action="" class="subject-form">
                <div class="form-group">
                    <label for="subject-name">名称</label>
                    <input type="text" name="subject-name" id="subject-name" class="form-control">
                </div>
                <button class="btn btn-success" type="button" id="submit">提交</button>
            </form>
        </div>
    </div>
</div>

<h2 class="h2">语义</h2>
<table class="table">
    <thead>
    <tr>
        <td>#</td>
        <td>名称</td>
        <td>操作</td>
        <td>图片</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${subjects}" var="subject">
        <tr>
            <td>${subject.id}</td>
            <td>${subject.name}</td>
            <td>
                <button class="btn btn-danger btn-delete" data-id="${subject.id}">删除</button>
            </td>
            <td class="preview-lg">
                <div class="preview-lg img-preview-wrapper">
                    <div class="img-center"><img
                            src="/rest/image/cut/${subject.image.id}?centerX=${subject.centerX}&centerY=${subject.centerY}&offX=${subject.offX}&offY=${subject.offY}">
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    jQuery(function ($) {
        var cropData={};
        var cropper = new Cropper($('#image')[0], {
            preview: '.img-preview',
            crop: function (e) {
                cropData=e.detail;
                console.log(e);
                console.log('detail x: ' + e.detail.x);
                console.log('detail y: ' + e.detail.y);
                console.log('detail width: ' + e.detail.width);
                console.log('detail height: ' + e.detail.height);
                console.log('detail rotate: ' + e.detail.rotate);
                console.log('detail scaleX: ' + e.detail.scaleX);
                console.log('detail scaleY: ' + e.detail.scaleY);
            }
        });

        $('#submit').click(function () {
            $.ajax({
                type: "POST",
                url: "/rest/subject",
                data: {
                    imageId:"${imageId}",
                    name: $('#subject-name').val(),
                    centerX: parseInt(cropData.x + cropData.width / 2),
                    centerY: parseInt(cropData.y + cropData.height / 2),
                    offX: parseInt(cropData.width / 2),
                    offY: parseInt(cropData.height / 2)
                },

                success: function () {
                    alert("添加成功");
                    location.reload();
                }
            });
        });

        $('.btn-delete').click(function(){
            $.ajax({
                type: "DELETE",
                url: "/rest/subject/"+$(this).data('id'),
                success: function () {
                    alert("删除成功");
                    location.reload();
                }
            });


        });

    });


    jQuery(function ($) {
        $('img').load(function () {
            $('table .img-center').width(function () {
                return $(this).find('img').width();
            });
            $('table .img-center').height(function () {
                return $(this).find('img').height();
            })
        });

    });

</script>
</body>
</html>