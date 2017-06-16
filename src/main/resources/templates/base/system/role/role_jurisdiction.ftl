<!DOCTYPE html>
<html>
<head>
<#include "${ctxPath}/base/include/head.ftl" />
<#include "${ctxPath}/base/include/jurisdiction.ftl" />
<!-- 页面独有文件 -->
<link rel="stylesheet" href="${ctxPath}/static/plugin/h-ui/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css?v=${v}" type="text/css">
<script type="text/javascript" src="${ctxPath}/static/plugin/h-ui/lib/zTree/v3/js/jquery.ztree.all.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctxPath}/static/base/js/role/role_jurisdiction.js?v=${v}"></script>
</head>
<body>
<article class="page-container">
    <ul id="tree" class="ztree"></ul>
    <input class="btn btn-primary radius" type="button" onclick="save();" value="保存">
</article>
</body>
<script language="JavaScript">
    var type = "${type}";
    var id = "${id}";
</script>
</html>