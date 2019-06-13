<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar_usr.ftl">
  <div class="col-md-9">
    <h1>创建反馈</h1>
    <hr>
    <form class="form-horizontal" action="/api/feedback/create" method="POST">
      <input type="text" name="orderId" value="${biz_modifier}" hidden>

      <div class="form-group">
        <label class="col-sm-2 control-label">信息</label>
        <div class="col-sm-10">
          <textarea name="message" style="resize: none" rows="5" class="form-control"></textarea>
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-success">反馈</button>
          <a class="btn btn-default" href="/user/order/list">返回</a>
        </div>
      </div>
    </form>
  </div>
</@layout.content>