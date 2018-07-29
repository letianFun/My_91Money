
var codeTF=false;
var nameTF=false;
$(document).ready(function() {
    $('#registerForm').validate({
        debug:true,
        rules:{
            username: {
                required: true,
                rangelength: [5, 16]
            },
            password: {
                required: true,
                rangelength: [5, 16]
            },
            repeatPassword: {
                required: true,
                equalTo: '#password'
            }
        },
        messages:{
            username: {
                required: '请输入用户名',
                rangelength: '用户名长度必须在{0}-{1}个字符之间'
            },
            password: {
                required: '请输入密码',
                rangelength: '密码长度必须在{0}-{1}个字符之间'
            },
            repeatPassword: {
                required: '请输入确认密码',
                equalTo: '两次输入密码不一致不一致'
            }
        },
        submitHandler:function(form){
            if($("#uid").val().length==0){
                $("#uid").val(-1);
            }
            if (nameTF && codeTF){
                sub("registerForm","register1.html");
            }

            // $(form).ajaxSubmit({
            //     type: 'post',
            //     dataType: 'json',
            //     success: function(r){
            //         if(r.code == 0){
            //             window.location.href = 'register1.html';
            //         }else{
            //             layer.msg(r.msg);
            //         }
            //     }
            // });
        }
    });

    $('#username').change(function(){
        var username = $('#username').val();
        alert("---------------------进入了change方法-------"+username+"-----------");
        if (username.length < 5){
            return;
        }
        alert("------------------开始发起ajax请求---------------------");
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8081/checkname.do',
            dataType: 'json',
            data: {
                username: username
            },
            success: function(r){
                if(r.code == 0){
                    nameTF=true;
                    alert("nameTF="+nameTF);
                    $('#checkName').html(r.msg);
                    $('#checkName').css("color","green");

                }else{
                    nameTF=false;
                    alert("nameTF="+nameTF);
                    $('#checkName').html(r.msg);
                    $('#checkName').css("color","red");
                }
            }
        });

    });

    $('#sendPhone').click(function(){
        var phone = $('#phone').val();
        if(phone.length != 11){
            layer.alert('请输入手机号');
            return;
        }
        var _this =  $(this);
        _this.prop('disabled', true);
        _this.text('正在获取中......')
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8082/sendmsg.do',
            dataType: 'json',
            xhrFields:{withCredentials:true},
            data: {
                phone: phone
            },
            xhrFields:{withCredentials:true},
            success: function(r){
                if(r.code == 0){
                    var count = 5;
                    window.setInterval(function(){
                        count--;
                        if(count <= 0){
                            _this.text('重新发送');
                            _this.prop('disabled', false);
                        }else{
                            _this.text("重新发送(" + count + ")");
                        }

                    }, 1000);
                }else{
                    _this.text('重新发送');
                    _this.prop('disabled', false);
                    layer.alert(r.msg);


                }
            }
        });

    });

    $('#phonVerify').change(function(){
        var usercode = $('#phonVerify').val();
        alert("---------------------进入了检查验证码方法-------"+usercode+"-----------");

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8082/checkmsg.do',
            dataType: 'json',
            xhrFields:{withCredentials:true},
            data: {
                usercode: usercode
            },
            success: function(r){
                if(r.code == 0){
                    codeTF=true;
                    alert("codeTF="+codeTF);
                    $('#phonVerifys').html(r.msg);
                    $('#phonVerifys').css("color","green");

                }else{
                    nameTF=false;
                    alert("codeTF="+codeTF);
                    $('#phonVerifys').html(r.msg);
                    $('#phonVerifys').css("color","red");
                }
            }
        });

    });

});

