修改用户

要修改的用户的信息会放在 biz_modifier 这个attribute里面

最后把所有用户信息用JSON POST到/api/user/modify/{id} （即便没修改的部分也得POST过来）

{
Long id;            // 用户ID
String username;    // 用户名
String pwd;         // 密码（明文）
Integer type;       // 类型 0=用户 1=管理员
String realname;    // 真实姓名
String phone;       // 电话号码
}
