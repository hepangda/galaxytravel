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

    #viewModal {
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
    <h1>反馈管理</h1>
    <table class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>订单ID</th>
        <th style="width: 60%; text-overflow: ellipsis; overflow:hidden; white-space:nowrap;">概要信息(40)</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <#list biz_list as feedback>
        <tr>
          <td class="text-right">${feedback.id?c}</td>
          <td>${feedback.orderId?c}</td>
          <td>
            <#if feedback.message?length gt 40>
                ${feedback.message?substring(0, 40)}...
            <#else>
                ${feedback.message}
            </#if>
          </td>
          <td>
            <a href="" data-toggle="modal" data-target="#viewModal"
               onclick="vvalue({id: ${feedback.id?c}, orderId: ${feedback.orderId?c}, message: '${feedback.message}'})">
              全文
            </a>
            <a href="" data-toggle="modal" data-target="#makesureModal"
               onclick='dvalue("${feedback.id?c}")'>删除</a>
          </td>
        </tr>
      </#list>
      </tbody>
    </table>

    <nav class="pull-right">
        <#if RequestParameters['page']?? && RequestParameters['page']?eval gt 1>
          <a href="/admin/feedback/list?page=${RequestParameters['page']?eval - 1}"><span>&laquo;</span>上一页</a>
        </#if>

      第<#if RequestParameters['page']??>${RequestParameters['page']}<#else>1</#if>页，共${page_max}
      页

        <#if RequestParameters['page']??>
            <#if RequestParameters['page']?eval lt page_max>
              <a href="/admin/feedback/list?page=${RequestParameters['page']?eval + 1}">下一页<span>&raquo;</span></a>
            </#if>
        <#else>
            <#if 1 lt page_max>
              <a href="/admin/feedback/list?page=2">下一页<span>&raquo;</span></a>
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
          真的要删除它吗？
        </div>
        <div class="modal-footer">
          <form action="/api/feedback/delete" method="post">
            <input hidden id="deleteId" name="id">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-danger">确认，并删除</button>
          </form>
        </div>
      </div>
    </div>
  </div>



  <div id="viewModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
      <div class="modal-content">

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
          <h4 class="modal-title" id="viewId">查看反馈 #</h4>
        </div>

        <div class="modal-body">
          <form class="form-horizontal">
            <div class="form-group">
              <label for="viewOrderId" class="col-sm-2 control-label">订单ID</label>
              <div class="col-sm-10">
                <input class="form-control" id="viewOrderId" readonly>
              </div>
            </div>

            <div class="form-group">
              <label for="viewMessage" class="col-sm-2 control-label">信息</label>
              <div class="col-sm-10">
                <textarea style="resize: none" rows="5" class="form-control" readonly
                          id="viewMessage"></textarea>
              </div>
            </div>
          </form>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
      </div>
    </div>
  </div>


  <script>
    function dvalue(a) {
      $("#deleteId").val(a);
    }

    function vvalue(a) {
      $("#viewId").text('查看反馈 #' + a.id);
      $("#viewOrderId").val(a.orderId);
      $("#viewMessage").val(a.message);
    }
  </script>
</@layout.content>
