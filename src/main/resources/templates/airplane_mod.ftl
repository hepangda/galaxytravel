修改飞机

要修改的飞机的信息会放在 biz_modifier 这个attribute里面

最后把所有飞机信息用JSON POST到/api/airplane/modify/{id} （即便没修改的部分也得POST过来）

{
Long id;        // 飞机ID
String name;    // 飞机名
Integer type;   // 飞机类型：0=未启用 1=已启用   这边用下拉列表就行
Integer rows;   // 飞机座位行数
Integer cols;   // 飞机座位列数
private Integer firstClassRows; // 飞机头等舱的行数 （这里默认飞机的前X行为头等舱）
}

