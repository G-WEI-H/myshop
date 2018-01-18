<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="mesage" type="java.lang.String" required="true" description="错误信息提示标签" %>

<c:if test="${mesage != null && mesage.contains('成功')}">
    <div class="alert alert-success alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            ${mesage}
    </div>
</c:if>

<c:if test="${mesage != null && mesage.contains('失败')}">
    <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            ${mesage}
    </div>
</c:if>