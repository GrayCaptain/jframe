﻿(function () {
    mvcApp.notification.toast = function (message) {
        $.toast({
            message: message,
            css: "info"
        });
    };

    mvcApp.notification.toastSuccess = function (message) {
        $.toast({
            message: message,
            css: "success"
        });
    };

    mvcApp.notification.toastError = function (message) {

        if (message == undefined || message === "") {
            message = "出错啦";
        }
        if ($('.toast').length > 0) {
            $('.toast').remove();
        }
        $.toast({
            message: message,
            css: "error",
            displayDuration: 3000
        });
    };

    mvcApp.notification.dialog = function (id, title, options) {
        var $dialog = $("#" + id);
        if ($dialog.length === 0) {
            $dialog = $("<div id='" + id + "' class='dialog' title='" + title + "'></div>").appendTo("body");
        }
        if (options.autoOpen == undefined) {
            options.autoOpen = false;
        }
        if (options.modal == undefined) {
            options.modal = true;
        }
        if (options.close == undefined) {
            options.close = function () {
                $dialog.dialog("destroy");
                $dialog.remove();
            };
        } else {
            (function (closeFunc) {
                options.close = function () {
                    closeFunc();
                    $dialog.dialog("destroy");
                    $dialog.remove();
                };
            })(options.close);
        }
        var createFunc = options.create;
        options.create = function () {
            createFunc && createFunc();
        };
        $dialog.dialog(options);
        $dialog.dialog("open");
        $(".ui-dialog").resizable("option", "disabled", true);
    };

    mvcApp.notification.alert = function (title, content, onClose, width, height) {

        mvcApp.notification.messageBox(title, content, {
            "OK": function () {
                $(this).dialog("close");
            }
        }, width, height, onClose);
        $(".ui-dialog").resizable("option", "disabled", true);
    };

    mvcApp.notification.alertError = function (message, title, onClose) {
        if (title == undefined) {
            title = "出错啦";
        }
        if (message == undefined || message === '') {
            message = "糟了，服务器出错了~请再试试吧，如果一直出错，还可以联系客服解决哦！";
        }
        message = "<p class='error'>" + message + "</p>";
        mvcApp.notification.alert(title, message, onClose);
    };

    mvcApp.notification.alertInfo = function (message, onClose) {
        if (message == undefined || message === '') {
            message = "糟了，服务器出错了~请再试试吧，如果一直出错，还可以联系客服解决哦！";
        }
        this.alert('提示', message, onClose);
    };

    mvcApp.notification.messageBox = function (title, content, buttons, width, height, onClose) {
        var id = "messagebox" + new Date().getTime();
        width = width == undefined ? 400 : width;
        height = height == undefined ? 200 : height;
        var $dialog = $("<div id='" + id + "' class='message-box'>" + content + "</div>");
        $dialog.appendTo("body");
        $dialog.dialog({
            modal: true,
            title: title,
            width: width,
            minHeight: height,
            height: 'auto',
            buttons: buttons,
            resizable: false,
            close: function () {
                onClose && onClose();
                $(this).dialog("destroy").remove();
            }
        });
    };

})();






