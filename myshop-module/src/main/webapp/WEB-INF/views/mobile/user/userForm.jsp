<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>商城管理 | 新增用户</title>
    <jsp:include page="/WEB-INF/views/includes/header.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
   <sys:nav/>
    <sys:menu parentTitle="用户管理" subTitle="用户列表"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                ${tbUser.id!=null?"修改":"添加"}用户
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"> 首页</a></li>
                <li><a href="#"> 用户管理</a></li>
                <li class="active">${tbUser.id!=null?"修改":"添加"}用户</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
          <sys:message mesage="${message}"/>

            <div class="row">
                <div class="col-md-12">
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title"></h3>
                        </div>

                        <form:form id="formId" action="/user/save" method="post" modelAttribute="tbUser" class="form-horizontal">
                            <form:hidden path="id" />
                            <div class="box-body">
                            <div class="form-group">
                                <form:label path="username" class="col-sm-2 control-label " >用户名</form:label>
                                <div class="col-sm-10">
                                    <form:input path="username" class="form-control required" placeholder="用户名"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="password" class="col-sm-2 control-label">密码</form:label>
                                <div class="col-sm-10">
                                    <form:password path="password" class="form-control" placeholder="密码"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="phone" class="col-sm-2 control-label">手机号</form:label>
                                <div class="col-sm-10">
                                    <form:input path="phone" class="form-control" placeholder="手机号"/>
                                </div>
                            </div>
                            <div class="form-group ">
                                <form:label path="email" class="col-sm-2 control-label ">邮箱</form:label>
                                <div class="col-sm-10">
                                    <form:input path="email" class="form-control required email" placeholder="邮箱"/>
                                </div>
                            </div>
                        </div>

                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>

                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
</div>
<jsp:include page="/WEB-INF/views/includes/copyright.jsp" />
<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
<script src="/static/myshop/user/uservalidate.js"></script>
<script >
    $(function () {
        UserValidator.validate();
    });
</script>

</body>
</html>
