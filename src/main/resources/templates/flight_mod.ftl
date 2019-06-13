<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar.ftl">
  <div class="col-md-9">
    <h1>编辑航班 #${biz_modifier.id?c}</h1>
    <hr>
    <form class="form-horizontal" action="/api/flight/modify/${biz_modifier.id?c}" method="POST">
      <input type="text" class="invisible" name="id" value="${biz_modifier.id?c}">

      <div class="form-group">
        <label class="col-sm-2 control-label">航班名</label>
        <div class="col-sm-10">
          <input type="text" maxlength="19" class="form-control" name="name" required
                 value="${biz_modifier.name}">
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">航线</label>
        <div class="col-sm-4">
          <select name="airlineId" class="form-control">
              <#list biz_airline_map?keys as key>
                <option value="${key}"
                        <#if key == biz_modifier.airlineId?c>selected</#if>>
                    ${biz_airline_map[key]}
                </option>
              </#list>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">飞机</label>
        <div class="col-sm-4">
          <select name="airplaneId" class="form-control">
              <#list biz_airplane_map?keys as key>
                <option value="${key}"
                        <#if key == biz_modifier.airplaneId?c>selected</#if>>
                    ${biz_airplane_map[key]}
                </option>
              </#list>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">时间</label>
        <div class="col-sm-10">
          <input type="datetime-local" id="scheTime" class="form-control" name="scheTime" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">头等舱价格</label>
        <div class="col-sm-10">
          <input type="number" min="0" class="form-control" name="firstClassPrice"
                 value="${biz_modifier.firstClassPrice}" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">经济舱价格</label>
        <div class="col-sm-10">
          <input type="number" min="0" class="form-control" name="secondClassPrice"
                 value="${biz_modifier.secondClassPrice}" required>
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

  <script>
    function c() {
      document.getElementById('scheTime').value = "${biz_modifier.scheTime}".replace(/ /, "T");
    }
    c();
  </script>

</@layout.content>