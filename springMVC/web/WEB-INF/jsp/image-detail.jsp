<%@ page contentType="text/html;charset=utf-8" %>
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
        </div>
    </div>
</div>


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
    <tr>
        <td>0</td>
        <td>asdf</td>
        <td>
            <button class="btn btn-danger">删除</button>
        </td>
        <td class="preview-lg">
            <div class="preview-lg img-preview-wrapper">
                <div class="img-center"><img
                        src="/rest/image/cut/4028b881546d9d0f01546d9d369e0000?centerX=100&centerY=100&offX=80&offY=100">
                </div>
            </div>
        </td>
    </tr>
    <tr>
        <td>0</td>
        <td>asdf</td>
        <td>
            <button class="btn btn-danger">删除</button>
        </td>
        <td class="preview-lg">
            <div class="preview-lg img-preview-wrapper">
                <div class="img-center"><img
                        src="/rest/image/cut/4028b881546d9d0f01546d9d369e0000?centerX=100&centerY=100&offX=80&offY=100">
                </div>
            </div>
        </td>
    </tr>
    </tbody>
</table>
<script>
    jQuery(function ($) {
        $('#upload-image').click(function () {
            console.log('click');
            $.ajax({
                type: "POST",
                url: "/rest/image",
                data: new FormData($('#upload-form')[0]),
                processData: false,
                contentType: false,
                success: function () {
                    alert("Data Uploaded: ");
                }
            });
        });
        var cropper = new Cropper($('#image')[0], {
            preview: '.img-preview',
            crop: function (e) {
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