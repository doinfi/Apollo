/**
 * SOS Map
 * 1. 集成google 、 amap 地图服务。
 * 2. 通过lanuage , en 用google cn 用amap(高德)
 * 3. 调用地图API逆向查询地理位置
 * @author max.hu
 */

var notFoundMessage_en = 'Not found';
var notFoundMessage_cn = '没有数据';

var SosMap = {
    //默认en 
    language: "en",
    amapObj: null,
    //初始化
    init: function (sosId, language) {
        this.language = language;
        if (sosId === undefined || sosId === '') {
            var message = language === 'zh-CN' ? notFoundMessage_cn : notFoundMessage_en;
            errorShowMessage(message);
            return;
        }
        this.querySos(sosId, function (record) {
            if (record === undefined || record === null || record.sosLogLat === undefined || record.sosLogLat === '') {
                var message = language === 'zh-CN' ? notFoundMessage_cn : notFoundMessage_en;
                errorShowMessage(message);
                return;
            }
            var lngLatArr = record.sosLogLat.split(',');
            if (language === "zh-CN") {
                SosMap.initAMap(lngLatArr[0], lngLatArr[1]);
                SosMap.queryAMapAddress(record.sosLogLat, function (address) {
                    SosMap.showTipsMessage(record, language, address);
                });
            } else {
                SosMap.queryGoogleMapAddress(record.sosLogLat, function (address) {
                    SosMap.initGoogleMap(lngLatArr[0], lngLatArr[1]);
                    SosMap.showTipsMessage(record, language, address);
                })
            }
        });
    },

    /**
     * 查询SOS 信息
     */
    querySos: function (sosId, callback) {
        $.ajax({
            type: "POST",
            url: "/coros/user/querySos",
            contentType: 'application/json',
            data: JSON.stringify({
                sosId: sosId
            }),
            dataType: "json",
            success: function (data) {
                if (data && data.result === "0000") {
                    callback(data.data);
                } else if (data) {
                    var message = language === 'zh-CN' ? notFoundMessage_cn : notFoundMessage_en;
                    errorShowMessage(message);
                    console.log("error");
                }
            },
            error: function (xhr, errorInfo, ex) {
                var message = language === 'zh-CN' ? notFoundMessage_cn : notFoundMessage_en;
                errorShowMessage(message);
                console.log("error");
            }
        });
    },
    /**
     * 设置标签信息
     */
    showTipsMessage: function (record, language, address) {
        var enStr = ' <b>This is COROS SOS {nickname}</b> fell down at <b>{address}</b>， please go to help him/her ASAP,{date}';
        var cnStr = '<b>COROS SOS 中心 {cnTime}</b> 您的好友<b>{nickname}</b>在 <b>{address}</b>跌倒，请尽快去帮助他/她。';
        var cnCancelStr = "{cnModifyTime} 您的好友<b>{nickname}</b>取消了SOS紧急呼叫模式，已确认自身安全，谢谢您的关心，打扰之处还望见谅。";
        var enCancelStr = "The registered owner has cancelled the SOS Emergency Contact mode and has confirmed his orher safety on {enModifyTime} Thank you for your concern.";
        if (address === undefined) {
            enStr = '<b>This is COROS SOS {nickname}</b> sent out an SOS alert on {date}. '
                + 'We are unable to obtain his/her GPS location. Please call XXX toconfirm his/her safety.';
            cnStr = '<b>COROS SOS 中心 {nickname}</b> {date} 时间发出SOS求助，无法定位到对方所在位置，请电话尝试联系对方，确认对方是否安全。';
        }
        if (record.cnModifyTime === undefined) {
            record.cnModifyTime = '';
        }
        if (record.enModifyTime === undefined) {
            record.enModifyTime = '';
        }
        var param = {
            nickname: record.nickname,
            address: address,
            date: record.sosShowTime,
            cnTime: record.cnTimeStr,
            cnModifyTime: record.cnModifyTime,
            enModifyTime: record.enModifyTime
        };

        // 语言
        var htmlText = language === 'zh-CN' ? cnStr : enStr;
        htmlText = htmlText.format(param);

        if (record.sosStatus === 1) {
            //如果取消再加入取消的内容
            var cancelText = language === 'zh-CN' ? cnCancelStr : enCancelStr;
            cancelText = cancelText.format(param);
            htmlText += "<br>"
            htmlText += cancelText;
        }
        $('#title').html(htmlText);
    },
    /**
     *  根据经纬度查询Google地图返回对应详细地址
     *  @Param lat 经纬度逗号分隔
     *  @Param language 语言 en 英文，cn中文 默认 en
     *  @Param callback 回调函数，参数为Google API result
     *  @Author max.hu
     **/
    queryGoogleMapAddress: function (lnglat, callback) {
        //纬度在前 经度在后 与 高德有区别
        var lngLatArr = lnglat.split(',');
        var latlng = lngLatArr[1] + ',' + lngLatArr[0];
        $.ajax({
            type: "POST",
            url: 'https://maps.googleapis.com/maps/api/geocode/json?latlng=' + latlng +
            '&key=AIzaSyDPCLDkEHWsYSDbyAqdA2QIMQSv35CCao8&language=en',
            dataType: "json",
            success: function (data) {
                if (data.results[0] && data.results[0] !== undefined) {
                    var formatted_address = data.results[0].formatted_address;
                    callback(formatted_address);
                } else {
                    $('#map').hide();
                    callback(undefined);
                    console.log("error");
                }
            },
            error: function (xhr, errorInfo, ex) {
                $('#map').hide();
                callback(undefined);
                console.log("error");
            }
        });
    },
    /**
     *调用高德地图正向查询详细地址
     *    @param lnglat 经纬度
     *    @param callback 异步调用回调 参数为 formatAddress
     *    @Author max.hu
     **/
    queryAMapAddress: function (lnglat, callback) {
        var that = this;
        var xys = lnglat.split(',');
        //其他坐标转高德坐标
        var gps = [parseFloat(xys[0]), parseFloat(xys[1])];
        AMap.convertFrom(gps, 'gps', function (status, result) {
            if (result.info === 'ok') {
                var lnglats = result.locations; // Array.<LngLat>
                var lnglatXY = new AMap.LngLat(lnglats[0].lng, lnglats[0].lat);
                var MGeocoder;
                //加载地理编码插件
                that.amapObj.plugin(["AMap.Geocoder"], function () {
                    MGeocoder = new AMap.Geocoder({
                        radius: 100000,
                        extensions: "all"
                    });
                    //返回地理编码结果
                    AMap.event.addListener(MGeocoder, "complete", function (data) {
                        if (data === undefined || data.regeocode === undefined
                            || data.regeocode.formattedAddress === undefined) {
                            $('#map').hide();
                            callback(undefined);
                        } else {
                            callback(data.regeocode.formattedAddress);
                        }
                    });
                    //逆地理编码
                    MGeocoder.getAddress(lnglatXY);
                });

                //加点
                var marker = new AMap.Marker({
                    map: that.amapObj,
                    icon: "https://webapi.amap.com/images/0.png",
                    position: lnglatXY,
                    offset: new AMap.Pixel(-10, -34)
                });
                marker.setPosition(lnglatXY);
                that.amapObj.setFitView();

            }
        });

    },
    /**
     * 初始化Google地图服务
     */
    initGoogleMap: function (lng, lat) {
        var myLatLng = {
            lat: Number(lat),
            lng: Number(lng)
        };
        // Create a map object and specify the DOM element for display.
        var map = new google.maps.Map(document.getElementById('map'), {
            center: myLatLng,
            scrollwheel: false,
            zoom: 12
        });

        var marker = new google.maps.Marker({
            map: map,
            position: myLatLng,
            title: 'Tumble'
        });
    },

    initAMap: function (lat, lng) {
        this.amapObj = new AMap.Map("map", {
            view: new AMap.View2D({
                center: new AMap.LngLat(lng, lat), //地图中心点
                zoom: 13 //地图显示的缩放级别
            })
        });
        // TODO 英文版地图
        // this.amapObj = new AMap.Map("map", {
        //     center: new AMap.LngLat(lng, lat), //地图中心点
        //     lang: 'en',
        //     zoom: 13 //地图显示的缩放级别
        // });
    }
};

/**
 * 异常情况显示异常信息
 * 隐藏地图div
 * @param message 异常信息
 */
function errorShowMessage(message) {
    $('#title').html(message);
    $('#map').hide();
}


/**
 * 扩展字符串 format 方法
 * var str='这是一个测试的字符串：{0} {1}'.format('Hello','world');
 或:
 var str='这是一个测试的字符串：{str0} {str1}'.format({str0:'Hello',str1:'world'});
 * @param args
 * @returns {String}
 */
String.prototype.format = function (args) {
    var result = this;
    if (arguments.length > 0) {
        if (arguments.length === 1 && typeof (args) === "object") {
            for (var key in args) {
                if (args[key] !== undefined) {
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        } else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] !== undefined) {
                    var reg = new RegExp("({)" + i + "(})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}
