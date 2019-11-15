/**
 * Created by Infi on 17/7/2.
 */
// wait for the DOM to be loaded
$(document).ready(function () {
    // 1. 获得token
    var accessToken = sessionStorage.getItem('accessToken');
    if (accessToken == undefined || accessToken.trim() === '') {
        location.href = "../login.html";
    }
    $('#accessToken').value = accessToken;

    // 2. 提交数据
    // bind 'myForm' and provide a simple callback function
    $('#firmwareForm').ajaxForm(function (data) {
        $('#tips').text(data.message)
    })
});