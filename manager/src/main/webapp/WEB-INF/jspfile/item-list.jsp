<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<table class="easyui-datagrid" id="courselist" title="课程列表"
       data-options="singleSelect:true,selectOnCheck:false,checkOnSelect:false,collapsible:false,url:'content/list',method:'get',
                   queryParams:{cateId:0},pageSize:300,toolbar:contentListToolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:60">课程ID</th>
        <th data-options="field:'title',width:200">课程名称</th>
        <th data-options="field:'description',width:200">课程描述</th>
        <th data-options="field:'isnews',width:70">最新上线</th>
        <th data-options="field:'issupreme',width:70">是否精品</th>
        <th data-options="field:'created',width:130,align:'center',formatter:cms.formatDateTime">创建日期</th>
        <th data-options="field:'updated',width:130,align:'center',formatter:cms.formatDateTime">更新日期</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<script>

    var refreshcourse = function (nodeid) {
        var datagrid = $("#courselist");
        datagrid.datagrid('reload', {
            cateId: nodeid
        });
    };

    var contentListToolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
            var node = $("#contentCategoryTree").tree("getSelected");
            if(!node || !$("#contentCategoryTree").tree("isLeaf",node.target)){
                $.messager.alert('提示','新增内容必须选择一个内容分类!');
                return ;
            }
            TT.createWindow({
                url : "/content-add"
            });
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
            var ids = TT.getSelectionsIds("#contentList");
            if(ids.length == 0){
                $.messager.alert('提示','必须选择一个内容才能编辑!');
                return ;
            }
            if(ids.indexOf(',') > 0){
                $.messager.alert('提示','只能选择一个内容!');
                return ;
            }
            TT.createWindow({
                url : "/content-edit",
                onLoad : function(){
                    var data = $("#contentList").datagrid("getSelections")[0];
                    $("#contentEditForm").form("load",data);

                    // 实现图片
                    if(data.pic){
                        $("#contentEditForm [name=pic]").after("<a href='"+data.pic+"' target='_blank'><img src='"+data.pic+"' width='80' height='50'/></a>");
                    }
                    if(data.pic2){
                        $("#contentEditForm [name=pic2]").after("<a href='"+data.pic2+"' target='_blank'><img src='"+data.pic2+"' width='80' height='50'/></a>");
                    }

                    contentEditEditor.html(data.content);
                }
            });
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
            var ids = TT.getSelectionsIds("#contentList");
            if(ids.length == 0){
                $.messager.alert('提示','未选中商品!');
                return ;
            }
            $.messager.confirm('确认','确定删除ID为 '+ids+' 的内容吗？',function(r){
                if (r){
                    var params = {"ids":ids};
                    $.post("/content/delete",params, function(data){
                        if(data.status == 200){
                            $.messager.alert('提示','删除内容成功!',undefined,function(){
                                $("#contentList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }];

</script>