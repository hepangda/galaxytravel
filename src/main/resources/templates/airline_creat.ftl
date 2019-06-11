创建航线

所有机场的信息会放在 biz_airport_list 这个attribute里面

最后航线信息用JSON POST到/api/airline/create，

{
String name;        // 航线名
Long sourcePortId;  // 起飞机场ID
Long destPortId;    // 目标机场ID
Integer type;       // 航线状态：0=未启用 1=规划中 2=已启用
}

