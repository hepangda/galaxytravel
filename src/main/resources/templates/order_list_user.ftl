<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar_usr.ftl">
  <div class="col-md-9">
    <h1>订单查询</h1>

    <table class="table">
      <thead>
      <tr>
        <th style="width: 10%; text-overflow: ellipsis;">ID</th>
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
            <a href="/user/feedback/create/${order.id?c}">反馈</a>
          </td>
        </tr>
      </#list>
      </tbody>
    </table>

    <nav class="pull-right">
        <#if RequestParameters['page']?? && RequestParameters['page']?eval gt 1>
          <a href="/user/order/list?page=${RequestParameters['page']?eval - 1}"><span>&laquo;</span>上一页</a>
        </#if>

      第<#if RequestParameters['page']??>${RequestParameters['page']}<#else>1</#if>页，共${page_max}
      页

        <#if RequestParameters['page']??>
            <#if RequestParameters['page']?eval lt page_max>
              <a href="/user/order/list?page=${RequestParameters['page']?eval + 1}">下一页<span>&raquo;</span></a>
            </#if>
        <#else>
            <#if 1 lt page_max>
              <a href="/user/order/list?page=2">下一页<span>&raquo;</span></a>
            </#if>
        </#if>
    </nav>
  </div>
</@layout.content>