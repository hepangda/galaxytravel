创建飞机

用JSON POST到/api/airport/create，

{
String name;    // 飞机名
Integer type;   // 飞机类型：0=未启用 1=已启用   这边用下拉列表就行
Integer rows;   // 飞机座位行数
Integer cols;   // 飞机座位列数
private Integer firstClassRows; // 飞机头等舱的行数 （这里默认飞机的前X行为头等舱）
}

