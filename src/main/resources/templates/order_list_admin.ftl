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
  </style>

  <div class="col-md-9">
    <h1>订单管理</h1>

    <table class="table">
      <thead>
      <tr>
        <th style="width: 10%; text-overflow: ellipsis;">ID</th>
        <th>用户名</th>
        <th>航班名</th>
        <th>舱位</th>
        <th>实际消费</th>
        <th>状态</th>
        <th>行数</th>
        <th>列数</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <#list biz_list as order>
        <tr>
          <td class="text-right">${order.id?c}</td>
          <td>${biz_user_map[order.userId?c]}</td>
          <td>${biz_flight_map[order.flightId?c]}</td>
          <td>
              <#if order.type==0!>头等舱</#if>
              <#if order.type==1!>经济舱</#if>
          </td>
          <td>${order.cost?c}</td>
          <td>
              <#if order.type==0!><span class="label label-danger">取消</span></#if>
              <#if order.type==1!><span class="label label-success">正常</span></#if>
          </td>
          <td>${order.row}</td>
          <td>${order.col}</td>
          <td>
            <a href="" data-toggle="modal" data-target="#makesureModal"
               onclick='dvalue("${order.id?c}")'>变更状态</a>
          </td>
        </tr>
      </#list>
      </tbody>
    </table>

    <nav class="pull-right">
        <#if RequestParameters['page']?? && RequestParameters['page']?eval gt 1>
          <a href="/admin/order/list?page=${RequestParameters['page']?eval - 1}"><span>&laquo;</span>上一页</a>
        </#if>

      第<#if RequestParameters['page']??>${RequestParameters['page']}<#else>1</#if>页，共${page_max}
      页

        <#if RequestParameters['page']??>
            <#if RequestParameters['page']?eval lt page_max>
              <a href="/admin/order/list?page=${RequestParameters['page']?eval + 1}">下一页<span>&raquo;</span></a>
            </#if>
        <#else>
            <#if 1 lt page_max>
              <a href="/admin/order/list?page=2">下一页<span>&raquo;</span></a>
            </#if>
        </#if>
    </nav>
  </div>

  <div class="modal fade" id="makesureModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
          <h4 class="modal-title">确认吗</h4>
        </div>
        <div class="modal-body">
          真的要取消它吗？
        </div>
        <div class="modal-footer">
          <form action="/api/order/delete" method="post">
            <input hidden id="deleteId" name="id">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-danger">确认</button>
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