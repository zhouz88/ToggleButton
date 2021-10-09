# ToggleButton

可以看出，该方法所做的工作与上面的差不多，都进行了offset和union对坐标的调整，然后把dirty区域的信息保存在mDirty中，最后调用了scheduleTraversals方法，触发View的工作流程，由于没有添加measure和layout的标记位，因此measure、layout流程不会执行，而是直接从draw流程开始。

好了，现在总结一下invalidate方法，当子View调用了invalidate方法后，会为该View添加一个标记位，同时不断向父容器请求刷新，父容器通过计算得出自身需要重绘的区域，直到传递到ViewRootImpl中，最终触发performTraversals方法，进行开始View树重绘流程(只绘制需要重绘的视图)。
————————————————
版权声明：本文为CSDN博主「程序员的自我反思」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/a553181867/article/details/51583060。
(requestlayout/invalidate区别)

————————————————
版权声明：本文为CSDN博主「程序员的自我反思」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/a553181867/article/details/51583060
https://blog.csdn.net/a553181867/article/details/51583060
