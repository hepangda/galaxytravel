修改订单

要修改的订单的信息会放在 biz_modifier 这个attribute里面

最后把所有订单信息用JSON POST到/api/order/modify/{id} （即便没修改的部分也得POST过来）

{
Long id;        // 订单ID
Long userId;    // 用户ID 【不让改】
Long flightId;  // 航班ID
Integer clazz;  // 座位类型 0=头等舱 1=经济舱
Long cost;      // 花费了多少钱
Integer type;   // 状态 0=已取消 1=正常
Integer row;    // 第几排
Integer col;    // 第几列
}
