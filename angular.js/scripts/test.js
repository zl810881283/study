/**
 +-------------------------------------------------------------------------------------------------------------
 * [ȫ������ͼ]��� jquery.kinMaxShow
 +-------------------------------------------------------------------------------------------------------------
 * @author   Mr.kin
 * @version  1.1
 * @file       jquery.kinMaxShow-1.1.src.js
 * @info       ����BUG�����顢��ȡ���°汾 ��Mail��Mr.kin@foxmail.com(ע���ʼ����������kinMaxShow �Ա��������Զ��鵵)
 * @date       2013-08-27
 +-------------------------------------------------------------------------------------------------------------
 */
(function ($) {
    $.fn.kinMaxShow = function (user_options) {
        //Ĭ������
        var default_options = {
            //�õ�Ƭ�߶� Ĭ��350
            height: 350,
            //�õ�Ƭ�л����ʱ�� ��λ:��
            intervalTime: 3,
            //�õ�Ƭ�л�ʱ�� ��λ������ ,������Ϊ0 �����л�Ч�� ֱ��������һ��
            switchTime: 1000,
            //��ͣ��ͣ�л� ���ͣ����kinMaxShow�� �Ƿ���ͣ�л� Ĭ��true ��ͣ��ͣ������Ϊfalse ��ͣ����ͣ
            hoverPause: true,
            //����Ч��(�л�) jQuery�Դ��� "linear" �� "swing" ,����Ҫ��������Ч����ʹ�� jquery.easing.js  ���
            easing: 'linear',
            //ͼƬ���뷽ʽ
            imageAlign: 'center center',
            //��ť
            button: {
                //��ť����л��¼� ��ѡ�¼� click��mouseover
                switchEvent: 'click',
                //��ť���Ƿ���ʾ�������֣���1��ʼ��Ĭ�ϲ���ʾ
                showIndex: false,
                //��ť��ʽ
                //���� ��ť��ʽ  ֧�ֳ���CSS��ʽ������ͬjQuery css({key:val,����})
                normal: {
                    width: '10px',
                    height: '10px',
                    lineHeight: '14px',
                    right: '10px',
                    bottom: '10px',
                    fontSize: '10px',
                    background: "#8dc93a",
                    border: "1px solid #ffffff",
                    color: "#666666",
                    textAlign: 'center',
                    marginRight: '8px',
                    fontFamily: "Verdana",
                    float: 'left'
                },
                //��ǰ ��ť��ʽ
                focus: {background: "#8dc93a", border: "1px solid #8dc93a", color: "#000000"}
            },
            //�л��ص� index ��ǰͼƬ������action ���� ������ ���� �г� ֵ:fadeIn��fadeOut �������� thisָ�� ��ǰͼƬ�������� ��������������Ԫ�صĶ��� �����demo��
            callback: function (index, action) {
            }

        };
        options = jQuery.extend(true, {}, default_options, user_options);

        var k = {};

        //��ǰѡ���
        k.selector = $(this).selector;

        //�ж��Ƿ��ж������ ��ѡȡ�˶�������׳�����ͬһҳ�����ʹ�ö�� ����Ҫ�ֱ���ò��ҽ���ѡ�����id��
        if ($(this).length > 1) {
            $.error('kinMaxShow error[More than one selected object]');
            return false;
        }

        //��ǰ��������
        k.self = this;
        //��ǰͼƬ����
        k.index = 0;
        //ǰһ��ͼƬ����
        k.lindex = 0;
        //ͼƬ����
        k.size = $(k.self).children('div').size();
        //CSS class�����ռ�ǰ׺
        k.prename = 'KMSPrefix_' + k.selector.replace(/\W/ig, '') + '_';
        //���ݴ洢
        k.data = {};
        //֧�ֺ�������
        k.fn = {};

        //���� �����õ�Ƭ��͸�
        k.onload = function () {
            //���������ߴ� ������ʱ�������ݲ���
            $(k.self).css({
                height: options.height,
                overflow: 'hidden',
                position: 'relative'
            }).children('div').addClass(k.prename + 'image_item').hide();
            //��ʼ��
            k.init();

        };


        //��ʼ��
        k.init = function () {

            k.setLayout();
            k.setAnimate();

        };

        //����
        k.setLayout = function () {

            //image ����
            $(k.self).children('div').wrapAll('<div class="' + k.prename + 'image_box"></div>');
            $('.' + k.prename + 'image_item', k.self).each(function () {
                var a = $(this).children('a');
                if (a.length) {
                    var image = a.children('img').attr('src');
                    a.children('img').remove();
                    a.addClass(k.prename + 'coverlink');
                } else {
                    var image = $(this).children('img').attr('src');
                    $(this).children('img').remove();
                }
                //
                $(this).css({background: 'url(' + image + ') no-repeat ' + options.imageAlign, 'z-index': 1});

            });

            $('.' + k.prename + 'image_item', k.self).eq(0).css('z-index', '2');

            //button ����
            if (options.button.normal.display != 'none') {
                var button_list = '';
                for (i = 1; i <= k.size; i++) {
                    if (options.button.showIndex) {
                        button_list += '<li>' + i + '</li>';
                    } else {
                        button_list += '<li> </li>';
                    }
                }
                $(k.self).append('<ul class="' + k.prename + 'button">' + button_list + '</ul>');
                $('.' + k.prename + 'button li', k.self).eq(0).addClass(k.prename + 'focus');
            }

            //���� css
            k.setCSS();

            //��ʾ����
            $('.' + k.prename + 'image_item:gt(0)', k.self).css('z-index', 1).css({opacity: 0});
            $('.' + k.prename + 'image_item', k.self).show();
            $(k.self).css({overflow: 'visible', visibility: 'visible', display: 'block'});


        };

        //CSS
        k.setCSS = function () {

            var cssCode = '<style type="text/css">';
            cssCode += k.selector + ' *{ margin:0;padding:0;} ';
            cssCode += k.selector + ' .' + k.prename + 'image_box{width:100%;height:' + parseInt(options.height) + 'px;position:relative;z-index:1;} ';
            cssCode += k.selector + ' .' + k.prename + 'image_box .' + k.prename + 'image_item{width:100%;height:' + parseInt(options.height) + 'px;position:absolute;overflow:hidden;} ';
            cssCode += k.selector + ' .' + k.prename + 'image_box .' + k.prename + 'image_item a.' + k.prename + 'coverlink{width:100%;height:' + parseInt(options.height) + 'px;display:block;text-decoration:none;padding:0;margin:0;background:transparent;text-indent:0;outline:none;hide-focus:expression(this.hideFocus=true);} ';
            if (options.button.normal.display != 'none') {
                cssCode += k.selector + ' .' + k.prename + 'button{' + k.fn.objToCss(options.button.normal, ['top', 'right', 'bottom', 'left'], true) + ';position:absolute;list-style:none;z-index:2;overflow:hidden;_zoom:1;}';
                cssCode += k.selector + ' .' + k.prename + 'button li{' + k.fn.objToCss(options.button.normal, ['top', 'right', 'bottom', 'left']) + ';cursor:pointer;-webkit-text-size-adjust:none;}';
                cssCode += k.selector + ' .' + k.prename + 'button li.' + k.prename + 'focus{' + k.fn.objToCss(options.button.focus, ['top', 'right', 'bottom', 'left']) + ';cursor:default;}';
            }
            cssCode += '</style>';
            $(k.self).prepend(cssCode);

        }

        //��������
        k.setAnimate = function () {

            options.callback.call($('.' + k.prename + 'image_item:eq(' + k.index + ')', k.self), k.index, 'fadeIn');

            var overDelayTimer;//��switchEvent��mouseoverʱ  ִ���ӳټ�ʱ��
            $('.' + k.prename + 'button', k.self).delegate('li', options.button.switchEvent, function () {
                _this = this;
                function setChange() {
                    k.index = $(_this).index();
                    k.setOpacity();
                }

                if (options.button.switchEvent == 'mouseover') {
                    overDelayTimer = setTimeout(setChange, 200);
                } else {
                    setChange();
                }
            })
            //mouseover ��ʱ
            if (options.button.switchEvent == 'mouseover') {
                $('.' + k.prename + 'button', k.self).delegate('li', 'mouseout', function () {
                    clearTimeout(overDelayTimer);
                })
            }

            //��������
            k.index = 1;
            k.lindex = 0;
            //�Զ��л���ʱ��
            k.data.moveTimer = setInterval(k.setOpacity, options.intervalTime * 1000 + options.switchTime);

            //��ͣ��ͣ
            if (options.hoverPause) {
                $(k.self).hover(function () {
                    clearInterval(k.data.moveTimer);
                }, function () {
                    k.data.moveTimer = setInterval(k.setOpacity, options.intervalTime * 1000 + options.switchTime);
                })
            }

        };

        //����(�л�)
        k.setOpacity = function () {

            //�ص� fadeOut callback
            options.callback.call($('.' + k.prename + 'image_item:eq(' + (k.lindex) + ')', k.self), k.lindex, 'fadeOut');
            //��ť�л�
            if (options.button.normal.display != 'none') {
                $('ul.' + k.prename + 'button li', k.self).removeClass(k.prename + 'focus');
                $('ul.' + k.prename + 'button li', k.self).eq(k.index).addClass(k.prename + 'focus');
            }

            //ִֹͣ���еĶ���
            $('.' + k.prename + 'image_item:animated', k.self).stop(true, false);
            //������һ����ʾ��z-indexΪ0
            $('.' + k.prename + 'image_item', k.self).css('z-index', 1);
            //���õ�ǰ��ʾ��z-indexΪ1
            $('.' + k.prename + 'image_item', k.self).eq(k.index).css({opacity: 0, 'z-index': 2});
            //alert(k.index)
            $('.' + k.prename + 'image_item', k.self).eq(k.index).animate({opacity: 1}, options.switchTime, options.easing, function () {
                    $('.' + k.prename + 'image_box .' + k.prename + 'image_item:not(:eq(' + k.index + '))', k.self).css({opacity: 0});
                    //�ص� fadeIn callback
                    options.callback.call($('.' + k.prename + 'image_item:eq(' + k.index + ')', k.self), k.index, 'fadeIn');
                    k.lindex = k.index;
                    if (k.index == k.size - 1) {
                        k.index = 0;
                    } else {
                        k.index++;
                    }
                }
            );

        };

        //����
        k.run = function () {
            k.onload();
        };

        /* obj ������ʽ������"-"����ҪתΪ�շ�ʽд�� �磺font-size:12px; fontSize:12px;  excArr:����Ҫת�����б��ų������ ���� ���� ['test1','opacity'] ��excFlagΪture��ֻת��excArr�����е�CSS*/
        k.fn.objToCss = function (obj, excArr, excFlag) {
            excFlag = excFlag ? true : false;
            var isIE = navigator.userAgent.indexOf("MSIE") != -1;
            var style = '';
            if (excFlag) {
                for (var key in obj) {
                    if ($.inArray(key, excArr) != -1) {
                        pKey = key.replace(/([A-Z])/, KtoLowerCase);
                        if (pKey == 'opacity' && isIE) {
                            style += "filter:alpha(opacity=" + (obj[key] * 100) + ");";
                        } else {
                            style += pKey + ":" + obj[key] + ";";
                        }
                    }
                }
                ;
            } else {
                for (var key in obj) {
                    if ($.isArray(excArr)) {
                        if ($.inArray(key, excArr) == -1) {
                            pKey = key.replace(/([A-Z])/, KtoLowerCase);
                            if (pKey == 'opacity' && isIE) {
                                style += "filter:alpha(opacity=" + (obj[key] * 100) + ");";
                            } else {
                                style += pKey + ":" + obj[key] + ";";
                            }
                        }
                    } else {
                        pKey = key.replace(/([A-Z])/, KtoLowerCase);
                        if (pKey == 'opacity' && isIE) {
                            style += "filter:alpha(opacity=" + (obj[key] * 100) + ");";
                        } else {
                            style += pKey + ":" + obj[key] + ";";
                        }
                    }
                }
                ;
            }


            function KtoLowerCase(word) {
                var str = '';
                str = '-' + word.toLowerCase();
                return str;
            };
            return style;
        };

        /* ���� */
        k.run();


    }

})(jQuery)

jQuery(function () {
    jQuery(".photo_carousel").kinMaxShow();
});