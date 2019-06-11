创建机场

用JSON POST到/api/airport/create，

{
String cityName;  // 城市名  比如：上海
String portName;  // 机场名  比如：上海虹桥 上海浦东
Integer type;     // 类型 0=未启用 1=已启用 还是用下拉列表
}

