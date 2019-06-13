<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar_usr.ftl">
  <style>
    .searchbutton {
      margin: 0;
      padding: 0;
      border: 1px solid transparent;
      outline: none;
    }
  </style>

  <div class="col-md-9">
    <h1>航班搜索结果</h1>
    <form class="form-inline pull-right" action="/user/flight/list" method="get">
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

    <table class="table">
      <thead>
      <tr>
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
            <a href="/user/order/create/${flight.id?c}">买头等舱</a>
            <a href="/user/order/create/${flight.id?c}">买经济舱</a>
          </td>
        </tr>
      </#list>
      </tbody>
    </table>
  </div>
</@layout.content>