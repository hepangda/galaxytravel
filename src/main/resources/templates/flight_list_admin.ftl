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
      <#if not_query??>
        <h1>航班管理</h1>
        <a class="btn btn-success" href="/admin/flight/create">添加航班</a>
        <form class="form-inline pull-right" action="/admin/flight/list" method="get">
          <div class="form-group">
            <div class="input-group">
              <input type="text" class="input-small" name="from" placeholder="起飞地"/>
              <input type="text" class="input-small" name="to" placeholder="降落地"/>
              <input type="date" class="input-small" name="time" placeholder="出行时间"/>
              <span class="input-group-addon">
            <button type="submit" class="searchbutton">
              <i class="glyphicon glyphicon-search"><span>搜索</span></i>
            </button>
          </span>
            </div>
          </div>
        </form>
      <#else>
        <h1>航班搜索结果</h1>
      </#if>

    <table class="table">
      <thead>
      <tr>
        <th style="width: 10%; text-overflow: ellipsis;">ID</th>
        <th>航班名</th>
        <th>飞机名</th>
        <th>航线名</th>
        <th>时间</th>
        <th>头等舱价格</th>
        <th>经济舱价格</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <#list biz_list as flight>
        <tr>
          <td class="text-right">${flight.id?c}</td>
          <td>${flight.name}</td>
          <td>${biz_airline_map[flight.airlineId?c]}</td>
          <td>${biz_airplane_map[flight.airplaneId?c]}</td>
          <td>${flight.scheTime}</td>
          <td>${flight.firstClassPrice}</td>
          <td>${flight.secondClassPrice}</td>
          <td>
            <a href="/admin/flight/modify/${flight.id?c}">编辑</a>
            <a href="" data-toggle="modal" data-target="#makesureModal"
               onclick='dvalue("${flight.id?c}")'>删除</a>
          </td>
        </tr>
      </#list>
      </tbody>
    </table>

      <#if not_query??>
        <nav class="pull-right">
            <#if RequestParameters['page']?? && RequestParameters['page']?eval gt 1>
              <a href="/admin/flight/list?page=${RequestParameters['page']?eval - 1}"><span>&laquo;</span>上一页</a>
            </#if>

          第<#if RequestParameters['page']??>${RequestParameters['page']}<#else>1</#if>页，共${page_max}
          页

            <#if RequestParameters['page']??>
                <#if RequestParameters['page']?eval lt page_max>
                  <a href="/admin/flight/list?page=${RequestParameters['page']?eval + 1}">下一页<span>&raquo;</span></a>
                </#if>
            <#else>
                <#if 1 lt page_max>
                  <a href="/admin/flight/list?page=2">下一页<span>&raquo;</span></a>
                </#if>
            </#if>
        </nav>
      <#else>
        <nav class="pull-right">
          <a href="/admin/flight/list"><span>&laquo;</span>返回</a>
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