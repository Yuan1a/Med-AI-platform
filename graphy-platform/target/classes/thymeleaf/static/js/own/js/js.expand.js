/**
 * 格式化日期 YYYY-mm-dd HH:MM表示2019-06-06 19:45
 * @param fmt
 * @returns {*}
 */
Date.prototype.own_format = function (fmt) {
    var ret;
    var date = this;
    const opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        }
    }
    return fmt;
}
/**
 * 字符串替换
 * @param {Object} oldStr 原字符
 * @param {Object} newStr 新字符
 */
String.prototype.own_Replace = function (oldStr, newStr) {
    var str = this;
    if (str == null || str == "" || oldStr == null || oldStr == "") return str;
    return str.replace(new RegExp(oldStr, "gi"), newStr);
}
/**
 * 判断是否为日期格式(yyyy-MM-dd)
 */
String.prototype.own_IsDate = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断是否为日期格式(yyyy-MM-dd HH:mm:ss)
 */
String.prototype.own_IsDateTime = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r = str.match(reg);
    if (r == null) {
        return false;
    } else {
        return true;
    }
}
/**
 * 判断是否为日期格式(HH:mm:ss)
 */
String.prototype.own_IsTime = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^((20|21|22|23|[0-1]\d)\:[0-5][0-9])(\:[0-5][0-9])?$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断输入的字符是否为英文字母
 */
String.prototype.own_IsLetter = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^[a-zA-Z]+$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断输入的字符是否为整数
 */
String.prototype.own_IsInteger = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^[-+]?\d*$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断输入的字符是否为双精度
 */
String.prototype.own_IsDouble = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^[-\+]?\d+(\.\d+)?$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断输入的字符是否为:a-z,A-Z,0-9
 */
String.prototype.own_IsString = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^[a-zA-Z0-9_]+$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断输入的字符是否为中文
 */
String.prototype.own_IsChinese = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^[\u0391-\uFFE5]+$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断输入的EMAIL格式是否正确
 */
String.prototype.own_IsEmail = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断输入的邮编(只能为六位)是否正确
 */
String.prototype.own_IsZIP = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^\d{6}$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断电话
 */
String.prototype.own_IsPhone = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断手机
 */
String.prototype.own_IsMobile = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}
/**
 * 判断url
 */
String.prototype.own_IsUrl = function () {
    var str = this;
    if (str == null || str == "") return false;
    var reg = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
    var r = str.match(reg);
    if (r == null) {
        return false
    } else {
        return true;
    }
}