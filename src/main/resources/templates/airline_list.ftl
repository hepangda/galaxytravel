<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar.ftl">
  <style>
    #makesureModal {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translateX(-50%) translateY(-50%);
    }

    .searchbutton {
      margin: 0;
      padding: 0;
      border: 1px solid transparent;
      outline: none;
    }
  </style>

  <div class="col-md-9">
      <#if !not_query??>
        <h1>航线管理</h1>
        <a class="btn btn-success" href="/admin/airline/create">添加航线</a>
        <form class="form-inline pull-right" action="/admin/airline/list" method="get">
          <div class="form-group">
            <div class="input-group">
              <input type="text" class="form-control" name="keyword" placeholder="航线名"/>
              <span class="input-group-addon">
            <button type="submit" class="searchbutton">
              <i class="glyphicon glyphicon-search"><span>搜索</span></i>
            </button>
          </span>
            </div>
          </div>
        </form>
      <#else>
        <h1>航线搜索结果</h1>
      </#if>

    <table class="table">
      <thead>
      <tr>
        <th style="width: 10%; text-overflow: ellipsis;">ID</th>
        <th style="width: 25%;">航线名</th>
        <th>起飞机场</th>
        <th>降落机场</th>
        <th>类型</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <#list biz_list as airline>
        <tr>
          <td class="text-right">${airline.id?c}</td>
          <td>${airline.name}</td>
          <td>${biz_airport_map[airline.sourcePortId?c]}</td>
          <td>${biz_airport_map[airline.destPortId?c]}</td>
          <td>
              <#if airline.type==0!><span class="label label-danger">未启用</span></#if>
              <#if airline.type==1!><span class="label label-warning">规划中</span></#if>
              <#if airline.type==2!><span class="label label-success">已启用</span></#if>
          </td>

          <td>
            <a href="/admin/airline/modify/${airline.id?c}">编辑航线信息</a>
            <a href="" data-toggle="modal" data-target="#makesureModal"
               onclick='dvalue("${airline.id?c}")'>删除航线</a>
          </td>
        </tr>
      </#list>
      </tbody>
    </table>

      <#if !not_query??>
        <nav class="pull-right">
            <#if RequestParameters['page']?? && RequestParameters['page']?eval gt 1>
              <a href="/admin/airline/list?page=${RequestParameters['page']?eval - 1}"><span>&laquo;</span>上一页</a>
            </#if>

          第<#if RequestParameters['page']??>${RequestParameters['page']}<#else>1</#if>页，共${page_max}
          页

            <#if RequestParameters['page']??>
                <#if RequestParameters['page']?eval lt page_max>
                  <a href="/admin/airline/list?page=${RequestParameters['page']?eval + 1}">下一页<span>&raquo;</span></a>
                </#if>
            <#else>
                <#if 1 lt page_max>
                  <a href="/admin/airline/list?page=2">下一页<span>&raquo;</span></a>
                </#if>
            </#if>
        </nav>
      <#else>
        <nav class="pull-right">
          <a href="/admin/airline/list"><span>&laquo;</span>返回</a>
        </nav>
      </#if>
  </div>

  <div class="modal fade" id="makesureModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
          <h4 class="modal-title">确认吗</h4>
        </div>
        <div class="modal-body">
          真的要删除它吗？
        </div>
        <div class="modal-footer">
          <form action="/api/airline/delete" method="post">
            <input hidden id="deleteId" name="id">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-danger">确认，并删除</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <script>
    function dvalue(a) {
      $("#deleteId").val(a);
    }
  </script>
</@layout.content>