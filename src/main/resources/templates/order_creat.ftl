<#import "layout/main.ftl" as layout>
<@layout.content>
  <div class="col-md-offset-2 col-md-8">
    <h1>购票</h1>
    <hr>
    <form class="form-horizontal" action="/api/order/create" method="POST"
          onsubmit="return fillTickets('fieldTickets');">
      <div class="form-group">
        <label class="col-sm-2 control-label">航班</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="name" required readonly
                 value="${biz_flight_msg.name}">
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">舱位</label>
        <div class="col-sm-10">
          <input class="form-control"
                 value="<#if biz_clazz == 0>头等舱</#if><#if biz_clazz == 1>经济舱</#if>" readonly>
          <select name="clazz" class="form-control" style="visibility: hidden">
            <option value="0" <#if biz_clazz == 0>selected</#if>>头等舱</option>
            <option value="1" <#if biz_clazz == 1>selected</#if>>经济舱</option>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">价格</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="cost" required readonly
                 value="<#if biz_clazz == 0>${biz_flight_msg.firstClassPrice}</#if><#if biz_clazz == 1>${biz_flight_msg.secondClassPrice}</#if>">
        </div>
      </div>

      <div>
        <input type="hidden" name="tickets" id="fieldTickets">
        <input type="hidden" name="userId" value="${currentUser.id?c}">
        <input type="hidden" name="flightId" value="${biz_flight_msg.id?c}">
        <input type="hidden" name="type" value="1">

        <p class="text-center">选座</p>
        <div class="seats">
            <#list biz_seat as stmsg>
              <div class="seats-row">
                  <#list stmsg as st>
                    <div class="${st[1]}" data-ticketid="${st[0]}"></div>
                  </#list>
              </div>
            </#list>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">提交订单</button>
            <a class="btn btn-default" href="/user/flight/list">返回</a>
          </div>
        </div>
    </form>
  </div>
  <script src="/js/selectSeat.js"></script>
</@layout.content>