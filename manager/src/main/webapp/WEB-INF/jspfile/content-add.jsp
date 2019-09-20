<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="jsfile/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="jsfile/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="jsfile/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="courseAddForm" class="itemForm" method="post">
		<input type="hidden" name="cateId"/>
	    <table cellpadding="5">
	        <tr>
	            <td>课程标题:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 680px;"></input></td>
	        </tr>
	        <tr>
	            <td>课程描述:</td>
	            <td><input class="easyui-textbox" type="text" name="description" data-options="multiline:true,validType:'length[0,500]'" style="height:60px;width: 680px;"></input>
	            </td>
	        </tr>
	        <%--<tr>--%>
	            <%--<td>图片:</td>--%>
	            <%--<td>--%>
	                <%--<input type="hidden" name="pic" />--%>
	                <%--<a href="javascript:void(0)" class="easyui-linkbutton onePicUpload">图片上传</a>--%>
	            <%--</td>--%>
	        <%--</tr>--%>
	        <tr>
	            <td>内容:</td>
	            <td>
	                <textarea style="width:680px;height:300px;visibility:hidden;" name="content"></textarea>
	            </td>
	        </tr>
            <tr>
                <td></td>
                <td>
                    <input type="checkbox" name="isnews" value="1" />是否最新上线
                    <input type="checkbox" name="issupreme" value="1" />是否精品
                </td>
            </tr>
	    </table>
	</form>
	<div style="position:absolute;bottom:30px;right:50px;">
	    <a href="javascript:void(0)" class="easyui-linkbutton" style="width: 64px;height: 30px;" onclick="courseAddPage.submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" style="width: 64px;height: 30px;" onclick="courseAddPage.clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
    var courseAddEditor;
    $(function(){
        courseAddEditor = TT.createEditor("#courseAddForm [name=content]");
        // TT.initOnePicUpload();
        $("#courseAddForm [name=cateId]").val($("#catetree").tree("getSelected").id);
    });

    var courseAddPage  = {
        submitForm : function (){
            if(!$('#courseAddForm').form('validate')){
            	$.messager.alert('提示','表单还未填写完成!');
            	return ;
            }
            courseAddEditor.sync();

            $.post("content/save",$("#courseAddForm").serialize(), function(data){
            	if(data.status == 200){
            		$.messager.alert('提示','新增内容成功!');
            	$("#courselist").datagrid("reload");
            	TT.closeCurrentWindow();
            	}
            });
        },
        clearForm : function(){
            $('#courseAddForm').form('reset');
            courseAddEditor.html('');
        }
    };
</script>
