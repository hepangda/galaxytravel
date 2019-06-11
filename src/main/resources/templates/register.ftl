注册，把User的这些信息POST给/api/register即可

{
String username;    // 用户名
String pwd;         // 密码（明文 就是这么随意）
Integer type;       // 类型 0=用户 1=管理员 （register的必须为0）
String realname;    // 真实姓名
String phone;       // 电话号码
}