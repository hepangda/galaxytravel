<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar.ftl">
  <div class="col-md-9">
    <h1>编辑航线 #${biz_modifier.id?c}</h1>
    <hr>
    <form class="form-horizontal" action="/api/airline/modify/${biz_modifier.id?c}" method="POST">
      <input type="text" class="invisible" name="id" value="${biz_modifier.id?c}">

      <div class="form-group">
        <label class="col-sm-2 control-label">航线名</label>
        <div class="col-sm-10">
          <input type="text" maxlength="19" class="form-control" name="name"
                 value="${biz_modifier.name}" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">起飞机场</label>
        <div class="col-sm-4">
          <select name="sourcePortId" class="form-control">
              <#list biz_airport_map?keys as key>
                <option value="${key}"
                        <#if key == biz_modifier.sourcePortId?c>selected</#if>>
                    ${biz_airport_map[key]}
                </option>
              </#list>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">降落机场</label>
        <div class="col-sm-4">
          <select name="destPortId" class="form-control">
              <#list biz_airport_map?keys as key>
                <option value="${key}"
                        <#if key == biz_modifier.destPortId?c>selected</#if>>
                    ${biz_airport_map[key]}
                </option>
              </#list>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">状态</label>
        <div class="col-sm-4">
          <select name="type" class="form-control">
            <option value="0" <#if biz_modifier.type==0!>selected</#if>>未启用</option>
            <option value="1" <#if biz_modifier.type==1!>selected</#if>>规划中</option>
            <option value="2" <#if biz_modifier.type==2!>selected</#if>>已启用</option>
          </select>
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-success">保存</button>
          <a class="btn btn-default" href="/admin/airline/list">返回</a>
        </div>
      </div>
    </form>
  </div>
</@layout.content>