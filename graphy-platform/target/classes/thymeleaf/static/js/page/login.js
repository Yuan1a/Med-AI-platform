var pageLogin = {
    account: {
        query: "#account",
        postName: "account"
    },
    password: {
        query: "#password",
        postName: "password"
    },
    code: {
        query: "#code",
        postName: "code"
    },
    codeImg: {
        query: "#code-img",
        loadurl:  baseconfig.webLevel + "/pf/PlatformLogin/getVerifyCode"
    },
    loginConfig: {
        url:  baseconfig.webLevel + "/pf/PlatformLogin/login",
        postType: "application/x-www-form-urlencoded",
        call: function (res) {
            if (res.code == "1") {
                window.location = baseconfig.webLevel +"/pf/PlatformIndex/index";
            } else {
                pageLogin.alert(res.msg, function () {
                    if (res.msg.indexOf("请输入") < 0) {
                        pageLogin.refurbishauthenticode();
                    }
                });
            }

        }
    },
    /**
     * 提示信息
     * @param msg
     */
    alert: function (msg, back) {
        $.alert({
            title: '提示',
            animateFromElement: false,
            content: msg,
            type: "blue",
            backgroundDismiss: true,
            boxWidth: '300px',
            useBootstrap: false,
            buttons: {
                'confirm': {
                    text: '关闭消息提示框',
                    btnClass: 'btn-info',
                    action: function () {
                        this.setCloseAnimation('top');
                    }
                }
            },
            onDestroy: function () {
                if (back != null) back();
            }
        });
    },
    $account: null,
    $password: null,
    $code: null,
    $codeImg: null,
    $loginBtn: null,
    /**
     * 登录系统
     */
    login: function () {
        if (pageLogin.$loginBtn.val() != "登录") return;
        var account = pageLogin.$account.val();
        var password = pageLogin.$password.val();
        var code = pageLogin.$code.val();
        if (account == "") {
            pageLogin.alert("请输入用户名", function () {
                pageLogin.$account.focus();
            });
            return;
        }
        if (password == "") {
            pageLogin.alert("请输入密码", function () {
                pageLogin.$password.focus();
            });
            return;
        }
        if (code == "" && $(".notcode").length == 0) {
            pageLogin.alert("请输入验证码", function () {
                pageLogin.$code.focus();
            });
            return;
        }
        var data = {};
        data[pageLogin.account.postName] = account;
        data[pageLogin.password.postName] = password;
        data[pageLogin.code.postName] = code;
        pageLogin.$loginBtn.val("正在登录....");
        $.ajax({
            type: "POST",
            url: pageLogin.loginConfig.url,
            data: data,
            dataType: "json",
            success: function (res) {
                pageLogin.$loginBtn.val("登录");
                pageLogin.loginConfig.call(res);
            }
        });
    },
    /**
     * 刷新验证码
     */
    refurbishauthenticode: function () {
        var loadurl = pageLogin.codeImg.loadurl;
        if (loadurl != null) {
            loadurl += loadurl.indexOf("?") > -1 ? "&temp=" + (new Date().getTime().toString()) : "?temp=" + (new Date().getTime().toString());
            pageLogin.$codeImg.attr("src", loadurl);
        }
    },
    /**
     * 更改颜色
     */
    upPlatformLoginColor: function () {
        var platformLoginColor = $("#platformLoginColor").val();
        if (platformLoginColor != null) {
            var s = ".logo-name";
            s += ",.own-login .middle .middle-main .right .middle-main-rlogin .rtop .name";
            $(s).css("color", platformLoginColor);
            $(".own-login .middle .middle-main .right .middle-main-rlogin .rtop .name").css("border-bottom-color", platformLoginColor);
            $(".own-login .middle .middle-main .right .middle-main-rlogin .rbottom .handler input[type='button']").css("background-color", platformLoginColor);
        }
    }
};
$(function () {

    pageLogin.upPlatformLoginColor();
    var winheight = $(window).height();
    var oltop = $(".own-login .top");
    var olmiddle = $(".own-login .middle");
    var olbottom = $(".own-login .bottom");
    $(window).resize(function () {
        olmiddle.scrollTop(1);
        if ($(window).height() > winheight) {
            if (olmiddle.scrollTop() <= 0) {
                olbottom.show();
                oltop.show();
            }
        } else {
            if (olmiddle.scrollTop() > 0) {
                olbottom.hide();
                oltop.hide();
            }
        }

        winheight = $(window).height();
    });

    pageLogin.$account = $(pageLogin.account.query);
    pageLogin.$password = $(pageLogin.password.query);
    pageLogin.$code = $(pageLogin.code.query);
    pageLogin.$codeImg = $(pageLogin.codeImg.query);
    pageLogin.$loginBtn = $("#login");
    pageLogin.$loginBtn.click(function () {
        pageLogin.login();
    });
    pageLogin.$codeImg.click(function () {
        pageLogin.refurbishauthenticode();
    });
    pageLogin.refurbishauthenticode();
    $(pageLogin.account.query + "," + pageLogin.password.query + "," + pageLogin.code.query).keyup(function (e) {
        if (e.keyCode == 13) {
            pageLogin.login();
        }
    });
});