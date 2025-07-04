var ownBtHandler = {
    /**
     * 根据数据源生成树形列表需要的数据
     * @param rows
     * @param opts
     */
    treeInitData: function (rows, tree) {
        if (tree != null) {
            $.each(rows, function (i, n) {
                var identify = i + 1;
                tree.keyIdentify[n[tree.idName]] = identify;
                if (tree.maxIdentify < identify) {
                    tree.maxIdentify = identify;
                }
                n[tree.treeId] = identify;
            });
            $.each(rows, function (i, n) {
                var pid = n[tree.pidName];
                n[tree.treePid] = tree.keyIdentify[pid] == null ? 0 : tree.keyIdentify[pid];
            });
            tree.rows = rows;
            return tree;
        }
    },
    /**
     * 异步请求下级节点
     * @param $table
     * @param pid
     * @param back
     */
    treeLoadRows: function ($table, pid, back) {
        var tree = $table.data("tree");
        if (tree != null) {
            var opt = $table.bootstrapTable("getOptions");
            var url = tree.url;
            var postPidName = tree.postPidName;
            var pdata = {};
            pdata[postPidName] = pid;
            $.own_loading();
            $.own_post({
                url: url,
                data: pdata,
                call: function (res) {
                    $.own_loading_close();
                    var ipid = tree.keyIdentify[pid];
                    if (res.code == "1") {
                        if (back != null) back(res.result, pid);
                    } else {
                        $.own_error(res.msg);
                    }
                }
            });
        }
    },
    /**
     * 视图添加数据行
     * @param $table
     */
    treeAppendRows: function ($table, child, pid, back) {
        var tree = $table.data("tree");
        if (tree != null) {
            var ipid = tree.keyIdentify[pid];
            var identify = tree.maxIdentify;
            $.each(child, function (i, n) {
                identify += 1;
                tree.keyIdentify[n[tree.idName]] = identify;
                n[tree.treeId] = identify;
                n[tree.treePid] = ipid;
                if (tree.maxIdentify < identify) {
                    tree.maxIdentify = identify;
                }
            });
            var btData = $table.bootstrapTable("getData");
            $.merge(btData, child);
            $.unique(btData);
            $table.bootstrapTable("load", btData);
            tree.rows = btData;
            $table.own_ace_table_tree_repaint(function () {
                if (back != null) back();
            });
        }
        $table.data("tree", tree);
    }
};
if ($.fn.bootstrapTable != null) {
    //api地址:  https://blog.csdn.net/mathew_leung/article/details/85227957
    $.extend($.fn.bootstrapTable.defaults, {
        //是否显示列头
        showHeader: true,
        showLoading: true,
        undefinedText: '',
        showFullscreen: false,
        toolbarAlign: 'left',
        paginationHAlign: 'right',
        silent: true,
        method: 'POST', //请求方式（*）
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: false, //是否启用排序
        sortOrder: "asc", //排序方式
        //queryParams: InitTable.queryParams,  //传递参数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 50, //每页的记录行数（*）
        pageList: [10, 50, 100, 200, 500, 1000], //可供选择的每页的行数（*）
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false, //是否显示所有的列
        showRefresh: false, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: false, //是否启用点击选中行
        //height: 680,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id", //每一行的唯一标识，一般为主键列
        showToggle: false, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        showExport: false,
        //exportDataType: 'all',
        //exportDataType: "selected",        //导出checkbox选中的行数
        paginationLoop: false,
        beforeRequest: function (params) { //请求数据前
            return params;
        },
        loadSuccess: function (data) { //数据加载完成后

        },
        queryParams: function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            var data = {
                size: params.limit, //页面大小
                page: (params.offset / params.limit) + 1 //页码
            };
            delete params["limit"];
            delete params["offset"];
            if (this.toolbar != null && this.toolbar != "") {
                var pnValues = $(this.toolbar).own_ace_form_data();
                if (pnValues != null) {
                    for (var key in pnValues) {
                        data[key] = pnValues[key];
                    }
                }
            }
            if (this.postType == "2") {
                return data;
            } else {
                data = this.beforeRequest(data);
                return JSON.stringify(data);
            }
        },
        onLoadSuccess: function (data) {
            if (data.code == "-1000") {
                $.own_confirm("您的会话已过期,是否重新登录?", function () {
                    window.location =  baseconfig.webLevel +"/pf/PlatformLogin/login-page";
                });
            } else if (data.code != "1") {
                $.own_error(data.msg);
            } else {
                var $table = $(this.tb);
                var bdata = data.result;
                var vopt = $table.bootstrapTable("getOptions");
                if (vopt.tree != null) {
                    var tree = ownBtHandler.treeInitData(bdata, vopt.tree);
                    $table.data("tree", tree);
                    bdata = tree.rows;
                } else {
                    bdata.rows = bdata.records;
                    bdata.pageNumber = bdata.page;
                    bdata.pageSize = bdata.size;
                    bdata.total = bdata.total;
                }
                $table.bootstrapTable("load", bdata);
                if (vopt.tree != null) {
                    $table.own_ace_table_tree_repaint();
                }
                data.result = bdata;
                this.loadSuccess(data);
            }
        },
        onLoadError: function () {

        }
    });
}
(function ($) {
    $.fn.extend({
        /**
         * 绑定表格
         * @param opt
         */
        own_ace_table: function (opt) {
            var tb = $(this);
            var def = {
                tb: tb,
                url: null, //请求地址
                postType: "1",//提交的数据类型 1=application/json;charset=utf-8 2=application/x-www-form-urlencoded;charset=utf-8
                tree: null,//树形表格配置
                columns: []
            };
            $.extend(def, opt);
            if (def.tree != null) {
                var deftree = {
                    idName: "id",//数据源主键字段名称  *
                    pidName: "pid",//数据源父节点字段名称 *
                    expandField: "name",//折叠/展开的列名称 *
                    postPidName: "pid",//当异步加载父节点参数名称
                    existChildrenName: "hasChildren",//服务返回的是否有子节点的列名称
                    treeId: "treeId",
                    treePid: "treePid",
                    url: def.url,
                    maxIdentify: 0,
                    keyIdentify: {}
                };
                $.extend(deftree, def.tree);
                def.uniqueId = deftree.treeId;
                def.idField = deftree.treeId;
                def.parentIdField = deftree.treePid;
                def.treeShowField = deftree.expandField;
                def.tree = deftree;
            }
            tb.addClass("own-ace-table");
            for (var i = 0; i < def.columns.length; i++) {
                def.columns[i].halign = def.columns[i].halign == null ? "center" : def.columns[i].halign;
                def.columns[i].align = def.columns[i].align == null ? "center" : def.columns[i].align;
                def.columns[i].valign = def.columns[i].valign == null ? "middle" : def.columns[i].valign;
                def.columns[i].oh = def.columns[i].oh == null ? false : def.columns[i].oh;
                if (def.columns[i].oh) {
                    def.columns[i].formatter = function (value, row, index) {
                        if (value == null) return "";
                        return "<div class='oh' title='" + value + "'>" + value + "</div>";
                    }
                }
            }

            if (def.data != null && def.tree != null) {
                def.tree = ownBtHandler.treeInitData(def.data, def.tree);
                tb.data("tree", def.tree);
                def.data = def.tree.rows;
            }
            if (def.postType == "2") {
                def.contentType = "application/x-www-form-urlencoded; charset=UTF-8";
            }
            tb.data("def", def);
            if (def.url != null) def.url = baseconfig.webLevel + def.url;
            tb.bootstrapTable("destroy");
            tb.bootstrapTable(def);
            if (def.data != null && def.tree != null) {
                tb.own_ace_table_tree_repaint();
            }
            if (def.data != null && def.loadSuccess != null) {
                def.loadSuccess(def.data);
            }
        },
        /**
         * 重绘树形表格
         * @param back 回调函数
         */
        own_ace_table_tree_repaint: function (back) {
            var $table = $(this);
            var tree = $table.data("tree");
            if (tree != null) {
                var opt = null;
                try {
                    opt = $table.bootstrapTable("getOptions");
                } catch (e) {

                }
                if (opt == null) opt = $table.data("def");
                var expandFieldIndex = null;

                if (!Array.isArray(opt.columns[0])) {
                    opt.columns = [opt.columns];
                }
                for (var i = 0; i < opt.columns[0].length; i++) {
                    if (opt.columns[0][i].field != null && opt.columns[0][i].field == tree.expandField) {
                        expandFieldIndex = i;
                        break;
                    }
                }
                if (expandFieldIndex != null) {
                    $table.treegrid({
                        saveState: true,
                        initialState: 'collapsed',
                        treeColumn: expandFieldIndex
                    });
                    $table.find("tr[data-uniqueid]").each(function () {
                        var $tr = $(this);
                        var uniqueid = $tr.attr("data-uniqueid");
                        var row = $table.bootstrapTable("getRowByUniqueId", uniqueid);
                        var existChildren = row[tree.existChildrenName];
                        var $tricon = $tr.find(".treegrid-expander");
                        if (existChildren == false) {
                            $tricon.removeClass("treegr-end-node").addClass("treegr-end-node");
                        } else {
                            $tricon.click(function () {
                                var cellIcon = $(this);
                                if ($tr.attr("expanded") == null || $tr.attr("expanded") == "0") {
                                    $tr.attr("expanded", "1");
                                    if ($table.find(".treegrid-parent-" + uniqueid).length == 0) {
                                        var pidValue = row[tree.idName];
                                        ownBtHandler.treeLoadRows($table, pidValue, function (rows, pid) {
                                            ownBtHandler.treeAppendRows($table, rows, pid, function (pid, ipid) {
                                                var $onrow = $table.find(".treegrid-" + ipid);
                                                $onrow.treegrid("expand");
                                            });
                                        });
                                    }
                                }
                            });
                        }
                    });
                    if (back != null) back();
                }
            }
        },
        /**
         * 获取提交的表单数据
         */
        own_ace_form_data: function () {
            var box = $(this);
            var data = {};
            var pn = box.find("*[pn],.own_ace_address");
            $.each(pn, function (i, n) {
                var input = $(n);
                var ownValue = input.own_value();
                if (input.hasClass("own_ace_address")) {
                    for (var key in ownValue) {
                        data[key] = ownValue[key];
                    }
                } else {
                    var pnName = input.attr("pn");
                    data[pnName] = ownValue;
                }

            });
            return data;
        },
        /**
         * 设置或者获取控件值
         */
        own_value: function (servalue) {
            var c = $(this);
            if (c.hasClass("own-ace-switch")) {
                if (servalue == undefined) {
                    return c.find("input[type='checkbox']").get(0).checked ? "1" : "0";
                } else {
                    c.find("input[type='checkbox']").attr("checked", "1" == servalue ? true : false);
                    return;
                }
            } else if (c.hasClass("own-image-upload")) {
                return c.find("input[type='file']").own_image_upload("value");
            } else if (c.hasClass("own-ace-checkbox-group")) {
                var def = c.data("def");
                if (servalue == undefined) {
                    var value = "";
                    c.find("input:checked").each(function () {
                        var ccheck = $(this);
                        value += value == "" ? ccheck.attr("value") : "," + ccheck.attr("value");
                    });
                    return value;
                } else {
                    c.find("input[type='checkbox'],input[type='radio']").each(function () {
                        var tc = $(this);
                        if (servalue == null) servalue = "";
                        servalue = "," + servalue + ",";
                        tc.attr("checked", servalue.indexOf("," + tc.attr("value") + ",") > 0);
                    });
                    return;
                }
            } else if (c.hasClass("own-ace-select")) {
                if (servalue == undefined) {
                    return c.val();
                } else {
                    c.val(servalue);
                    return;
                }
            } else if (c.hasClass("own_ace_address")) {
                var defHandler = c.data("defHandler");
                var address = defHandler.getPostData();
                return address;
            } else if (c.hasClass("ace-input-tags")) {
                var def = c.data("def");
                var value = "";
                c.parent().find(".tag").each(function () {
                    value += $(this).text().toString().replace("×", "") + def.apart;
                });
                return value;
            } else if (c.hasClass("own-ace-upload") && c.attr("mmtype") == "upload-image") {
                var datas = [];
                c.find(".own-ace-upload-files-item").each(function () {
                    datas.push($(this).data("data"));
                });
                return datas;
            } else if (c.hasClass("own-ace-upload") && c.attr("mmtype") == "upload-image") {
                var datas = [];
                c.find(".own-ace-upload-files-item").each(function () {
                    datas.push($(this).data("data"));
                });
                return datas;
            } else if (c.hasClass("own-ace-editor")) {
                if (servalue == undefined) {
                    return c.own_ace_editor().html();
                } else {
                    c.own_ace_editor().insertHtml(servalue);
                    return;
                }
            } else if (c.is("input") && c.attr("type") == "text") {
                if (servalue == undefined) {
                    return c.val();
                } else {
                    c.val(servalue);
                    return;
                }
            } else if (c.is("input") && c.attr("type") == "password") {
                if (servalue == undefined) {
                    return c.val();
                } else {
                    c.val(servalue);
                    return;
                }
            } else if (c.is("input") && c.attr("type") == "hidden") {
                if (servalue == undefined) {
                    return c.val();
                } else {
                    c.val(servalue);
                    return;
                }
            } else if (c.is("input") && c.attr("type") == "date") {
                if (servalue == undefined) {
                    return c.val();
                } else {
                    c.val(servalue);
                    return;
                }
            } else if (c.is("input") && c.attr("type") == "datetime") {
                if (servalue == undefined) {
                    return c.val();
                } else {
                    c.val(servalue);
                    return;
                }
            } else if (c.is("input") && (c.attr("type") == "checkbox" || c.attr("type") == "radio")) {
                if (servalue == undefined) {
                    return c.get(0).checked ? "1" : "0";
                } else {
                    c.attr("checked", servalue == "1");
                    return;
                }
            } else if (c.is("textarea")) {
                if (servalue == undefined) {
                    return c.val();
                } else {
                    c.val(servalue);
                    return;
                }
            } else if (c.is("select")) {
                if (servalue == undefined) {
                    return c.val();
                } else {
                    c.val(servalue);
                    return;
                }
            }
            console.error("无效的标签");
        },
        /**
         * 绑定是或者否的选择控件
         * @param {Object} opt
         */
        own_ace_yesno: function (opt) {
            var cs = $(this);
            cs.each(function (i, n) {
                var cz = $(n);
                cz.empty();
                var def = {
                    value: null, //初始化是否选中  true|false
                    select: function (value) {

                    }
                };
                $.extend(def, opt);
                if (cz.attr("value") != null && cz.attr("value") != "") {
                    def.value = cz.attr("value") == "1" ? true : false;
                }
                if (cz.attr("val") != null && cz.attr("val") != "") {
                    def.value = cz.attr("val") == "1" ? true : false;
                }
                cz.removeClass("own-ace-switch").addClass("own-ace-switch");
                var cz_checkbox = $('<input class="ace ace-switch ace-switch-4 btn-flat" type="checkbox"/>');
                var cz_lbl = $('<span class="lbl"></span>');
                cz.append(cz_checkbox).append(cz_lbl);
                cz_checkbox.data("def", def);
                if (def.value != null) {
                    cz_checkbox.attr("checked", def.value);
                }
                cz_checkbox.click(function () {
                    var ddef = $(this).data("def");
                    ddef.select($(this).is(':checked'));
                });
            });
            cs.data("def", this);
            return this;
        },
        /**
         * 单选组合框
         * @param {Object} opt
         */
        own_ace_radio_group: function (opt) {
            var cs = $(this);
            if (opt == null) opt = {};
            opt.multiple = false;
            cs.own_ace_checkbox_group(opt);
        },
        /**
         * 多选组合框
         * @param {Object} opt
         */
        own_ace_checkbox_group: function (opt) {
            var cs = $(this);
            cs.each(function (i, n) {
                var c = $(n);
                var def = {
                    data: [],
                    value: null, //字符串,并且多项为逗号隔开
                    multiple: true, //是否多选
                    fieldValue: "value",
                    fieldText: "text",
                    layout: "horizontal", //horizontal:横向 vertical:纵向
                    select: function (value) {

                    }
                };
                $.extend(def, opt);
                var name = "";
                if (c.attr("pn") == null) {
                    name = $.own_guid();
                } else {
                    name = c.attr("pn");
                }

                if (c.attr("value") != null && c.attr("value") != "") {
                    def.value = c.attr("value");
                }
                if (c.attr("val") != null && c.attr("val") != "") {
                    def.value = c.attr("val");
                }
                c.addClass("own-ace-checkbox-group");
                c.addClass(def.layout);
                c.data("def", def);
                c.empty();
                $.each(def.data, function (i, n) {
                    var macgi = $("<label class='own-ace-checkbox-group-item'></label>");
                    macgi.data("data", n);
                    var macgi_checkbox = $('<input class="ace ace-checkbox-2" type="checkbox">');
                    if (def.multiple == false) {
                        macgi_checkbox.attr("type", "radio");
                        macgi_checkbox.attr("name", name);
                    }
                    if (def.value != null && ("," + def.value + ",").indexOf("," + n[def.fieldValue] + ",") > -1) {
                        macgi_checkbox.attr("checked", "checked");
                    }
                    macgi_checkbox.data("data", n);
                    var macgi_lbl = $('<span class="lbl"></span>');
                    macgi_checkbox.attr("value", n[def.fieldValue]);
                    macgi_lbl.text(" " + n[def.fieldText]);
                    macgi.append(macgi_checkbox).append(macgi_lbl);
                    c.append(macgi);
                    macgi_checkbox.change(function () {
                        var value = c.own_value();
                        def.select(value);
                    });
                });
            });
        },
        /**
         * 选择框
         * @param {Object} opt
         */
        own_ace_select: function (opt) {
            var cs = $(this);
            cs.each(function (i, n) {
                var c = $(n);
                c.empty();
                var def = {
                    value: null,
                    data: [],
                    nullText: "请选择",
                    isnull: true, //是否显示请选择项目
                    fieldValue: "value",
                    fieldText: "text",
                    select: function (value) {

                    }
                };
                $.extend(def, opt);
                if (c.attr("value") != null && c.attr("value") != "") {
                    def.value = c.attr("value");
                }
                if (c.attr("val") != null && c.attr("val") != "") {
                    def.value = c.attr("val");
                }
                if (def.isnull && def.data != null) {
                    var temp = [];
                    var nullItem = {};
                    nullItem[def.fieldValue] = "";
                    nullItem[def.fieldText] = def.nullText;
                    temp.push(nullItem);
                    $.merge(temp, def.data);
                    def.data = temp;
                }
                c.data("def", def);
                c.empty();
                $.each(def.data, function (ii, nn) {
                    var option = $("<option></option>");
                    option.attr("value", nn[def.fieldValue]).text(nn[def.fieldText]);
                    c.append(option);
                });
                c.addClass("form-control").addClass("chosen-select").addClass("own-ace-select");
                if (def.value != null) {
                    c.val(def.value);
                } else if (c.attr("val") != null) {
                    c.val(c.attr("val"));
                }
                c.unbind("change");
                c.change(function () {
                    var dthis = $(this);
                    var ddef = dthis.data("def");
                    var value = dthis.own_value();
                    ddef.select(value);
                });
                c.own_select_mania({});
            });
        },
        /**
         * 绑定select_mania
         * api地址 https://www.jq22.com/jquery-info20485?tdsourcetag=s_pcqq_aiomsg
         * @param opt
         */
        own_select_mania: function (opt) {
            $(this).each(function (i, n) {
                var $cs = $(this);
                var def = {};
                $.extend(def, opt);
                $cs.selectMania({
                    size: 'small',
                    placeholder: '请选择',
                    removable: true,
                    search: true
                });
            });
        },
        /**
         * 图片上传
         * @param {Object} opt
         */
        own_image_upload: function (opt) {
            var $inputFile = $(this);
            if (typeof (opt) == 'string') {
                var iDef = $inputFile.data("def");
                if (opt == "value" || opt == "val") {
                    var returnValue = "";
                    $inputFile.parents(".own-image-upload").find(".image img").each(function () {
                        var src = $(this).attr("src");
                        if (src.indexOf("base64,") < 0 && iDef.host != null && iDef.host != "") {
                            if (src.startsWith(iDef.host)) {
                                src = src.substring(iDef.host.length);
                            }
                        }
                        returnValue += returnValue == "" ? src : "_" + src;
                    });
                }
                return returnValue;
            }
            var def = {
                //是否多个文件
                multifile: false,
                //图片最大宽度
                imageMaxWidth: 600,
                //图片最大高度
                imageMaxHeight: 400,
                picbox: "picbox-" + (new Date().getTime())
            };
            $.extend(def, opt);
            var control = {
                //原始上传控件
                $inputFile: null,
                //主上传区域
                $ofu: null,
                //处理过的上传控件
                $ofuUpload: null,
                //图片请求前置
                host: "",
                //初始化图片路径逗号隔开
                value: null
            };
            control.$inputFile = $inputFile;
            control.$inputFile.data("def", def);

            var ofuHandler = {
                /**
                 * 解析文件
                 */
                readURL: function (ifile, back) {
                    if (ifile.files && ifile.files.length > 0) {
                        var files = ifile.files;
                        var datas = [];
                        var fileCount = files.length;
                        $.each(files, function (i, file) {
                            var reader = new FileReader();
                            reader.onload = function (e) {
                                var canvas = document.createElement('canvas')
                                var ctx = canvas.getContext('2d');
                                var img = new Image();
                                img.onload = function () {
                                    // 限制图片的宽度与高度
                                    if (img.width > def.imageMaxWidth) {
                                        img.height = img.height * (def
                                                .imageMaxWidth /
                                            img.width);
                                        img.width = def.imageMaxWidth;
                                    } else if (img.height > def
                                        .imageMaxHeight) {
                                        img.width = img.width * (def
                                                .imageMaxHeight /
                                            img.height);
                                        img.height = def.imageMaxHeight;
                                    }
                                    canvas.width = img.width;
                                    canvas.height = img.height;
                                    ctx.drawImage(img, 0, 0, img.width, img
                                        .height);
                                    var base64 = canvas.toDataURL();
                                    if (back != null) back(base64);
                                }
                                img.src = e.target.result;

                            };
                            reader.readAsDataURL(file);
                        });

                    }
                },
                /**
                 * 初始化
                 */
                init: function () {
                    control.$ofu = $("<div class='own-image-upload'></div>");
                    if (control.$inputFile.attr("pn") != null) {
                        var pn = control.$inputFile.attr("pn");
                        control.$ofu.attr("pn", pn);
                        control.$inputFile.removeAttr("pn");
                    }
                    control.$inputFile.after(control.$ofu);
                    ofuHandler.creOfuUpload();
                    var path = control.$inputFile.attr("val");
                    if (path != null && path != "") def.value = path;
                    if (def.value != null && def.value != "") {
                        path = def.value.split(",");
                        for (var i = 0; i < path.length; i++) {
                            if (path[i] != "") {
                                ofuHandler.addImage(def.host + path[i]);
                            }
                        }
                    }
                },
                /**
                 * 添加图片
                 * @param {Object} url 图片链接地址/base64
                 */
                addImage: function (url) {
                    if (!def.multifile) {
                        control.$ofu.find(".image").remove();
                    }
                    var $image = $("<div class='ofu-item image'></div>");
                    var $img = $("<img rel='" + def.picbox + "' src='" + url + "'  />");
                    var $delb = $("<span class='del-b'></span>");
                    var $del = $("<span class='del'></span>");
                    $image.append($img).append($del).append($delb);
                    $image.find(".del,.del-b").click(function () {
                        $(this).parents(".ofu-item").remove();
                    });
                    control.$ofuUpload.before($image);
                    $("img[rel='" + def.picbox + "']").own_image_show();
                },
                /**
                 * 创建上传按钮
                 */
                creOfuUpload: function () {
                    control.$ofuUpload = $("<div class='ofu-item ofu-upload'></div>");
                    control.$ofu.append(control.$ofuUpload);
                    control.$ofuUpload.append(control.$inputFile);
                    control.$ofuUpload.after("<span style='clear: both;'></span>");
                    control.$inputFile.attr("accept", "image/*");
                    if (def.multifile == true) control.$inputFile.attr("multiple", true);
                    control.$inputFile.change(function (e) {
                        try {
                            ofuHandler.readURL(this, function (url) {
                                ofuHandler.addImage(url);
                            });
                        } catch (ex) {

                        } finally {
                            e.target.value = null;
                        }
                    });
                }
            };
            ofuHandler.init();
        },
        /**
         * 选项卡
         * @param {Object} opt
         */
        own_ace_tab: function (opt) {
            var tabs = $(this);
            if (opt != undefined) {
                tabs.empty();
                var def = {
                    /**
                     * 最多可打开多少个选项卡
                     */
                    maxCount: 30,
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

                    },
                    /**
                     * 选项卡双击事件
                     */
                    dblclick: function () {

                    },
                    /**
                     * 普通/全屏事件
                     */
                    full: function () {

                    }
                };
                $.extend(def, opt);
                var otHander = {
                    otmc: null,
                    otmcSlitherLeft: false,
                    otmcSlitherRight: false,
                    /**
                     * 选项卡滑动
                     */
                    otmcSlither: function () {
                        tabs.find(".own-tabs-left").mousedown(function () {
                            if (!otHander.otmcSlitherLeft) otHander.otmcSlitherLeft = true;
                        }).mouseup(function () {
                            if (otHander.otmcSlitherLeft) otHander.otmcSlitherLeft = false;
                        }).mouseleave(function () {
                            if (otHander.otmcSlitherLeft) otHander.otmcSlitherLeft = false;
                        });
                        tabs.find(".own-tabs-right").mousedown(function () {
                            if (!otHander.otmcSlitherRight) otHander.otmcSlitherRight = true;
                        }).mouseup(function () {
                            if (otHander.otmcSlitherRight) otHander.otmcSlitherRight = false;
                        }).mouseleave(function () {
                            if (otHander.otmcSlitherRight) otHander.otmcSlitherRight = false;
                        });
                        setInterval(function () {
                            if (otHander.otmcSlitherLeft == true) {
                                otHander.otmc.scrollLeft(otHander.otmc.scrollLeft() - 2);
                            }
                            if (otHander.otmcSlitherRight == true) {
                                otHander.otmc.scrollLeft(otHander.otmc.scrollLeft() + 2);
                            }
                        }, 1);
                    },
                    /**
                     * 选项卡 选中
                     * @param {Object} code
                     */
                    tabsSelect: function (code) {
                        var onItem = otHander.otmc.find("div[code='" + code + "']");
                        var onItemDef = onItem.data("def");
                        otHander.otmc.find(".on").removeClass("on");
                        onItem.addClass("on");
                        def.onSelect(onItemDef);
                    },
                    /**
                     * 关闭选项卡
                     * @param {Object} code 选项卡唯一标识
                     * @param {Object} isCloseBefore 关闭前是否执行用户事件
                     */
                    tabsClose: function (code, isCloseBefore) {
                        var onItem = otHander.otmc.find("div[code='" + code + "']");
                        var onItemDef = onItem.data("def");
                        if (isCloseBefore == true && def.onCloseAfter(onItemDef) == false) return;
                        if (onItemDef.isClose == true) {
                            var prevItem = onItem.prev();
                            def.onCloseAfter(onItemDef);
                            onItem.remove();
                            if (prevItem != null) {
                                var prevItemDef = prevItem.data("def");
                                if (prevItemDef != null) {
                                    otHander.tabsSelect(prevItemDef.code);
                                }

                            }
                        }
                    },
                    /**
                     * 关闭所有选项卡
                     */
                    tabsCloseAll: function () {
                        $.own_confirm("确定关闭所有选项卡?", function () {
                            otHander.otmc.find(".own-tabs-item").each(function () {
                                otHander.tabsClose($(this).attr("code"), false);
                            });
                        });

                    }
                };
                if (!tabs.hasClass("own-tabs")) tabs.addClass("own-tabs");
                tabs.append('<div class="own-tabs-left"></div>');
                tabs.find(".own-tabs-left").append('<i class="own-framework-font of-icon-chevronleft own-tabs-left-icon"></i>');
                tabs.append('<div class="own-tabs-middle"></div>');
                tabs.find(".own-tabs-middle").append('<div class="own-tabs-middle-top-line"></div>');
                tabs.find(".own-tabs-middle").append('<div class="own-tabs-middle-content"></div>');
                tabs.find(".own-tabs-middle").append('<div class="own-tabs-middle-bottom-line"></div>');
                tabs.append('<div class="own-tabs-right"></div>');
                tabs.find(".own-tabs-right").append('<i class="own-framework-font of-icon-chevronright own-tabs-right-icon"></i>');

                tabs.append('<div class="own-tabs-full"></div>');
                tabs.find(".own-tabs-full").append('<i class="own-framework-font of-icon-zoomoutmap"></i>');

                tabs.append('<div class="own-tabs-close"></div>');
                tabs.find(".own-tabs-close").append('<i class="own-framework-font of-icon-clear own-tabs-right-icon"></i>');
                tabs.data("def", def);
                tabs.data("otHander", otHander);
                otHander.otmc = tabs.find(".own-tabs-middle-content");
                otHander.otmcSlither();
                tabs.find(".own-tabs-close").click(function () {
                    otHander.tabsCloseAll();
                });
                tabs.find(".own-tabs-full").click(function () {
                    def.full();
                });
                //选项卡双击事件
                otHander.otmc.dblclick(function (e) {
                    def.dblclick();
                    e.stopPropagation();
                });

            }
            /**
             * 添加选项卡
             * @param {Object} tab
             *
             */
            this.add = function (opt) {
                var tabsDef = tabs.data("def");
                if (tabs.find(".own-tabs-item").length >= tabsDef.maxCount) {
                    $.own_alert("最多只能同时打开" + tabsDef.maxCount + "选项卡!请移除选项卡后在点击导航!");
                    return false;
                }
                var tabsHandler = tabs.data("otHander");
                var def = {
                    code: $.own_guid(), //主键
                    icon: "own-framework-font own-base-iconwindow-2-line", //图标样式
                    name: "选项卡标题", //名称
                    isSelect: false, //是否被选中
                    isClose: false //是否有关闭按钮
                };
                $.extend(def, opt);
                var otmc = tabs.find(".own-tabs-middle-content");
                var inItem = otmc.find(".own-tabs-item[code='" + def.code + "']");
                if (inItem == null || inItem.length == 0) {
                    var item = $("<div class='own-tabs-item'><div>");
                    if (def.isSelect) item.addClass("on");
                    item.attr("code", def.code);
                    item.append('<div class="' + def.icon + ' own-tabs-item-icon"></div>');
                    item.append('<div class="own-tabs-item-name">' + def.name + '</div>');
                    if (def.isClose) {
                        item.append('<div class="own-framework-font of-icon-clear own-tabs-item-close"></div>');
                    } else {
                        item.find(".own-tabs-item-name").css("padding-right", "1em");
                    }
                    otmc.append(item);
                    item.data("def", def);
                    item.find(".own-tabs-item-name,.own-tabs-item-icon").click(function () {
                        var onItemDef = $(this).parents(".own-tabs-item").data("def");
                        tabsHandler.tabsSelect(onItemDef.code);
                    });
                    if (def.isClose) {
                        item.find(".own-tabs-item-close").click(function () {
                            var tabsItemDef = $(this).parents(".own-tabs-item").data("def");
                            if (tabsItemDef.isClose == true && tabsItemDef.code != null && tabsItemDef.code != "") {
                                tabsHandler.tabsClose(tabsItemDef.code);
                            }
                        });
                    }
                    if (def.isSelect) tabsHandler.tabsSelect(def.code);
                    otmc.scrollLeft(100000);
                    tabsDef.onInsert(def);
                    //选项卡双击事件
                    item.dblclick(function (e) {
                        tabsDef.dblclick();
                        e.stopPropagation();
                    });
                } else {
                    tabsHandler.tabsSelect(inItem.data("def").code);
                }
            }
            return this;
        },
        /**
         * 绑定颜色选择
         * @param opt
         */
        own_ace_color: function (opt) {
            var cs = $(this);
            cs.each(function (i, n) {
                var c = $(n);
                var def = {};
                $.extend(def, opt);
                c.colorpicker();
            });
        },
        /**
         * 富文本编辑器
         * @param {Object} opt
         * 参考文献:https://blog.csdn.net/joe__sir/article/details/96894176
         */
        own_ace_editor: function (opt) {
            var c = $(this);
            if (opt == undefined) return c.data("data");
            c.empty();
            var def = {
                height: 200,
                tabsize: 2,
                placeholder: null, //自定义占位符
                shortcuts: false, //禁用快捷键
                disableDragAndDrop: true, //禁用拖拽
                lang: 'zh-CN',
                callbacks: {
                    /**
                     * 初始化后
                     */
                    onInit: function () {

                    }
                }
            };
            $.extend(def, opt);
            if (!c.hasClass("own-ace-editor")) c.addClass("own-ace-editor");
            var editorHandler = {
                /**
                 * 清空
                 */
                empty: function () {
                    var cnext = c.next();
                    cnext.find(".note-editable").empty();
                },
                /**
                 * 获取html
                 */
                html: function () {
                    var cnext = c.next();
                    var ne = cnext.find(".note-editable");
                    return ne.html();
                },
                /**
                 * 插入html
                 * @param {Object} html
                 */
                insertHtml: function (html) {
                    var cnext = c.next();
                    cnext.find(".note-editable").html(html);
                },
                /**
                 * 末尾添加html
                 * @param {Object} html
                 */
                addHtml: function (html) {
                    var cnext = c.next();
                    cnext.find(".note-editable").append(html);
                },
                /**
                 * 插入text
                 * @param {Object} text
                 */
                insertText: function (text) {
                    c.summernote('insertText', text);
                },
                /**
                 * 插入图片
                 * @param {Object} url
                 * @param {Object} filename
                 */
                insertImage: function (url, filename) {
                    c.summernote('insertImage', url, filename);
                },
                /**
                 * 插入元素
                 * @param {Object} node
                 */
                insertNode: function (node) {
                    c.summernote('insertNode', node);
                }
            };
            c.summernote(def);
            this.insertHtml = editorHandler.insertHtml;
            this.addHtml = editorHandler.addHtml;
            this.html = editorHandler.html;
            this.insertText = editorHandler.insertText;
            this.insertImage = editorHandler.insertImage;
            this.insertNode = editorHandler.insertNode;
            c.data("data", this);
        },
        /**
         * 关键字输入框
         * @param opt
         */
        own_ace_input_tags: function (opt) {
            var cs = $(this);
            $.each(cs, function () {
                var tag = $(this);
                var def = {
                    placeholder: "输入后回车确认",
                    height: "5em",
                    apart: ";",//存储分割符
                    value: null
                };
                $.extend(def, opt);
                try {
                    tag.removeClass("ace-input-tags").addClass("ace-input-tags");
                    tag.css("height", def.height);
                    tag.empty();
                    tag.data("def", def);
                    if (tag.attr("value") != null && tag.attr("value") != null) def.value = tag.attr("value");
                    if (tag.attr("val") != null && tag.attr("val") != null) def.value = tag.attr("val");
                    var tag_input = $("<input type='text'/>");
                    tag.append(tag_input);
                    tag_input.tag({placeholder: def.placeholder});
                    if (def.value != null && def.value != "") {
                        var $tag_obj = tag_input.data('tag');
                        var values = def.value.toString().split(def.apart);
                        for (var i = 0; i < values.length; i++) {
                            if (values[i] != null && values[i] != "") {
                                $tag_obj.add(values[i]);
                            }
                        }
                    }
                } catch (e) {

                }
            });

        },
        /**
         * 生成二维码
         * @param {Object} opt
         */
        own_ace_qrcode: function (opt) {
            var c = $(this);
            c.html("二维码生成中..");
            var def = {
                text: null, //二维码内容
                logo: null, //中间图片地址
                height: 250,
                width: 250,
                padding: 10,
                logoHeight: 50,
                logoWdith: 50,
                render: 'canvas', //设置渲染方式，有table和canvas，使用canvas方式渲染性能相对来说比较好
                ecLevel: 'H', //识别度  'L', 'M', 'Q' or 'H'
                left: 0,
                top: 0
            };
            $.extend(def, opt);
            c.css("padding", def.padding + "px");
            c.css("height", def.height + def.padding * 2);
            c.css("width", def.width + def.padding * 2);
            c.css("background-color", "#fff");
            c.css("text-align", "center");
            var qrcode_hander = {
                utf16to8: function (str) {
                    var out, i, len, c;
                    out = "";
                    len = str.length;
                    for (i = 0; i < len; i++) {
                        c = str.charCodeAt(i);
                        if ((c >= 0x0001) && (c <= 0x007F)) {
                            out += str.charAt(i);
                        } else if (c > 0x07FF) {
                            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
                        } else {
                            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
                        }
                    }
                    return out;
                }
            }
            var reg = /^[\u0391-\uFFE5]+$/;
            //如果文本中包含中文进行转码
            if (reg.test(def.text)) def.text = qrcode_hander.utf16to8(def.text);
            if (def.logo != null && def.logo != "") {
                var image = new Image();
                image.src = def.logo;
                image.style = "background-color: white;";
                def.image = image;
                image.onload = function () {
                    c.empty();
                    c.qrcode(def);
                    var qrcanvas = c.find("canvas:eq(0)")[0];
                    var ctx = qrcanvas.getContext('2d');

                    ctx.drawImage(def.image,
                        (qrcanvas.width - def.logoWdith) / 2,
                        (qrcanvas.height - def.logoHeight) / 2,
                        def.logoWdith, def.logoHeight);
                }
            } else {
                c.empty();
                c.qrcode(def);
            }
        },
        /**
         * 放大显示图片
         * @param opt
         */
        own_image_show: function (opt) {
            var images = $(this);
            var def = {};
            $.extend(def, opt);
            images.picbox(null, function (el) {
                return [el.src, el.title];
            }, null);
        },
        /**
         * 绑定省/市/县地址选择
         * @param opt
         */
        own_ace_address: function (opt) {
            var c = $(this);
            var def = {
                postType: "2",//提交的数据类型 1=application/json;charset=utf-8 2=application/x-www-form-urlencoded;charset=utf-8
                prov: null,//当前省值
                provPn: "prov",//省post参数
                city: null,//当前市值
                cityPn: "city",//市post参数
                county: null,//当前县值
                countyPn: "county",//县post参数
                town: null,//当前镇值
                townPn: "town",//镇post参数
                village: null,//当前村值
                villagePn: "village",//村post参数
                rootPidValue: "0",//根节点的父节点值
                postPidName: "pid",//当获取数据的时候父节点对应服务器的参数名称
                fieldValue: "id",//地址ID
                fieldText: "name",//地址名称
                url: null//获取数据的地址
            };
            $.extend(def, opt);
            var defHandler = {
                prov: null,
                city: null,
                county: null,
                town: null,
                village: null,
                /**
                 * 获取地址数据
                 * @param back
                 */
                loadAddress: function (pid, back) {
                    var data = {};
                    if (pid == null || pid == "" || pid == "-1") {
                        back([]);
                        return;
                    }
                    data[def.postPidName] = pid;
                    $.own_post({
                        url: def.url,
                        postType: def.postType,
                        data: data,
                        call: function (res) {
                            if (res.code == "1") {
                                if (back != null) back(res.result);
                            } else {
                                $.own_error(res.msg);
                            }
                        }
                    });
                },
                /**
                 * 绑定控件
                 * @param select 选择控件
                 * @param rows 数据源
                 * @param pchooseText 默认显示
                 * @param defValue 默认值
                 * @param selected 选定后回调
                 */
                bindAddress: function (select, rows, defValue) {
                    var datas = [];
                    var pchoose = {};
                    pchoose[def.fieldValue] = "";
                    if (select.attr("dtype") == "prov") {
                        pchoose[def.fieldText] = "--省--";
                    } else if (select.attr("dtype") == "city") {
                        pchoose[def.fieldText] = "--市--";
                    } else if (select.attr("dtype") == "county") {
                        pchoose[def.fieldText] = "--县/区--";
                    } else if (select.attr("dtype") == "town") {
                        pchoose[def.fieldText] = "--镇--";
                    } else if (select.attr("dtype") == "village") {
                        pchoose[def.fieldText] = "--村--";
                    }

                    datas.push(pchoose);
                    select.empty();
                    $.each(rows, function () {
                        datas.push(this);
                    });
                    $.each(datas, function () {
                        var item = this;
                        select.append("<option value='" + item[def.fieldValue] + "'>" + item[def.fieldText] + "</option>");
                    });
                    select.val(defValue);
                },
                /**
                 * 获取提交的参数
                 */
                getPostData: function () {
                    var data = {};
                    data[def.provPn] = defHandler.prov.val();
                    data[def.cityPn] = defHandler.city.val();
                    data[def.countyPn] = defHandler.county.val();
                    data[def.townPn] = defHandler.town.val();
                    data[def.villagePn] = defHandler.village.val();
                    return data;
                }
            };
            if (!c.hasClass("own_ace_address")) c.addClass("own_ace_address");
            if (c.attr("prov") != null) def.prov = c.attr("prov");
            if (c.attr("city") != null) def.city = c.attr("city");
            if (c.attr("county") != null) def.county = c.attr("county");
            if (c.attr("town") != null) def.town = c.attr("town");
            if (c.attr("village") != null) def.village = c.attr("village");
            defHandler.prov = $("<select dtype='prov'  class='form-control chosen-select'></select>");
            defHandler.city = $("<select  dtype='city' class='form-control chosen-select'></select>");
            defHandler.county = $("<select  dtype='county' class='form-control chosen-select'></select>");
            defHandler.town = $("<select  dtype='town' class='form-control chosen-select'></select>");
            defHandler.village = $("<select  dtype='village' class='form-control chosen-select'></select>");
            c.append(defHandler.prov).append(defHandler.city).append(defHandler.county).append(defHandler.town).append(defHandler.village);

            defHandler.loadAddress(def.rootPidValue, function (rows) {
                defHandler.bindAddress(defHandler.prov, rows, def.prov);
            });
            defHandler.loadAddress(def.prov, function (rows) {
                defHandler.bindAddress(defHandler.city, rows, def.city);
            });
            defHandler.loadAddress(def.city, function (rows) {
                defHandler.bindAddress(defHandler.county, rows, def.county);
            });
            defHandler.loadAddress(def.county, function (rows) {
                defHandler.bindAddress(defHandler.town, rows, def.town);
            });
            defHandler.loadAddress(def.town, function (rows) {
                defHandler.bindAddress(defHandler.village, rows, def.village);
            });


            defHandler.prov.change(function () {
                defHandler.loadAddress(defHandler.prov.val(), function (rows) {
                    defHandler.bindAddress(defHandler.city, rows, "");
                });
                defHandler.bindAddress(defHandler.county, [], "");
                defHandler.bindAddress(defHandler.town, [], "");
                defHandler.bindAddress(defHandler.village, [], "");
            });
            defHandler.city.change(function () {
                defHandler.loadAddress(defHandler.city.val(), function (rows) {
                    defHandler.bindAddress(defHandler.county, rows, "");
                });
                defHandler.bindAddress(defHandler.town, [], "");
                defHandler.bindAddress(defHandler.village, [], "");
            });

            defHandler.county.change(function () {
                defHandler.loadAddress(defHandler.county.val(), function (rows) {
                    defHandler.bindAddress(defHandler.town, rows, "");
                });
                defHandler.bindAddress(defHandler.village, [], "");
            });

            defHandler.town.change(function () {
                defHandler.loadAddress(defHandler.town.val(), function (rows) {
                    defHandler.bindAddress(defHandler.village, rows, "");
                });

            });


            c.data("defHandler", defHandler);
        },
        /**
         * 搜索下拉预选框绑定
         * api：https://github.com/twitter/typeahead.js/blob/master/doc/jquery_typeahead.md
         * @param opt
         */
        own_ace_typeahead: function (opt) {
            var $input = $(this);
            var def = {
                $input: $input,//文本框对象【必填】
                url: null,//数据源请求地址【url与data选填】
                data: [],//数据集合【url与data选填】
                limit: 1000,//显示预选记录数
                filterName: null,//设置用户筛选字段得列名称
                displayName: null,//设置选中后的提取对象的字段名称
                keyWordName: "keyWord",//关键字名称
                select: function (data) {//选定记录后触发

                },
                change: function () {//当输入失去焦点并且值自最初获得焦点以来已更改时触发

                },
                beforeRequest: function () {//数据请求前
                    return {};
                },
                display: function (data) {//设置数据渲染
                    return data;
                }
            }
            $.extend(def, opt);
            def.$input.typeahead({
                hint: true,
                highlight: true,
                minLength: 0
            }, {
                limit: def.limit,
                source: function () {
                    return function findMatches(keyWord, back) {
                        var matches = [];
                        if (def.url == null) {
                            if (def.data != null && def.data.length > 0) {
                                if (keyWord == null || keyWord == "") {
                                    matches = def.data;
                                } else {
                                    if (def.filterName != null && def.filterName != "") {
                                        $.each(def.data, function (i, n) {
                                            if (n[def.filterName].indexOf(keyWord) > -1) {
                                                matches.push(n);
                                            }
                                        });
                                    }
                                }
                            }
                        } else {
                            var param = {};
                            param[def.keyWordName] = keyWord;
                            param["limit"] = def.limit;
                            var brParam = def.beforeRequest();
                            if (brParam != null) {
                                for (var key in brParam) {
                                    param[key] = brParam[key];
                                }
                            }
                            $.own_post({
                                url: def.url,
                                async: false,
                                data: param,
                                call: function (res) {
                                    if (res.code == "1") {
                                        $.each(res.result, function (i, n) {
                                            matches.push(n);
                                        });
                                    }
                                }
                            });
                        }

                        back(matches);
                    }
                }(),
                display: function (data) {
                    return def.display(data);
                }
            });
            def.$input.bind('typeahead:select', function (ev, data) {
                if (def.displayName != null) {
                    def.$input.typeahead("val", data[def.displayName]);
                }
                def.select(data);
            });
            def.$input.bind('typeahead:change', function (ev, data) {
                def.change(data);
            });
            def.$input.bind('typeahead:autocomplete', function (ev, data) {
                if (def.displayName != null) {
                    def.$input.typeahead("val", data[def.displayName]);
                }
                def.select(data);
            });
        }
    });
    $.extend({
        /**
         * api地址:https://www.jquery-confirm.cn/
         * 弹出窗口
         * @param opt
         */
        own_window: function (opt) {
            var def = {
                id: null,
                title: null,
                size: 3,
                url: null,
                actionType: "get",
                type: "orange", //为模式着色，为用户提供成功/失败/警告的提示，可用选项为：'default,blue, green, red, orange, purple & dark'
                fullScreen: false, //全屏
                animateFromElement: false, //从单击的元素中为模态制作动画
                containerFluid: true, //如果为true，将使用容器流体布局，以使用整个浏览器宽度。
                container: 'body', //指定为jconfirm生成的HTML内容应附加在何处。 默认情况下，它附加在文档的 <body>中
                closeIcon: true, //默认情况下，如果两个按钮均为假，则closeIcon可见。（对话模式）。 通过将此值设置为true可以显示closeIcon。
                icon: "ace-icon fa fa-desktop",
                titleClass: "own-window-title",
                backgroundDismissAnimation: "glow", //shake、glow 当用户单击开箱即用时执行的动画，以引起用户的注意。 设置为""为无效果
                dragWindowBorder: true, //模态应该限制在窗口内
                dragWindowGap: 5, //模态和窗口之间的可拖动间隙，默认为15px
                bgOpacity: 0.5, //如果为null，则应用主题的默认bg不透明度。
                theme: "light", //对话框的颜色主题。可能的选项是'light', 'dark', 'material' & 'bootstrap'
                animationBounce: 1, //添加弹跳打开动画， 1 = No bounce
                offsetTop: 5,
                offsetBottom: 5,
                typeAnimated: false,
                jc: null,
                onOpen: function () {
                    var lastdialog = $(".jconfirm-box[role='dialog']:last");
                    lastdialog.addClass("own-ace-window");
                    lastdialog.attr("id", def.id).data("opt", def);
                },
                hide: function (result) {

                },
                contentLoaded: function (data, status, xhr) { //仅在通过Ajax加载内容时使用。在$ .ajax的始终回调中被调用

                },
                onContentReady: function (e) {
                    if (def.fullScreen == true) {
                        var lastdialog = $(".jconfirm-box[role='dialog']:last");
                        var formContent = lastdialog.find(".own-form-content:eq(0),.mm-form-content:eq(0)");
                        if (formContent.length > 0) {
                            formContent.attr("fullheight", "1");
                            formContent.css("height", ($(window).height() - 100) + "px");
                        }
                    }
                    def.contentReady();
                },
                onDestroy: function () {

                },
                contentReady: function () {

                }
            };
            $.extend(def, opt);
            if (def.fullScreen) def.size = "99.5%";
            if (def.id == null) def.id = "w_" + $.own_guid();
            if ((typeof def.size == 'string') && def.size.constructor == String) {
                def.useBootstrap = false;
                if (def.size.toString().indexOf("%")) {
                    def.boxWidth = def.size;
                }
            } else {
                switch (def.size) {
                    case 1:
                        def.columnClass = "xsmall";
                        break;
                    case 2:
                        def.columnClass = "small";
                        break;
                    case 3:
                        def.columnClass = "medium";
                        break;
                    case 4:
                        def.columnClass = "large";
                        break;
                    case 5:
                        def.columnClass = "xlarge";
                        break;
                }
            }
            if (def.url != null && def.url != "") {
                if (def.actionType == "post") {
                    def.content = function () {
                        var twin = this;
                        $.own_post({
                            url: def.url,
                            data: def.data,
                            dataType: "html",
                            postType: "2",
                            call: function (res) {
                                twin.$content.html(res);
                            }
                        });
                    }
                } else {
                    if (def.url.indexOf("http://") < 0 && def.url.indexOf("https://") < 0) {
                        def.url = own.config.webRoot + def.url;
                    }
                    def.content = function () {
                        var twin = this;
                        $.own_get(def.url, function (html) {
                            if (html == null) html = "";
                            html = html.replace("</html>", "");
                            html = html.replace(/\<html.*?\>/g, "");
                            twin.$content.html(html);
                        });
                    }

                }
            }
            def.jc = $.dialog(def);
        },
        /**
         * 关闭最后一个窗口
         */
        own_window_close_last: function (result) {
            var lastdialog = $(".jconfirm-box[role='dialog'].own-ace-window:last");
            var opt = lastdialog.data("opt");
            opt.jc.close();
            opt.hide(result);
        },
        /**
         * 关闭指定ID的窗口
         */
        own_window_close_id: function (id, result) {
            var lastdialog = $("#" + id);
            var opt = lastdialog.data("opt");
            opt.jc.close();
            opt.hide(result);
        },
        /**
         * post提交
         * @param {Object} opt
         */
        own_post: function (opt) {
            var def = {
                type: "POST",
                url: null,
                data: null,
                cache: false,
                dataType: "json",
                postType: "1",//提交的数据类型 1=application/json;charset=utf-8 2=application/x-www-form-urlencoded;charset=utf-8
                isEmptyForNull: false,
                contentType: 'application/json;charset=utf-8',
                async: true,
                alwayscall: function (res) { //不管是否请求正确都执行此函数

                },
                call: function (res) {

                },
                success: function (res) {
                    if (res.code == "-1000") {
                        $.own_loading_close();
                        $.own_confirm("您的会话已过期,是否重新登录?", function () {
                            window.location = baseconfig.webLevel+"/pf/PlatformLogin/login-page";
                        });
                        return;
                    }
                    def.alwayscall(res);
                    def.call(res);
                },
                error: function (req, a, b, c) {

                }
            };
            $.extend(def, opt);
            if (def.postType == "2") {
                def.contentType = "application/x-www-form-urlencoded;charset=utf-8";
            }
            if (def.url != null &&
                def.url != "" &&
                def.url != "/" &&
                def.url.indexOf("http://") < 0 &&
                def.url.indexOf("https://") < 0) {
                def.url = baseconfig.webLevel + def.url;
            }
            if (def.data != null && def.isEmptyForNull == true) {
                for (var key in def.data) {
                    if (def.data[key] == "") {
                        def.data[key] = null;
                    }
                }
            }
            if (def.contentType != null && def.contentType.indexOf("application/json") > -1) {
                if (def.data != null) {
                    def.data = JSON.stringify(def.data);
                }
            }
            return $.ajax(def);
        },
        /**
         * get请求
         * @param url
         * @param back
         */
        own_get: function (url, back) {
            var def = {
                type: "GET",
                url: url,
                cache: false,
                dataType: "html",
                success: function (html) {
                    if (back != null) back(html);
                }
            };
            if (def.url != null) def.url = baseconfig.webLevel + def.url;
            $.ajax(def);
        },
        /**
         * 获取唯一标识
         * @returns {string}
         */
        own_guid: function () {
            var s4 = function () {
                return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
            }
            return ("g" + s4() + s4() + s4() + s4() + s4() + s4() + s4() + s4());
        },
        /**
         * 普通消息提示框
         * @param msg 消息
         * @param back 关闭后回调函数
         */
        own_alert: function (msg, back) {
            $.alert({
                title: '提示',
                animateFromElement: false,
                content: msg,
                type: "blue",
                backgroundDismiss: true,
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
        /**
         * 错误提示框
         * @param msg 错误信息
         * @param back 关闭后回调函数
         */
        own_error: function (msg, back) {
            $.alert({
                title: '错误',
                animateFromElement: false,
                content: msg,
                type: "red",
                buttons: {
                    'confirm': {
                        text: '关闭错误提示框',
                        btnClass: 'btn-danger',
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
        /**
         * 询问提示框
         * @param msg
         * @param confirm 确认
         * @param cancel 取消
         */
        own_confirm: function (msg, confirm, cancel) {
            $.alert({
                title: '询问?',
                animateFromElement: false,
                content: msg,
                type: "orange",
                buttons: {
                    'confirm': {
                        text: '&nbsp;&nbsp;&nbsp;确定&nbsp;&nbsp;&nbsp;',
                        btnClass: 'btn-primary',
                        action: function () {
                            this.setCloseAnimation('top');
                            if (confirm != null) confirm();
                        }
                    },
                    'cancel': {
                        text: '&nbsp;取消&nbsp;',
                        action: function () {
                            this.setCloseAnimation('top');
                            if (cancel != null) cancel();
                        }
                    }
                }
            });
        },
        /**
         * 成功提示框
         * @param msg 信息
         * @param back 关闭后回调函数
         */
        own_success: function (msg, back) {
            $.alert({
                title: '提示',
                animateFromElement: false,
                content: msg,
                type: "green",
                autoClose: 'confirm|2000',
                backgroundDismiss: true,
                buttons: {
                    'confirm': {
                        text: '关闭提示框',
                        btnClass: 'btn-success',
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
        /**
         * 显示加载中
         */
        own_loading: function () {
            $(".own-loading").remove();
            var loading = $("<div class='own-loading'></div>");
            top.$("body").append(loading);
        },
        /**
         * 关闭加载中
         */
        own_loading_close: function () {
            $(".own-loading").remove();
        },
        /**
         * 获取请求地址栏的参数
         * @param  name 参数名称
         */
        own_url_param: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        },
        /**
         * 获取一个cookie
         * @param name
         */
        own_cookie_get: function (name) {
            var value = top.$.cookie(name);
            if (value == "null" || value == "") value = null;
            return value;
        },
        /**
         * 添加一个cookie
         * @param name
         * @param value
         */
        own_cookie_add: function (name, value) {
            top.$.cookie(name, null, {
                path: '/'
            });
            top.$.cookie(name, value, {
                path: '/',
                expires: 365 * 100
            });
        },
        /**
         * 移除一个cookie
         * @param name
         */
        own_cookie_remove: function (name) {
            top.$.cookie(name, null, {
                path: '/'
            });
        },
        /**
         * 获取上传文件的请求地址
         * @param fileId 文件ID
         * @param suffix 后缀名
         */
        own_getFileUrl: function (fileId, suffix) {
            var fileUrl = null;
            if ($.own_isNullOrEmpty(own.config.loadFileHost)) return null;

            if (own.config.loadFileHost.indexOf("http://") == 0 || own.config.loadFileHost.indexOf("https://") == 0) {
                fileUrl = own.config.loadFileHost + "/" + fileId;
            } else {
                fileUrl = baseconfig.webLevel + own.config.loadFileHost + "/" + fileId;
            }
            if (!$.own_isNullOrEmpty(suffix)) {
                fileUrl += suffix;
            }
            return fileUrl;
        },
        /**
         * 判断字符串是否为空或者null
         * @param value
         */
        own_isNullOrEmpty: function (value) {
            return (value == null || value == "");
        },
        /**
         * 浏览器执行winfrom代码
         * @param command 执行类型标识
         * @param message 执行请求参数
         */
        own_execute_win_code: function (command, message) {
            try {
                var winMsg = {
                    command: command,
                    message: typeof (message) == 'string' ? message : JSON.stringify(message)
                };
                var event = new MessageEvent("InvokeServerFunction", {
                    view: window,
                    bubbles: false,
                    cancelable: false,
                    data: JSON.stringify(winMsg)
                });
                document.dispatchEvent(event);

            } catch (e) {
                $.own_error(e.toString());
            }

        }
    });
})(jQuery);
$(function () {
    if (top.$("body:eq(0)").attr("wresize") == null) {
        top.$("body:eq(0)").attr("wresize", "1");
        $(window).resize(function () {
            var wheight = $(window).height() - 90;
            top.$(".own-form-content[fullheight='1']").each(function () {
                $(this).css("height", wheight + "px");
            });
        });
    }
});
