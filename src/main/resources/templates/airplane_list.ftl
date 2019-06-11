飞机管理

这里每个飞机右边都有编辑和删除，删除直接POST给/api/airplane/delete/{飞机id} 即可

所有飞机的信息会放在 biz_list 这个attribute里面，用getId就能拿对应飞机的id