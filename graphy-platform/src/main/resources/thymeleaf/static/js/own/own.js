/**
 * 基础对象
 */
var own = {
    config: {
        webRoot: "", //虚拟目录
        ownRes: "", //own资源目录
        loadFileHost: ""//访问上传文件的连接地址配置
    },
    /**
     * @param {Object} def 默认配置
     */
    init: function (def) {
        own.importMeta();
        if (def == null) def = {};
        own.config.webRoot = def.webRoot == null ? "" : def.webRoot;
        own.config.ownRes = def.ownRes == null ? "" : def.ownRes;
        own.config.loadFileHost = def.loadFileHost == null ? "" : def.loadFileHost;
        own.importCss("/js/assets/css/bootstrap.min.css");
        own.importCss("/js/assets/font-awesome/4.5.0/css/font-awesome.min.css");
        own.importCss("/js/assets/css/jquery-ui.custom.min.css");
        own.importCss("/js/assets/css/chosen.min.css");
        own.importCss("/js/assets/css/bootstrap-datepicker3.min.css");
        own.importCss("/js/assets/css/bootstrap-timepicker.min.css");
        own.importCss("/js/assets/css/daterangepicker.min.css");
        own.importCss("/js/assets/css/bootstrap-datetimepicker.min.css");
        own.importCss("/js/assets/css/bootstrap-colorpicker.min.css");
        own.importCss("/js/assets/css/fonts.googleapis.com.css");
        own.importCss("/fonts/own-framework/iconfont.css");
        own.importCss("/js/summernote/summernote.css");
        own.importCss("/js/assets/css/ace.min.css", null, "ace-main-stylesheet", "main-ace-style");
        if (own.ieVersion() <= 9) own.importCss("/js/assets/css/ace-part2.min.css", null, "ace-main-stylesheet", null);
        own.importCss("/js/assets/css/ace-skins.min.css");
        own.importCss("/js/assets/css/ace-rtl.min.css");
        if (own.ieVersion() <= 9) own.importCss("/js/assets/css/ace-ie.min.css");
        own.importCss("/js/jquery-confirm/jquery-confirm.min.css");
        own.importCss("/js/picbox/picbox.css", "text/css", null, null, "screen");
        own.importCss("/js/bootstrap-table/bootstrap-table.min.css", "text/css", null, null, null);
        own.importCss("/js/jquery-treegrid/jquery.treegrid.min.css", "text/css", null, null, null);
        own.importCss("/js/jquery-treeselect/css/jquery-treeselect.css", "text/css", null, null, null);
        own.importCss("/js/select-mania/select-mania.min.css", "text/css", null, null, null);
        own.importCss("/css/own-style.css", "text/css", null, null, null);
        own.importJs("/js/jquery.cookie.js");
        own.importJs("/js/assets/js/bootstrap.min.js");
        own.importJs("/js/assets/js/ace-elements.min.js");
        own.importJs("/js/assets/js/ace.min.js");
        own.importJs("/js/assets/js/ace-extra.min.js");
        own.importJs("/js/jquery-migrate-1.2.1.js");
        own.importJs("/js/picbox/picbox.js");
        own.importJs("/js/assets/js/moment.min.js");
        own.importJs("/js/bootstrap-table/bootstrap-table.min.js");
        own.importJs("/js/bootstrap-table/bootstrap-table-zh-CN.min.js");
        own.importJs("/js/jquery-treegrid/bootstrap-table-treegrid.js");
        own.importJs("/js/jquery-treegrid/jquery.treegrid.min.js");
        own.importJs("/js/assets/js/chosen.jquery.min.js");
        own.importJs("/js/assets/js/bootstrap-datepicker.min.js");
        own.importJs("/js/assets/js/bootstrap-timepicker.min.js");
        own.importJs("/js/assets/js/daterangepicker.min.js");
        own.importJs("/js/assets/js/jquery-typeahead.js");
        own.importJs("/js/assets/js/bootstrap-datetimepicker.min.js");
        own.importJs("/js/select-mania/select-mania.min.js");

        /*

        own.importJs("/js/assets/js/bootstrap-tag.min.js");
        own.importJs("/js/assets/js/jquery-ui.custom.min.js");
        own.importJs("/js/assets/js/jquery.ui.touch-punch.min.js");
        own.importJs("/js/assets/js/spinbox.min.js");

        own.importJs("/js/assets/js/bootstrap-colorpicker.min.js");
        own.importJs("/js/assets/js/jquery.knob.min.js");
        own.importJs("/js/assets/js/autosize.min.js");
        own.importJs("/js/assets/js/jquery.inputlimiter.min.js");
        own.importJs("/js/assets/js/jquery.maskedinput.min.js");
        own.importJs("/js/assets/js/bootstrap-tag.min.js");
        own.importJs("/js/assets/js/markdown.min.js");
        own.importJs("/js/assets/js/bootstrap-markdown.min.js");
        own.importJs("/js/assets/js/jquery.hotkeys.index.min.js");
        own.importJs("/js/assets/js/bootstrap-wysiwyg.min.js");
        own.importJs("/js/assets/js/bootbox.js");
        own.importJs("/js/jquery-fileupload/jquery.ui.widget.js");
        own.importJs("/js/jquery-fileupload/jquery.iframe-transport.js");
        own.importJs("/js/jquery-fileupload/jquery.fileupload.js");
        own.importJs("/js/jquery-treeselect/jquery.ztree.all.js");
        own.importJs("/js/jquery-treeselect/jquery.slimscroll.min.js");
        own.importJs("/js/jquery-treeselect/jquery.ztree.exhide.js");
        own.importJs("/js/jquery-treeselect/jquery-treeselect.js");
        own.importJs("/js/summernote/summernote.js");
        own.importJs("/js/summernote/lang/summernote-zh-CN.js");
        own.importJs("/js/jquery.qrcode.min.js");
        */
        own.importJs("/js/jquery-confirm/jquery-confirm.min.js");


        own.importJs("/js/js.expand.js");
        own.importJs("/js/own-handle.js");
        own.imported();
    },
    /**
     * @param {Object} js 引入脚本
     */
    importJs: function (js, isOwn) {
        if (isOwn == null) isOwn = true;
        var src = isOwn ? own.config.ownRes + js : js;

        var jsURL = "<script type='text/javascript' src='" + src + "'></script>";
        if (document.body == null) {
            document.write(jsURL);
        } else {
            var script = document.createElement("script");
            script.type = "text/javascript";
            script.src = src;
            document.body.appendChild(script);
        }
    },
    /**
     * @param {Object} css
     * @param {Object} type
     * @param {Object} cls
     * @param {Object} id
     * @param {Object} media
     */
    importCss: function (css, type, cls, id, media) {
        var src = own.config.ownRes + css;
        var cssURL = "<link rel='stylesheet' href='" + src + "'";
        if (type != null) cssURL += " type='" + type + "'";
        if (cls != null) cssURL += " class='" + cls + "'";
        if (id != null) cssURL += " id='" + id + "'";
        if (media != null) cssURL += " media='" + media + "'";
        cssURL += "/>";
        document.write(cssURL);
    },
    /**
     * 初始化 meta 标签
     */
    importMeta: function () {
        var metas = [];
        metas.push("<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'/>");
        metas.push("<meta charset='utf-8'/>");
        metas.push("<meta name='description' content=''/>");
        metas.push("<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/>");
        for (var i = 0; i < metas.length; i++) {
            document.write(metas[i]);
        }
    },
    /**
     * 初始化加载完成后
     */
    imported: function () {
        window.onload = function () {
            $("html:eq(0)").attr("lang", "en").css("overflow-x", "hidden");
            if ('ontouchstart' in document.documentElement) own.importJs("/js/assets/js/jquery.mobile.custom.min.js");

        }
    },
    /**
     * 判断是否为IE浏览器 返回版本号
     */
    ieVersion: function () {
        if (document.documentMode) return document.documentMode;
    }

};
