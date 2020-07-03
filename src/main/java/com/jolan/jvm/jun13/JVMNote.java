package com.jolan.jvm.jun13;

import java.util.Random;

/**
 * @author jolan80
 * @date 2020-06-13 16:40
 *
 * 5 方法区
 *  5.1 它存储了每一个类的结构信息
 *  5.2 方法去是规范，在不同虚拟机中实现不一样，最典型的就是永久代（PermGen space）和元空间(Metaspace)。实例变量存在堆内存中，和方法去无关
 *
 * 6 栈stack
 *  6.1 栈管运行，堆管存储
 *  6.2 栈也叫栈内存，主管Java程序的运行，是在线程创建时创建，它的生命期是跟随线程的生命期，线程结束栈内存也就释放，对于栈来说不存在垃圾回收的问题，只要线程一结束栈就
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
 *  7.1 一个JVM实例只存在一个堆内存，堆内存的大小是可以调解的。类加载器读取了类文件后，需要把类、方法、常变量放到堆内存中，保存所有引用类型的真实信息，以方便执行器执行。
 *      堆内存逻辑上分为三部分：新生+养老+永久（元空间），物理上 = 新生 + 养老
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
 *      实际而言，方法去（Method Area）和堆一样，是各个线程共享的内存区域，它用于存储虚拟机加载的：类信息+普通常量+静态常量+编译器编译后的代码等等，虽然JVM规范将方法去描述为堆的一个逻辑部分，但它却还有一个别名
 *          叫做Non-Heap(非堆)，目的就是要和堆分开。对于HotSpot虚拟机，很多开发者习惯将方法区称之为“永久代（Parmanent Gen）”，但严格本质上说两者不同，或者说使用永久代来实现方法去而已，永久代是方法区（相当于
 *          是一个接口interface）的一个实现，jdk1.7的版本中，已经将原本放在永久代的字符串常量移走。
 *      永久区（java1.7之前）：永久区存储是一个常驻内存区域，用于存放JDK自身锁携带的Class，Interface的元数据，也就是说它存储的是运行环境必须的类信息，被装载仅此区域的数据是不会被垃圾回收器回收掉的，关闭JVM才会
 *          释放此区域的内存。
 *
 * 8. 在java8中，永久代已经被移除，被一个成为元空间的区域所取代。元空间的本质和永久代类似。元空间与永久代之间最大的区别在于：永久代使用的JVM的堆内存，但是java8
 *      以后的元空间并不在虚拟机中而是使用本机物理内存。因此，默认情况下，元空间的大小仅受本地内存限制。类的元数据放入native memory，字符串池和类的静态变量
 *      放入java堆中，这样可以加载多少类的元数据就不再有MaxPermSize控制，而有系统的实际可用空间来控制。
 *
 * 9. -Xms:设置初始分配大小，默认为物理内存的1/64
 *    -Xmx:最大分配内存，默认为物理内存的1/4
 *    -XX:+PrintGCDetails:输出详细的GC处理日志
 *    一次GC日志分析：
 *      YongGC：
 *      [PSYoungGen: 1991K->489K(2560K)] 1991K->833K(9728K), 0.0326066 secs] [Times: user=0.00 sys=0.00, real=0.03 secs]
 *          PSYoungGen：GC的类型，这里表示yangGC
 *          1991K->489K(2560K)：表示GC前伊甸区占用1991K，GC后占用489K，伊甸区总大小为2560K
 *          1991K->833K(9728K)：表示GC前堆内存占用为1991K，GC后占用为833K，堆内存总大小为9728K
 *          0.0326066 secs：GC耗时
 *          Times: user=0.00 sys=0.00, real=0.03 secs：user表示用户耗时，sys表示系统耗时，real表示实际耗时
 *      FullGC：
 *  *      [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] [ParOldGen: 3569K->3553K(7168K)] 3569K->3553K(8704K), [Metaspace: 2962K->2962K(1056768K)], 0.0115174 secs] [Times: user=0.13 sys=0.00, real=0.01 secs]
 *              Full GC (Allocation Failure：GC类型
 *              PSYoungGen: 0K->0K(1536K)：回收前0k，回收后0k，总大小为1536k
 *              ParOldGen: 3569K->3553K(7168K)：3569K表示回收前，3553K表示回收后，7168K表示总大小
 *              3569K->3553K(8704K)：3569K表示GC回收前的堆大小，3553K表示回收后的堆大小，8704K表示堆总大小
 *              Metaspace: 2962K->2962K(1056768K):元空间或永久代，GC前空间2962K，GC后空间2962K，总空间1056768K
 *              0.0115174 secs:GC总时间
 *              Times: user=0.13 sys=0.00, real=0.01 secs：用户时间0.13 ，系统时间0.00，实际时间0.01
 *
 * 9. GC四大算法：
 *  9.1 引用计数法
 *      每有对象引用时计数器+1，当没有被对象引用时候，可以被回收。
 *      缺点：每次对对象赋值时均要维护引用计数器，且计数器本身也有一定的消耗；较难处理循环引用；
 *  9.2 复制算法（Copying）
 *      年轻代中使用的是Minor GC，这种GC算法采用的是复制算法算法（Copying）。复制算法不会产生内存碎片，缺点是耗费空间。如果对象存活率高，会非常浪费时间。
 *  9.3 标记清除（Mark-Sweep）
 *      老年代一般是由标记清除或者是标记清除与标记整理的混合实现。分为标记和清除两个阶段，先标记出要回收的对象，然后统一回收这些对象。
 *      优点是节约内存空间，缺点是会产生内存碎片，扫描两次比较耗时
 *  9.4 标记压缩（Mark-Compact）
 *       全称标记清除压缩算法，简称标记整理算法。比标记清除算法多了整理的步骤。缺点是耗时较长。
 *       在标记清除的基础上，再次扫描，并往一段滑动存活对象
 *
 *  */
public class JVMNote {
    public static void main(String[] args) {
//        getJVMParams();
        testOOM1();
//        testOOM2();
        return;
    }

    private static void testOOM2() {
        byte[] bytes = new byte[40 * 1024 *1024];
        return;
    }

    private static void testOOM1() {
        String str = "www.jolan.com";
        while (true){
            //-Xms10m -Xmx10m -XX:+PrintGCDetails
            //调整jvm参数为10M，java.lang.OutOfMemoryError: Java heap space
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999);
        }
    }

    private static void getJVMParams() {
        System.out.println(Runtime.getRuntime().availableProcessors());//获取cpu核数
        long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存
        long totalMemory = Runtime.getRuntime().totalMemory();//返回java虚拟机中的默认内存总量
        System.out.println("-Xmx:MAX_MEMORY = " + maxMemory + "(字节)、" + (maxMemory / (double)1024/1024) + "MB");
        System.out.println("-Xms:TOTAL_MEMORY = " + totalMemory + "(字节)、" + (totalMemory / (double)1024/1024) + "MB");
    }
}
