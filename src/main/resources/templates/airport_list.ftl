机场管理

这里每个机场右边都有编辑和删除，删除直接POST给/api/airport/delete/{机场id} 即可

所有机场的信息会放在 biz_list 这个attribute里面，用getId就能拿对应机场的id