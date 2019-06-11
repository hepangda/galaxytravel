创建航班

用JSON POST到/api/flight/create，

{
Long airlineId;       // 航线ID
Long airplaneId;      // 飞机ID
String name;          // 航班名 KS2333
Long scheTime;        // 航班时间 yyyyMMdd hhmmss 转换为Unix时间戳（单位秒）
Long firstClassPrice;   // 头等舱价格
Long secondClassPrice;  // 经济舱价格
}

