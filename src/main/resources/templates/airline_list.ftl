航线列表管理

这里每个航线右边都有编辑和删除，删除直接POST给/api/airline/delete/{航线id} 即可

所有航线的信息会放在 biz_list 这个attribute里面，用getId就能拿对应航线的id