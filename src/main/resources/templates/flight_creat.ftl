<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar.ftl">
  <div class="col-md-9">
    <h1>添加航班</h1>
    <hr>
    <form class="form-horizontal" action="/api/flight/create" method="POST">
      <div class="form-group">
        <label class="col-sm-2 control-label">航班名</label>
        <div class="col-sm-10">
          <input type="text" maxlength="19" class="form-control" name="name" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">航线</label>
        <div class="col-sm-4">
          <select name="airlineId" class="form-control">
              <#list biz_airline_map?keys as key>
                <option value="${key}">${biz_airline_map[key]}</option>
              </#list>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">飞机</label>
        <div class="col-sm-4">
          <select name="airplaneId" class="form-control">
              <#list biz_airplane_map?keys as key>
                <option value="${key}">${biz_airplane_map[key]}</option>
              </#list>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">时间</label>
        <div class="col-sm-10">
          <input type="datetime-local" class="form-control" name="scheTimeX" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">头等舱价格</label>
        <div class="col-sm-10">
          <input type="number" min="0" class="form-control" name="firstClassPrice" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">经济舱价格</label>
        <div class="col-sm-10">
          <input type="number" min="0" class="form-control" name="secondClassPrice" required>
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-success">保存</button>
          <a class="btn btn-default" href="/admin/flight/list">返回</a>
        </div>
      </div>
    </form>
  </div>
</@layout.content>