<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <!-- Main content -->
        <section class="content">
            <sys:message mesage="${message}"/>

            <div class="row" style="padding: 0px">
                <div class="col-md-12">
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">用户列表</h3>
                            <div class="box">
                                <div class="box-header">
                                    <a href="/user/form"  type="button" class="btn btn-small btn-default fa fa-plus">&nbsp;新增</a>
                                    <a href="/"   type="button"class="btn btn-small btn-danger fa fa-remove">&nbsp;选删</a>
                                    <div class="box-tools">
                                        <div class="input-group input-group-sm" style="width: 150px;">
                                            <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">

                                            <div class="input-group-btn">
                                                <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body table-responsive no-padding mailbox-controls mailbox-messages">
                                    <table class="table table-hover table-striped">
                                        <tr>
                                            <th>
                                          <input name="selectall" type="checkbox" onclick="checkClick(this)"> &nbsp;全选
                                            </th>
                                            <th>用户名</th>
                                            <th>手机号</th>
                                            <th>邮箱</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th>操作用户</th>
                                        </tr>
                                        <c:forEach items="${list}" var="tbuser">
                                        <tr>
                                            <td><input type="checkbox" class="ip" name="stuCheckBox" value="${tbuser.id }"></td>
                                            <td>${tbuser.username}</td>
                                            <td>${tbuser.phone}</td>
                                            <td>${tbuser.email}</td>
                                            <td><fmt:formatDate value="${tbuser.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                                            <td><fmt:formatDate value="${tbuser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>
                                                <a href="/user/form?id=${tbuser.id} "  type="button" class="btn  btn-primary btn-xs fa fa-edit ( ">修改</a>
                                                <a href="/user/delete?id=${tbuser.id}" id="delete" onclick="deleted"  type="button" class="btn  btn-danger btn-xs fa fa-remove">删除</a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
</div>
<jsp:include page="/WEB-INF/views/includes/copyright.jsp" />
<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
<script type="text/javascript">
    function checkClick(inp){
        var is = inp.checked;
        var arr = document.getElementsByClassName("ip");
        for(index in arr){
            arr[index].checked = is;
        }
    }
</script>
</body>
</html>
