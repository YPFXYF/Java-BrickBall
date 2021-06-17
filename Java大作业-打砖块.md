# Java大作业-打砖块

[【Java 2D游戏开发（二）】GameObject与Sprite/显示出第一个物体_XcantloadX的博客-CSDN博客](https://blog.csdn.net/XcantloadX/article/details/87903319)

参考链接如上

前置知识：Java AWT和Swing

## 大致架构

基础：窗体、图片的显示和移动、键盘监听、碰撞判定

设计：球和砖等实例，

### 已完成的

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

### 未完成的

RectGameObject类继承自GameObject类，用于碰撞判断

Ball类继承自RectGameObject表示小球

Brick类继承自RectGameObject表示砖块

球和砖的交互

Player类继承自Game类表示游戏

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