<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="jsfile/jquery-easyui-1.4.1/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="jsfile/jquery-easyui-1.4.1/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="cssfile/cms.css"/>
    <script type="text/javascript" src="jsfile/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="jsfile/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jsfile/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="jsfile/common.js"></script>
    <style type="text/css">
        .content {
            padding: 10px 10px 10px 10px;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'west',title:'分类树',split:true" style="width:300px;">
    <ul id="catetree" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;" data-options="
				url: 'content/category/tree',
				method: 'get',
				animate: true,
				onContextMenu: function(e,node){
					e.preventDefault();
					$(this).tree('select',node.target);
					$('#treemenu').menu('show',{
						left: e.pageX,
						top: e.pageY
					});
				}">
    </ul>
</div>

<div id="treemenu" class="easyui-menu" style="width:120px;">
    <div onclick="append()" data-options="iconCls:'icon-add'">添加</div>
    <div onclick="modifycategory()" data-options="iconCls:'icon-edit'">修改</div>
    <div onclick="removeit()" data-options="iconCls:'icon-remove'">删除</div>
    <div class="menu-sep"></div>
    <div onclick="expand()">展开</div>
    <div onclick="collapse()">折叠</div>
</div>

<div data-options="region:'center',title:''">
    <div id="tabs" class="easyui-tabs">
        <div id="contentlist" title="课程列表" style="padding:20px;">
            <table class="easyui-datagrid" id="courselist" title="课程列表"
                   data-options="singleSelect:false,selectOnCheck:false,checkOnSelect:false,collapsible:false,url:'content/list',method:'get',
                   queryParams:{cateId:0},pageSize:300,toolbar:contentListToolbar,fitColumns:true">
                <thead>
                <tr>
                    <%--<th data-options="field:'ck',checkbox:true"></th>--%>
                    <th data-options="field:'id',width:60">课程ID</th>
                    <th data-options="field:'title',width:200">课程名称</th>
                    <th data-options="field:'description',width:200">课程描述</th>
                    <th data-options="field:'isnews',width:70">最新上线</th>
                    <th data-options="field:'issupreme',width:70">是否精品</th>
                    <th data-options="field:'created',width:130,align:'center',formatter:cms.formatDateTime">创建日期</th>
                    <th data-options="field:'updated',width:130,align:'center',formatter:cms.formatDateTime">更新日期</th>
                    <%--<th>操作</th>--%>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript" charset="UTF-8">

    $.extend($.fn.tree.methods, {
        getLevel: function (jq, target) {
            var l = $(target).parentsUntil("ul.tree", "ul");
            return l.length + 1;
        }
    });

    $(function () {
        var t = $('#catetree');
        var tabs = $("#tabs");
        tabs.tabs('hideHeader');
        t.tree({
            onClick: function (node) {
                //切换tab页
                var lv = t.tree("getLevel", node.target);
                if (lv == 2) {
                    tabs.tabs('select', 0);
                }

                //加载页面
                var tab = tabs.tabs('getSelected');
                var datagrid = $("#courselist");
                datagrid.datagrid('reload', {
                    cateId: node.id
                });
            }
        });
    });

    function loadTree() {
        var t = $('#catetree');
        t.tree({
            animate: true,
            method: 'get',
            url: "content/category/tree",
        })
    }

    function append() {
        var t = $('#catetree');
        var node = t.tree('getSelected');
        var lv = $('#catetree').tree("getLevel", node.target);
        if (lv == 2) {
            node = $('#catetree').tree("getParent", node.target);
        }
        $.messager.prompt("新增分类", "请输入分类名称", function (nodeName) {
            if (nodeName) {
                $.ajax({
                    type: "GET",
                    url: "content/category/create",
                    data: {"parentId": node.id, "nodeName": encodeURI(nodeName)},
                    success: function (data) {
                        loadTree();
                    }
                })
            } else {
                return;
            }
        });
    }

    function modifycategory() {
        var t = $('#catetree');
        var node = t.tree('getSelected');
        if (node) {
            $.messager.prompt("修改分类", "请输入修改后的分类名称", function (newName) {
                if (newName) {
                    $.ajax({
                        type: "GET",
                        url: "content/category/modify",
                        data: {"id": node.id, "newName": encodeURI(newName)},
                        success: function (data) {
                            t.tree('update', {
                                target: node.target,
                                text: newName
                            })
                        }
                    })
                } else {
                    return;
                }
            });
        }
    }

    function removeit() {
        var t = $('#catetree');
        var node = t.tree('getSelected');
        var lv = t.tree("getLevel", node.target);
        if (lv == 1) {
            $.messager.alert("警告", "大分类节点不能删除！");
            return;
        } else {
            $.messager.confirm('确认', '您确认想要删除分类【' + node.text + '】吗？这将删除该分类下的所有课程！', function (r) {
                if (r) {
                    $.ajax({
                        type: "GET",
                        url: "content/category/remove",
                        data: {"id": node.id},
                        success: function (data) {
                            $('#catetree').tree('remove', node.target);
                        }
                    })
                }
            });
        }
    }

    function collapse() {
        var node = $('#catetree').tree('getSelected');
        $('#catetree').tree('collapse', node.target);
    }

    function expand() {
        var node = $('#catetree').tree('getSelected');
        $('#catetree').tree('expand', node.target);
    }

    var contentListToolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
            var t = $('#catetree');
            var node = t.tree('getSelected');
            var lv = t.tree("getLevel", node.target);
            if (1 == lv) {
                return;
            }

            var t = $("#catetree");
            var node = t.tree("getSelected");
            if(!node){
                $.messager.alert('提示','新增课程必须选择一个课程分类!');
                return;
            }
            var lv = t.tree("getLevel", node.target);
            if (1 == lv) {
                $.messager.alert('提示','不能在课程大分类下新增课程!');
                return;
            }
            TT.createWindow({
                width: 820,
                height: 550,
                collapsible: false,
                url : "content/showcontentadd"
            });
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
            var t = $('#catetree');
            var node = t.tree('getSelected');
            var lv = t.tree("getLevel", node.target);
            if (1 == lv) {
                return;
            }

            var ids = TT.getSelectionsIds("#courselist");
            if(ids.length == 0){
                $.messager.alert('提示','必须选择一个课程才能编辑!');
                return ;
            }
            if(ids.indexOf(',') > 0){
                $.messager.alert('提示','只能选择一个课程!');
                return ;
            }
            TT.createWindow({
                url : "content/showcontentedit",
                width: 820,
                height: 550,
                collapsible: false,
                onLoad : function(){
                    var data = $("#courselist").datagrid("getSelections")[0];
                    $("#courseEditForm").form("load",data);

                    // 实现图片
                    // if(data.pic){
                    //     $("#contentEditForm [name=pic]").after("<a href='"+data.pic+"' target='_blank'><img src='"+data.pic+"' width='80' height='50'/></a>");
                    // }
                    // if(data.pic2){
                    //     $("#contentEditForm [name=pic2]").after("<a href='"+data.pic2+"' target='_blank'><img src='"+data.pic2+"' width='80' height='50'/></a>");
                    // }

                    courseEditEditor.html(data.content);
                    document.getElementById("isnews").checked=data.isnews;
                    document.getElementById("issupreme").checked=data.issupreme;
                }
            });
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
            var t = $('#catetree');
            var node = t.tree('getSelected');
            var lv = t.tree("getLevel", node.target);
            if (1 == lv) {
                return;
            }

            var ids = TT.getSelectionsIds("#courselist");
            if(ids.length == 0){
                $.messager.alert('提示','未选中课程!');
                return;
            }
            $.messager.confirm('确认','确定删除ID为 '+ids+' 的课程吗？',function(r){
                if (r){
                    var params = {"ids":ids};
                    $.post("content/delete", params, function(data){
                        if(data.status == 200){
                            $.messager.alert('提示','删除课程成功!',undefined,function(){
                                $("#courselist").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }];
</script>

</body>
</html>