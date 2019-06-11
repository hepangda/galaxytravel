修改反馈

要修改的反馈的信息会放在 biz_modifier 这个attribute里面

最后把所有反馈信息用JSON POST到/api/feedback/modify/{id} （即便没修改的部分也得POST过来）

{
Long id;        // 反馈ID
Long orderId;   // 订单ID 这两项最好设计为不可编辑的或者不显示，不过不显示太丑了
String message; // 反馈信息
}