# Java大作业-打砖块

[【Java 2D游戏开发（二）】GameObject与Sprite/显示出第一个物体_XcantloadX的博客-CSDN博客](https://blog.csdn.net/XcantloadX/article/details/87903319)

参考链接如上

前置知识：Java AWT和Swing

## 大致架构

基础：窗体、图片的显示和移动、键盘监听、碰撞判定

设计：球和砖等实例，

游戏画面的更新是通过在RenderThread中sleep固定时间后不断尝试game.repaint()方法实现的。

## 已完成的

### Game类

继承自JFrame

建立Game类继承自JFrame，重写JFrame的paint方法实现我们自己绘图

储存有窗口大小等信息，以及存有窗口中的游戏对象元素的ArrayList。

paint方法中使用双缓冲解决渲染慢导致的闪烁问题

### GameObject类

新建GameObject类，游戏中所有对象都会继承自这个类。

里面有坐标，坐标处理的相关方法以及画图方法draw(Graphics g).还有onTick()方法执行移动对象的交互，transfer方法用于移动对象。

### Sprite类

Sprite类继承自GameObject，用于把图片作为游戏里可移动，操作的对象

### RenderThread类

RenderThread类实现Runnable接口，这个线程用于定时重绘游戏窗口画面。

### Input类

Input类监听键盘

### RectGameObject类

继承自GameObject类，用于碰撞判断

### Ball类

继承自RectGameObject表示小球

## 未完成的

### Brick类

继承自RectGameObject表示砖块

需要实现球和砖的交互，以及从游戏中移除砖块。

要消除砖块，需要先实现球和砖块碰撞的判定

目前砖块的尺寸是60*30 考虑如何把屏幕（1024$\times$768）铺满。(done)



## 解决窗口闪烁问题

按照如上流程写完之后，游戏画面会闪烁。这是因为渲染太慢

每个窗口都有一个Graphics，我们暂且叫它窗口画布。直接在上面画贴图比较费时。

```java
while(渲染未完成){
	窗口画布.DrawImage()->操作系统->显卡	
}
```

物体较多时就会变卡

解决方法是：在内存中创建一个独立的画布，渲染过程变为

```java
while(渲染未完成){
	内存画布.DrawImage() -> 内存
}
窗口画布.DrawImage(内存画布.到Image())->OS->显卡
```

## 键盘与游戏交互

监听键盘按键：实现KeyListener接口就可以了。

新建Input类，实现KeyListener接口，用哈希表把按键映射到对应的值上。重写KeyListener中的keyTyped,keyPressed和keyReleased方法





## 碰撞检测

https://blog.csdn.net/ximen250/article/details/105566663?spm=1001.2014.3001.5501

[(14条消息) “等一下，我碰！”——常见的2D碰撞检测_测试-CSDN博客](https://blog.csdn.net/cpongo3/article/details/90259127)

建立抽象类RectGameObject继承自GameObject

球和砖简化为判断圆形和矩形的碰撞

以球心为座标原点，窗口边框方向为座标轴方向建立座标系

### case1:砖全部在某一个象限内

依次计算每个角到圆心的距离是否小于半径即可。

如何判断四角是否在同一个象限内？判左上角和右下角是否在同一个象限即可。

### case2:砖跨过2个或2个以上的象限

此时的碰撞是砖块的边与球的碰撞

可以把球看成边长2r的正方形

找砖块离球心最近的边 然后判断应该让speedx取反还是让speedy取反







