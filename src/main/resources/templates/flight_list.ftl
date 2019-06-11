!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
航班管理

这个里面最好有搜索，按起飞地、目的地、日期搜索就行。这个目前后台还没实现，你可以假装已经实现了，在这里写注释给我

这里每个航班右边都有编辑和删除，删除直接POST给/api/flight/delete/{航班id} 即可

所有航班的信息会放在 biz_list 这个attribute里面，用getId就能拿对应航班的id

这个要有两种样式，login_user这个attribute里面有User当前登录信息
如果是Type=1（客户）的话，右边应该就是购票了（->order_creat)，若购票的话
就链接到/user/order/create/{航班id}