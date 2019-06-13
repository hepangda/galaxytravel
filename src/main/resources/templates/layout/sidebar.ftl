<div class="col-md-3 sidebar">
    <ul class="nav nav-pills nav-stacked">
        <li role="presentation" <#if active?exists && active == "airport">class="active"</#if>>
            <a href="/admin/airport/list">机场管理</a>
        </li>
        <li role="presentation" <#if active?exists && active == "airplane">class="active"</#if>>
            <a href="/admin/airplane/list">飞机管理</a>
        </li>
        <li role="presentation" <#if active?exists && active == "airline">class="active"</#if>>
            <a href="/admin/airline/list">航线管理</a>
        </li>
        <li role="presentation" <#if active?exists && active == "feedback">class="active"</#if>>
            <a href="/admin/feedback/list">反馈管理</a>
        </li>
        <li role="presentation" <#if active?exists && active == "user">class="active"</#if>>
            <a href="/admin/user/list">用户管理</a>
        </li>
        <li role="presentation" <#if active?exists && active == "flight">class="active"</#if>>
            <a href="/admin/flight/list">航班管理</a>
        </li>
        <li role="presentation" <#if active?exists && active == "order">class="active"</#if>>
            <a href="/admin/order/list">订单管理</a>
        </li>
    </ul>
</div>