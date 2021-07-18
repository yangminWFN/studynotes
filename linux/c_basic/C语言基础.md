# linux下编写C语言程序

## 一、C语言基础

### 1、头文件

- 例子：

  ```c
  #include <stdio.h>
  #include "mylib.h"
  int main(){
      printf("hello world\n");
      return 0;
  }
  ```

  这是两种声明头文件的方法:

  ==尖括号==表示==到环境指定的目录去引用==

  ==双引号==表示==首先在当前目录查找，然后在到环境指定的目录去引用==
  在C语言标准库中每个库函数都在一个头文件中声明，可以通过第一种方式引用

  `#include 头文件.h`的作用在C语言中，相当于把`头文件.h`中的代码cv到当前文件中，`头文件.h`中一般包括结构体、常量、类型定义、函数定义等内容

- **头文件基本语法--以`mylib.h`为例**

  ```c
  #ifndef _MYLIB_H_      //一般是头文件名的前后加下划线，然后.变成下划线
  #def _MYLIB_H_
  
  typedef struct _node{
      int value;
      struct _node *next;
  }Node;
  
  #endif
  ```

  ```c
  //使用头文件
  #include <stdio.h>
  #include "mylib.h"
  int main(){
      Node *p = (Node*)malloc(sizeof(Node));
      return 0;
  }
  ```

- 常用头文件

  ```c
  stdio.h 标准输入输出
  stdlib.h 标准常用库
  string.h 字符串函数库
  math.h 数学库
  ctype.h 字符函数库
  time.h 时间库
  windows.h 微软视窗库
  ```

### 2、结构体

- 定义普通结构体

  ```c
  typedef struct Student{
      char *name;
      int age;
  }Stu;
  ```

  使用一般结构体

  ```c
  //定义结构体
  struct Stu stu;
  //或
  struct Student stu;
  
  //初始化结构体
  stu.age = 10;
  stu.name = "哈哈哈";
  ```

  

### 3、指针

- 基本指针

  ```c
  int m = 4;
  int *p;
  p = &m;
  printf("p:%p\n",p);
  *p = 6;
  printf("m:%d,*p:%d",m,*p);
  /*输出
  p:0x7ffc0a6527ac
  m:6,*p:6
  */
  ```

- 指针数组

  ```c
  #include<stdio.h>
  int main(){
          int m[] = {1,2,3};
          int *p[3];
          int i;
          for(i = 0;i<3;i++){
                  p[i] = &m[i];
          }
          printf("*p:");
          for(i = 0;i<3;i++){
                  printf("%d ",*p[i]);
          }
          printf("\n m:");
          for(i = 0;i<3;i++){
                  printf("%d ",m[i]);
          }
          printf("\n");
          return 0;
  }
  /*输出
  *p:1 2 3
   m:1 2 3
  */
  ```

- 结构体指针

  ```c
  #include<stdio.h>
  #include<stdlib.h>
  #include<string.h>
  typedef struct Student{
          char name[10];
          int age;
  }Stu;
  int main(){
          Stu *p = (Stu*)malloc(sizeof(Stu));  //sizeof中是Stu或者Stu*都可以
          strcpy(p->name,"yangmin");
          p->age=19;
          printf("name:%s,age:%d",p->name,p->age);
          return 0;
  }
  
  //第二种方式
  #include<stdio.h>
  #include<stdlib.h>
  #include<string.h>
  typedef struct Student{
          char name[10];
          int age;
  }Stu;
  int main(){
          Stu stu; //注意定义时，不能加struct
      	//但可以使用 struct Student stu;
          strcpy(stu.name,"yangmin");
          stu.age=19;
          Stu *p = &stu;
          printf("name:%s,age:%d\n",p->name,p->age);
          return 0;
  }
  /*输出
  name:yangmin,age:19
  */
  ```

  若==struct node {}==这样来定义结构体的话。在申请node 的变量时，需要这样写，==struct node n==;
  若用==typedef==，如==typedef struct node{}NODE==；。在申请变量时就可以这样写，==NODE n==;
  区别就在于使用时，==是否可以省去struct==这个关键字

  *==**使用typedef方式定义结构体，使用别名声明结构体时不能加 struct**==*

- 指针运算

  ```c
  //数组的数组名其实可以看作一个指针
  int a[6]={1,2,3,4,5,6};
  for(int i=0;i< 6;i++){
      printf("%d ",*(a+i));
  }
  //或者
  int *p = a;
  *(p+i) 替换 *(a+i)
  
  其中 p++，表示在当前内存地址上，加上1个int类型所占字节长度；
  
  ```

  

### 4、字符串

- 字符串初始化方式

  ```c
  //字符串初始化的几种方式 
  //第一种
  int main(){
          char str[] = "hello";//实际上是6个字符，最后面有一个'\0'
      	//不能先声明char str[];str="hello";会报错
          printf("%s\n,len:%d\n",str,(int)(sizeof(str)/sizeof(str[0]))); //打印字符数组长度
          return 0;
  }
  /*输出
  hello,6
  */
  
  //第二种
  char *str;
  str = "hello";
  printf("%s\n,len:%ld\n",str,strlen(str)); //strlen(char*)求的是字符串长度，不包括最后的'\0'
  /*输出
  hello,5
  */
  
  ```



### 5、文件IO

- 文件读写

  ```c
  //文件写入
  int main(){
      FILE *fp = NULL;
      if((fp = fopen("./my.txt","w+")) == NULL){
          return -1;//打开文件失败
      }
      fputs("1.hello world\n",fp);
      fputs("2.java hahaha\n",fp);
      fclose(fp);
  }
  //文件读取
  #define SIZE 255
  int main(){
      FILE *fp = NULL;
      char buf[SIZE] = {};
      if((fp = fopen("./my.txt","r")) == NULL){
          return -1;//打开文件失败
      }
      //fgets遇到'\n'或者SIZE个字符后返回,始终会在返回的字符数组后面添加'\0'
      char *str = NULL;
      while((str = (fgets(buf,SIZE+1,fp)) != NULL){
          printf("%s",str);
      }
      return 0;
  }
  ```

  



## 二、gcc编译C程序

### 1、gcc编译过程：预处理->编译->汇编->链接


gcc无需加选项：对应gcc代码：gcc hello.c –o hello

#### 预处理 -E

**作用：**对头文件和宏定义等进行处理

对应gcc代码：gcc –E hello.c –o hello.i

#### 编译 -S

**作用：**检查代码是否有语法错误，并将其翻译成汇编语言

对应gcc代码：gcc –S hello.i –o hello.s

#### 汇编 -c

**作用：**将编译阶段生产的汇编.s文件，处理为.o目标文件

对应gcc代码：gcc –c hello.s -o hello.o

#### 链接

**作用：**将多个.o目标文件组装成最后的可执行文件

对应gcc代码：gcc hello.o -o hello

#### 运行 ./文件名

对应代码：./文件名

Ps：gcc编译多文件举例gcc -o main main.c sub_add.c unsgn_pow.c -I/include -L. -lmymath
其中，-I ./include 告诉gcc在“./include”目录下搜寻头文件
**-L. **表示要链接的库，库名为libmymath.a，链接时写为-lmymath