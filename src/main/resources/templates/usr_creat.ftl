创建用户

用户信息用JSON POST到/api/user/create，

{
String username;    // 用户名
String pwd;         // 密码
Integer type;       // 类型 0=用户 1=管理员
String realname;    // 真实姓名
String phone;       // 电话号码
}
