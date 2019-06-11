购票

用户想买的航班信息会放在 biz_flight_msg 这个attribute里面
航线信息在 biz_airline_msg
飞机信息在 biz_airplane_msg
座位信息在 biz_seat_msg 里面
biz_seat_msg是一个二维boolean数组，表示某个座位是否被卖出（还未实现，你先这么写就好）

当前登陆用户在session的login_user里面

最后用JSON POST到/api/order/create，

{
Long id;        // 订单ID【这一项你填null】
Long userId;    // 用户ID
Long flightId;  // 航班ID
Integer clazz;  // 座位类型 0=头等舱 1=经济舱
Long cost;      // 花费了多少钱【这一项你填null或者不填，就是当前这个座位的价钱】
Integer type;   // 状态 0=已取消 1=正常
Integer row;    // 第几排
Integer col;    // 第几列
}

