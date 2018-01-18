var UserValidator=function () {
    var handleValidate = function () {
        $("#formId").validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
                element.parent().parent().attr("class", "form-group has-error");
            },

            rules: {
                username: {
                    required: true,
                    remote: {
                        url: "/user/check",
                        type: "post",
                        dataType: "json",
                        data: {
                            username: function () {
                                return $("#username").val();
                            }
                        }
                    }
                },
                phone: {
                    required: true,
                    remote: {
                        url: "/user/check",
                        type: "post",
                        dataType: "json",
                        data: {
                            phone: function () {
                                return $("#phone").val();
                            }
                        }
                    }
                },
                email: {
                    required: true,
                    remote: {
                        url: "/user/check",
                        type: "post",
                        dataType: "json",
                        data: {
                            email: function () {
                                return $("#email").val();
                            }
                        }
                    }
                }
            },
            messages: {
                username: {
                    required: "请输入用户名",
                     remote: "用户名已存在，请重新输入"
            },
            phone: {
                required: "请输入手机号",
                remote: "手机号已存在，请重新输入"
            },
                email: {
                    required: "请输入邮箱",
                    remote: "邮箱已存在，请重新输入"
                }
            }
        });
    };
    return {
        validate:function () {
            handleValidate();
        }
    }
}();