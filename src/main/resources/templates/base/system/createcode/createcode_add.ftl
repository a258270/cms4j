<!DOCTYPE html>
<html>
<head>
<#include "${ctxPath}/base/include/head.ftl" />
<#include "${ctxPath}/base/include/jurisdiction.ftl" />
    <!-- 页面独有文件 -->
    <script type="text/javascript" src="${ctxPath}/static/base/js/createcode/createcode_add.js?v=${v}"></script>
</head>
<body>
<article class="page-container">
    <form method="post" class="form form-horizontal" id="form">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3 c-red">主键策略：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <label class="c-red">无需添加主键，系统默认增加内容为UUID的主键ID字段</label>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3 c-red">注意事项①：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <label class="c-red">当是否前端录入为“否”时，生成的代码的Service中会给这些字段加上默认值，当数据类型为String、Integer、Double时，默认值即为设置值，当数据类型为Date、Datetime时，默认值为当前时间，当数据类型为Boolean时，若设置默认值则默认值为true，若不设置，则为false</label>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>属性名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入属性名称" id="NAME" name="NAME">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入备注" id="REMARK" name="REMARK">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>数据类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select input-text radius" size="1" id="DATATYPE" name="DATATYPE" id="selectbox">
                    <option value="String" selected>String</option>
                    <option value="Integer">Integer</option>
                    <option value="Double">Double</option>
                    <option value="Date">Date</option>
                    <option value="Datetime">Datetime</option>
                    <option value="Boolean">Boolean</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否为必填项：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select input-text radius" size="1" id="ISREQUIRED" name="ISREQUIRED" id="selectbox">
                    <option value="是" selected>是</option>
                    <option value="否">否</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否前台录入：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select input-text radius" size="1" id="ISFRONT" name="ISFRONT" id="selectbox">
                    <option value="是" selected>是</option>
                    <option value="否">否</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">默认值：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="无" placeholder="请输入默认值" id="DEFUALT" name="DEFUALT" disabled>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否列表可见：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select input-text radius" size="1" id="ISLIST" name="ISLIST" id="selectbox">
                    <option value="是" selected>是</option>
                    <option value="否">否</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否为搜索项：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select input-text radius" size="1" id="ISSEARCH" name="ISSEARCH" id="selectbox">
                    <option value="是">是</option>
                    <option value="否" selected>否</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>检索条件：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select input-text radius" size="1" id="SEARCHCONDITION" name="SEARCHCONDITION" id="selectbox">
                    <option value="无" selected>无</option>
                    <option value="=">=</option>
                    <option value="like">like</option>
                    <option value=">">></option>
                    <option value="<"><</option>
                    <option value=">=">>=</option>
                    <option value="<="><=</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否为字典：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select input-text radius" size="1" id="ISDIC" name="ISDIC" id="selectbox">
                    <option value="是">是</option>
                    <option value="否" selected>否</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>字典内码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="无" placeholder="请输入字典内码" id="DICCODE" name="DICCODE" disabled>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="button" onclick="save();" value="提交">
            </div>
        </div>
    </form>
</article>
</body>
</html>