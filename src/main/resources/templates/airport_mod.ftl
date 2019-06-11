修改机场

要修改的机场的信息会放在 biz_modifier 这个attribute里面

最后把所有机场信息用JSON POST到/api/airport/modify/{id} （即便没修改的部分也得POST过来）

{
Long id;          // 机场ID
String cityName;  // 城市名  比如：上海
String portName;  // 机场名  比如：上海虹桥 上海浦东
Integer type;     // 类型 0=未启用 1=已启用 还是用下拉列表
}