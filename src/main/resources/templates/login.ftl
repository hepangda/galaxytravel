登陆，提供用户名密码就行。

${global_biz_message?if_exists}

<form method="post" action="/api/login">
  <input name="username">
  <input name="pwd">
  <button type="submit">submit</button>
</form>
