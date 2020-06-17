package com.jolan.jvm.jun13;

/**
 * @author jolan80
 * @date 2020-06-13 16:40
 *
 * 5 方法去
 *  5.1 它存储了每一个类的结构信息
 *  5.2 方法去是规范，在不同虚拟机中实现不一样，最典型的就是永久代（PermGen space）和元空间(Metaspace)。实例变量存在堆内存中，和方法去无关
 *
 * 6 栈stack
 *  6.1 栈管运行，堆管存储
 *  6.2 栈也叫栈内存，主管Java程序的运行，是在县城创建时创建，它的生命期是跟随线程的生命期，线程结束栈内存也就释放，对于栈来说不存在垃圾回收的问题，只要线程一结束栈就
 *      结束，生命周期和线程一致，是线程私有的。8种基本类型的变量 + 对象的引用变量 + 实例方法都是在函数的栈内存中分配。
 *  6.3 栈帧 = java方法
 *      栈帧中主要保存3类数据：
 *      本地变量（Local Variables）：输入参数和输出参数以及方法内的变量；
 *      栈操作（Operand Stack）：记录出栈、入栈操作；
 *      栈帧数据（Frame Data）：包括类文件、方法等等。
 *      每个方法执行的同时都会创建一个栈帧，用于存储局部变量表，操作数据栈，动态链接，方法出口等信息，每一个方法从调用直至执行完毕的过程，就对应这一个栈帧在虚拟机中
 *          入栈到出栈的过程。栈的大小和具体JVM实现有关，通畅在256K~456K之间，约定于1M左右。
 *  6.4 StackOverflowError： 属于错误。比如方法无限循环的递归调用，会出现这个错误。
 *  6.5 栈+堆+方法去的交互关系
 *      HotSpot（sun公司实现jdk的规范）是使用指针的方式来访问对象：java堆中会存放访问类元数据的地址，reference存储的就直接是对象的地址
 *
 * 7 堆
 *  7.1 一个JVM实例只存在一个堆内存，堆内存的大小是可以调解的。类加载器读取了类文件后，需要把类、方法、常变量放到堆内存中，保存所有引用类型的真是信息，以方便执行器执行。
 *      堆内存逻辑上分为三部分：新生+养老+永久（元空间）
 *  7.2
 *      Eden满了，开启GC = YGC = 轻GC ，YGC会把Eden区域基本清空
 *          S0(Survivor pace) = from
 *          S1(Survivor pace) = to
 *          from区和to区，他们的位置不固定，每次GC后会交换，GC之后有交换，谁空谁是to
 *      Old养老区满了，开区Full GC = FGC。FGC多次发现养老区无法腾出更多空间，报错Out Of Memory OOM
 *      Eden:from:to = 8:1:1，from和to永远相等。Young:Old = 1:2
 *      MinorGC过程：复制->清空->互换。
 *          1.首先，当Eden区满的时候会触发第一次GC，把还活着的对象拷贝到SurvivorFrom区，当Eden区再次触发GC的时候会扫描Eden区和from区，对这两个区域进行垃圾回收，
 *              经过这次回收后还存货的对象，则直接复制到To区（如果有对象的年龄已经达到了老年的标准，则复制到老年代区），同时把这些对象的年龄+1
 *          2.清空Eden区和SurvivorFrom中的对象。
 *          3.SurvivorTo和SurvivorFrom交换：最后SurvivorTo和SurvivorFrom互换，原SurvivorTo成为下一次GC时的SurvivorFrom区。部分对象会在From和To区域中复制来复制去，如此交换15次（有JVM参数MaxTenuringThreshold
 *              决定，这个参数默认是15），最终如果还是存货，就存入老年代。
 *
 *
 *  */
public class JVMNote {
}
