<div class="col-md-3 sidebar">
  <ul class="nav nav-pills nav-stacked">
    <li role="presentation" <#if active?exists && active == "flight">class="active"</#if>>
      <a href="/user/flight/list">航班订票</a>
    </li>
    <li role="presentation" <#if active?exists && active == "order">class="active"</#if>>
      <a href="/user/order/list">查看订单</a>
    </li>
  </ul>
</div>