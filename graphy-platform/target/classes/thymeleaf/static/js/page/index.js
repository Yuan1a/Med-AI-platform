var pageIndex = {
    $navbar: null,//头部
    $sidebar: null,//左边
    $indexMainContent: null,//内容区域
    $tabs: null,//选项卡
    $nav: null,//导航
    $pageContent: null,//展示内容标签对象
    /**
     * 绑定导航
     * @param data
     */
    bindNav: function (back) {
        pageIndex.$nav = $("#index-nav");
        var create = function (data) {
            var bnh = {
                /**
                 * 创建一个导航标签对象
                 * @param dataitem
                 * @param father
                 * @returns {p.fn.init|jQuery|HTMLElement}
                 */
                createItem: function (dataitem, father) {
                    if (dataitem.showInMenu == "1") {
                        var li = $("<li></li>");
                        if (dataitem.id != null) {
                            li.attr("id", dataitem.id);
                        }
                        var li_a = $("<a href='javascript:void(0);'></a>");
                        var li_a_icon = $("<i class='menu-icon'></i>");
                        var li_a_text = $("<span class='menu-text'>" + dataitem.moduleName + "</span>");
                        if (dataitem.moduleIcon != null && dataitem.moduleIcon != "") {
                            li_a_icon.addClass(dataitem.moduleIcon);
                        } else {
                            li_a_icon.addClass("fa fa-caret-right");
                        }
                        if (dataitem.iconColor != null && dataitem.iconColor != "") li_a_icon.css("color", dataitem.iconColor);
                        li_a.append(li_a_icon).append(li_a_text);
                        if (dataitem.children != null && dataitem.children.length > 0) {
                            var li_a_angledown = $("<b class='arrow fa fa-angle-down'></b>");
                            li_a.append(li_a_angledown);
                            li_a.addClass("dropdown-toggle");
                        }
                        li.append(li_a);
                        var li_arrow = $("<b class='arrow'></b>");
                        li.append(li_arrow);
                        if (dataitem.children != null && dataitem.children.length > 0) {
                            var li_ul = $("<ul class='submenu'></ul>");
                            li.append(li_ul);
                        }
                        if (dataitem.active) li.addClass("active");
                        if (dataitem.children != null && dataitem.children.length > 0) {
                            $.each(dataitem.children, function (i, cn) {

                                bnh.createItem(cn, li);
                            });
                        }
                        if (father != null && father.length > 0) father.find(".submenu").append(li);
                        li.data("data", dataitem);
                        //绑定单击事件
                        li_a.click(function () {

                            pageIndex.openNavPage($(this).parent());
                        });
                        return li;
                    }

                }
            };
            $.each(data, function (i, n) {
                var li = bnh.createItem(n);
                if (li != null) {
                    pageIndex.$nav.append(li);
                }

            });
            //打开首页
            pageIndex.likeDefault();
        }
        $.own_post({
            url: "/pf/PlatformIndex/getIndexModule",
            call: function (res) {
                if (res.code == "1") {
                    create(res.result);
                } else {
                    $.own_error(res.msg);
                }
            }
        });
    }
    /**
     * 打开链接地址
     * @param li
     */
    , openNavPage: function (li) {
        var breadcrumbs = pageIndex.$tabs.find("ul");
        var data = li.data("data");
        if (data.children == null || data.children.length == 0) {
            pageIndex.loadurl(data, function () {
                breadcrumbs.empty();
                pageIndex.$nav.find(".active").removeClass("active");
                var lis = [];
                lis.push(li);
                li.parents("li").each(function () {
                    lis.push($(this));
                });
                for (var i = lis.length - 1; i >= 0; i--) {
                    var item = $(lis[i]);
                    item.addClass("active");
                    var menuText = item.find(".menu-text:eq(0)").text();
                    var bcli = $("<li></li>");
                    if (i == lis.length - 1) {
                        var bcli_aceIcon = $("<i class='ace-icon fa fa-home home-icon'></i>");
                        bcli.append(bcli_aceIcon);
                    }
                    if (i == 0) {
                        bcli.addClass("active");
                    }
                    bcli.append(menuText);
                    breadcrumbs.append(bcli);
                }
            });
        }
    }
    /**
     * 加载请求页面
     */
    , loadurl: function (data, back) {
        if (data.children == null || data.children.length == 0) {
            if (
                (data.moduleController != null && data.moduleController != "" && data.moduleController != "#") ||
                (data.moduleIndex != null && data.moduleIndex != "" && data.moduleIndex != "#")) {
                data.moduleController = data.moduleController.own_Replace("#", "");
                data.moduleIndex = data.moduleIndex.own_Replace("#", "");
                var lictrlcode = data.moduleController + data.moduleIndex;
                var ok = pageIndex.$tabs.own_ace_tab().add({
                    code: lictrlcode,
                    icon: data.moduleIcon, //图标样式
                    name: data.moduleName, //名称
                    isSelect: true, //是否被选中
                    isClose: data.isClose == null ? true : data.isClose  //是否有关闭按钮
                });
                if (ok != null || ok == true) return;
                var url = data.moduleController + data.moduleIndex;
                if (data.moduleIsNewPage == "0") {
                    var hmc = pageIndex.$pageContent;
                    var panel = hmc.find("div[panelcode='" + lictrlcode + "']");
                    if (panel.length == 0) {
                        panel = $("<div panelcode='" + lictrlcode + "'></div>");
                        hmc.append(panel);
                        $.own_loading();
                        $.own_get(url, function (html) {
                            html = html.own_Replace("</html>", "");
                            html = html.replace(/\<html.*?\>/g, "");
                            $.own_loading_close();
                            panel.html(html);
                            if (back != null) back();
                        });
                    } else {
                        $.own_get(url, function (html) {
                            $.own_loading_close();
                            panel.html(html);
                            if (back != null) back();
                        });
                    }
                } else {
                    let $iframe = $("<iframe src='" + url + "' frameborder='0'/>");
                    var hmc = pageIndex.$pageContent;
                    var panel = hmc.find("div[panelcode='" + url + "']");
                    if (panel.length == 0) {
                        panel = $("<div panelcode='" + url + "' style='margin-right: -5px;margin-left: -5px;'></div>");
                        panel.html($iframe);
                        hmc.append(panel);

                    }
                }
            }
        }
    }
    /**
     * 链接首页
     */
    , likeDefault: function () {
        var children = pageIndex.$nav.children();
        var firstPage = null;
        if (children.length > 0) {
            firstPage = $(children[0]);
        }
        if (firstPage != null && firstPage.find(".submenu").length == 0) {
            firstPage.removeClass("active").addClass("active");
            var data = firstPage.data("data");
            data.isClose = false;
            pageIndex.loadurl(firstPage.data("data"));
        }
        /*
        if (children.length == 1) {
            $("#sidebar").removeClass("menu-min").addClass("menu-min");
            $("#sidebar-toggle-icon").removeClass("fa-angle-double-left").addClass("fa-angle-double-right");
        }*/


    }
    /**
     * 退出系统
     */
    , loginout: function () {
        $.own_confirm("确定退出?", function () {
            $.own_post({
                url: "/pf/PlatformIndex/loginOut",
                postType: "2",
                call: function (res) {
                    if (res.code == "1") {
                        window.location = baseconfig.webLevel+"/pf/PlatformLogin/login-page";
                    } else {
                        $.own_error(res.msg);
                    }
                }
            });
        });
    }
    /**
     * 修改密码
     */
    , upPassword: function () {
        $.own_window({
            url: "/pf/PlatformIndex/upPasswordPage",
            title: "修改密码",
            size: "600px",
            hide: function (res) {
                if (res == true) {
                    window.location = baseconfig.webLevel+"/pf/PlatformLogin/login-page";
                }
            }
        });
    }
    /**
     * 无原始密码修改页面
     */
    , upPasswordDirectPage: function () {
        $.own_window({
            url: "/pf/PlatformIndex/upPasswordDirectPage",
            title: "修改密码",
            size: "600px",
            closeIcon: false,
            hide: function (res) {
                if (res == true) {
                    window.location = baseconfig.webLevel+"/pf/PlatformLogin/login-page";
                }
            }
        });
    }
    /**
     * 个人信息编辑
     */
    , editUserInfoPage: function () {
        $.own_window({
            url: "/pf/PlatformIndex/editUserInfoPage",
            title: "个人信息编辑",
            size: 3,
            hide: function (res) {
                if (res != null) {
                    var nup = $("#nav-user-photo");
                    if (res.pic == null || res.pic == "") {
                        if (res.gender == null || res.gender == "" || res.gender == "1") {
                            nup.attr("src", baseconfig.webLevel+"/image/head/user1.png");
                        } else {
                            nup.attr("src", baseconfig.webLevel+"/image/head/user2.png");
                        }
                    } else {
                        nup.attr("src", baseconfig.webLevel+"/pf/PlatformImage/load" + res.pic);
                    }
                    $("#index-user-name").text(res.name);
                }
            }
        });
    }
    /**
     * 选项卡绑定
     */
    , tabBind: function () {
        //绑定选项卡
        pageIndex.$tabs = $("#own-index-page-tab")
        pageIndex.$tabs.own_ace_tab({
            /**
             * 插入选项卡后事件
             * @param {Object} data
             */
            onInsert: function (data) {

            },
            /**
             * 选项卡选中后事件
             * @param {Object} data
             */
            onSelect: function (data) {
                pageIndex.$pageContent.find("div[panelcode]:visible").hide();
                pageIndex.$pageContent.find("div[panelcode='" + data.code + "']").show();
            },
            /**
             * 关闭选项卡之前
             * @param {Object} data
             */
            onCloseBefore: function (data) {
                return true;
            },
            /**
             * 关闭选项卡之后
             * @param {Object} data
             */
            onCloseAfter: function (data) {
                pageIndex.$pageContent.find("div[panelcode='" + data.code + "']").remove();
            },
            /**
             * 双击选项卡
             */
            dblclick: function () {
                var hideClass = "full";
                var show = pageIndex.$sidebar.hasClass(hideClass);
                var speed = 100;

                if (show) {
                    pageIndex.$tabs.find(".own-tabs-full i").removeClass("of-icon-centerfocusstrong").addClass("of-icon-zoomoutmap");
                    pageIndex.$sidebar.animate({
                        width: pageIndex.$sidebar.attr("_width")
                    }, speed, function () {
                        pageIndex.$sidebar.removeClass(hideClass);
                        pageIndex.$indexMainContent.removeClass(hideClass);
                        pageIndex.$sidebar.removeAttr("style");
                    });
                    pageIndex.$navbar.animate({
                        height: pageIndex.$navbar.attr("_height")
                    }, speed, function () {
                        pageIndex.$navbar.removeClass(hideClass);
                        pageIndex.$navbar.removeAttr("style");
                    });
                } else {
                    pageIndex.$tabs.find(".own-tabs-full i").removeClass("of-icon-zoomoutmap").addClass("of-icon-centerfocusstrong");
                    pageIndex.$sidebar.attr("_width", pageIndex.$sidebar.width());
                    pageIndex.$navbar.attr("_height", pageIndex.$navbar.height());
                    pageIndex.$sidebar.animate({
                        width: "0"
                    }, speed, function () {
                        pageIndex.$sidebar.addClass(hideClass);
                        pageIndex.$indexMainContent.addClass(hideClass);
                        pageIndex.$sidebar.removeAttr("style");
                    });
                    pageIndex.$navbar.animate({
                        height: "0"
                    }, speed, function () {
                        pageIndex.$navbar.addClass(hideClass);
                        pageIndex.$navbar.removeAttr("style");
                    });
                }
            },
            full: function () {
                this.dblclick();
                var hideClass = "full";
                var show = pageIndex.$sidebar.hasClass(hideClass);
                return show;
            }
        });
    }
};
$(function () {

    if ($("#coerceUpdatePassword").val() == "1") {
        pageIndex.upPasswordDirectPage();
    }
    pageIndex.$navbar = $("#navbar");
    pageIndex.$sidebar = $("#sidebar");
    pageIndex.$indexMainContent = $("#own-index-main-content");
    pageIndex.$pageContent = $("#own-index-page-tab-content");
    $("#sidebar-toggle-icon").click(function () {
        $(this).removeClass("fa-angle-double-right").removeClass("fa-angle-double-left");
        if (pageIndex.$sidebar.hasClass("menu-min")) {
            pageIndex.$sidebar.removeClass("menu-min");
            $(this).addClass("fa-angle-double-left");
        } else {
            pageIndex.$sidebar.addClass("menu-min");
            $(this).addClass("fa-angle-double-right");
        }
    });
    $("#nav-user-photo").own_image_show();
    //初始化选项卡
    pageIndex.tabBind();
    //绑定导航
    pageIndex.bindNav(function () {
    });
    $("#index-loginout").click(function () {
        pageIndex.loginout();
    });
    $("#index-up-password").click(function () {
        pageIndex.upPassword();
    });
    $("#edit-user-info").click(function () {
        pageIndex.editUserInfoPage();
    });

});
