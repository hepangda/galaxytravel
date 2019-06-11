修改航线

要修改的航线的信息会放在 biz_modifier 这个attribute里面

最后把所有航线信息用JSON POST到/api/airline/modify/{id} （即便没修改的部分也得POST过来）

{
Long id;            // 航线ID
String name;        // 航线名
Long sourcePortId;  // 起飞机场ID
Long destPortId;    // 目标机场ID
Integer type;       // 航线状态：0=未启用 1=规划中 2=已启用
}

