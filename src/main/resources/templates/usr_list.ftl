用户列表管理

这里每个用户右边都有编辑和删除，删除直接POST给/api/user/delete/{用户id} 即可

所有用户的信息会放在 biz_list 这个attribute里面，用getId就能拿对应用户的id