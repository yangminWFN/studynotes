# 一、模板语法
## 代码示例
```
    var app=new Vue({
        el:"#app"
        data:{
            message:"hello world",
            rawHtml:"<span style='color:red'></span>",
            currentID:"myID",
            isDisabled:true,
            canSee:true,
            eventname:"click",
            attrname:"disabled"
        }
    })
```
## 注意点
1. `{{message}}`将`$data`中的变量转化为文本

2. `<span v-html='rawHtml'></span>`来将`$data`中的`rawHtml`字符串进行渲染

3. 在html标签的属性上绑定`$data`中的变量时使用`v-bind:id="myID"`，可以简写为`:id="myID"`

4. 在使用`v-bind:disabled="isDisabled"`时，如果`isDisabled`是`false，undefinded，null`，则`disabled`属性不会显示在标签上

5. 使用`v-if="canSee"`指令来表示该标签是否显示出来

6. 使用`v-on:click="btnClick"`指令来绑定事件处理函数，可以简写为`@click="btnClick"`

7. 在`v-on:click="btnClick"`和`v-bind:disabled="isDisabled"`这两个指令中，`click`和`disabled`被称作指令的参数。
在vue2中参数是可以动态指定的。语法为`v-on:[eventname]="btnClick"`以及`v-bind:[attrname]="isDisabled"`,需要注意的是，`eventname`和`attrname`这种动态参数中不要出现大写字母，要使用小写字母和`$data`中的变量绑定，因为浏览器会默认将属性名称转化为小写字母

8. 修饰符待补充：`<form v-on:submit.prevent="onSubmit"></form>` ，其中`.prevent`为修饰符，表示使用`e.preventDefault()`

# 二、计算属性
## 代码示例
```
    <p>{{message}}</p>
    <p>{{reverseMessage}}</p>
    var app=new Vue({
        el:"#app",
        data:{
            message:"hello world"
        },
        computed:{
          reverseMessage:function(){
                return this.message.split('').reverse().join('');
      }
        }
    })
```
## 注意点
1. 使用`computed`来指定一个Vue实例中的所有计算属性，使用函数的方式（getter）来返回计算属性的值

2. 计算属性的值与`$data`中的值的改动相响应，`message`变则`reverseMessage`也改变

# 三、侦听属性
## 代码示例
```
var app=new Vue({
        el:"#app",
        data:{
            message:"hello world"
            reverseMessage:""
        },
        watch:{
            message:function(newVal,oldVal){
                this.reverseMessage=newVal.split('').reverse().join('');  //在这里使用计算属性更优
            }
        }
    })
```
## 注意点
1. 在vue2中每个属性的侦听属性的参数列表中，第一个是新的值，第二个是原始值。

2. 如果需要根据值的改变来请求数据，需要限制请求的时间间隔

# 四、class和style样式渲染
## 代码示例
```
    <p v-bind:class="classObject">我是第一段文字</p>
    <p v-bind:style="styleObject">我是第二段文字</p>
    var app=new Vue({
        el:"#app",
        data:{
            classObject:{
                'text-danger':true,
                'text-center':true
            },
            styleObject:{
                color:"red",
                'font-size':"30px"
            }
        }
    })
```
## 注意点：
1. 使用`v-bind`来进行样式的绑定

2. 对于`v-bind:class`和`v-bind:style` Vuejs作了优化，使得`v-bind:class="classObject"`中的`classObject`既可以是字符串类型的值，又可以是数组和对象。`v-bind:style`是类似的。经常使用在`$data`中指定<对象>的方式来指定样式

# 五、条件渲染
## 代码示例：
```
    <template v-if="isSeen">
    <h1>我是标题</h1>
    <p key="p1">我是第一段文字</p>
    </template>
    <template v-else>
    <h2>哈哈</h2>
    <p key="p2">我是第二段文字</p>
    </template>
    var app=new Vue({
        el:"#app",
        data:{
            isSeen:true
        }
    })
```
## 注意点：
1. `v-if`会在dom树中添加或者移除掉节点元素，但是在不同的模板元素中进行切换时VueJS会进行计算缓存一些dom节点元素来减少dom操作。如果不需要缓存这些信息，需要手动给对应元素指定唯一的`key`属性

2. `v-show`采用惰性加载，直到第一次值为`true`时才会被渲染出来。另外它只是单纯的改变`css`的`display`属性！

3. `v-if`禁止与`v-for`联合使用，`v-for`的优先级将高于`v-if`。
    * ***根据条件过滤掉不符合条件的列表元素***  
      **正确方法：使用计算属性作为列表集合元素**  
        *示例代码：*
        ```
        <ul>
            <li v-for="user in activeUsers" v-bind:key="user.id">{{user.name}}</li>
        </ul>
        var app=new Vue({
            data:{
                users:[{id:1,name:"yangmin"},{id:2,name:"chenyu"},{id:3,name:"haha"}]
            },
            computed:{
                activeUsers:function(){
                    return this.users.filter(function(item){return item.id>=2;})
                }
            }
        })
        ```
    * ***根据一个属性是否显示某个列表***  
      **正确方法：在父元素或者template元素中指定v-if**  
      *示例代码：*
      ```
        <template v-if="seen">
            <ul>
                <li v-for="user in users" v-bind:key="user.id">{{user.name}}</li>
            </ul>
        </template>
        <template v-else>
            <p>暂无记录</p>
        </template>
        var app=new Vue({
            data:{
                users:[{id:1,name:"yangmin"},{id:2,name:"chenyu"},{id:3,name:"haha"}],
                seen:true
            }
        })
      ```

# 四、列表渲染
## 代码示例：
```
        <ul>
           <li v-for="(item,index) in users">
               {{index}} : {{item.name}}
           </li>
       </ul>
        <ul>
            <li v-for="(value,name) in currentObject">
                {{name}}:{{value}}
            </li>
        </ul>

        var app = new Vue({
            el: "#app",
            data: {
                users: [{ id: "user1", name: "yangmin" }, { id: "user2", name: "chenyu" }, { id: "user3", name: "haha" }],
                currentObject:{
                    name:"张三",
                    skill:"无能狂怒",
                    age:"40",
                    rank:"二弟"
                }
            }
        })
```
## 注意点：
1. `v-for `不仅可以对列表进行遍历，而且可以对对象中的属性进行遍历。

2. 遍历列表时参数为 `(item,index)` ;遍历对象时参数为 `(value.name,index)`

3. 建议尽可能在使用 `v-for` 时提供 `key attribute`，除非遍历输出的 DOM 内容非常简单，或者是刻意依赖默认行为以获取性能上的提升

4. 在使用变异方法 `(mutation method)` 例如 (pop,shift,splice,sort,reverse等) ，或者非变异方法例如 (filter,slice,concat等) 对数组元素进行更新时，由于VueJS对这些方法进行了包裹，将**高效的**触发视图更新。

5. 在以下两种情况下，**不会对视图进行更新**
   * **使用数组下标获取值后进行赋值**，即 `app.items[index] = newValue`。但是在使用 `app.items[index].name="yangmin"`进行更新时，或者使用 `app.items[index].obj={city:"haha",name:"哈哈"}`**会对视图进行更新**，也就是说***只要不改变当前数组元素的引用，改变其内部属性的值（不管值是字符串还是对象）都会触发视图更新！！！***
   * 对应解决办法：`app.items.splice(index,1,newValue)`或者 `Vue.set(items,index,newValue)`
   * **直接改变数组的长度来改变数组**，即 `app.items.length=2`
   * 对应解决办法： `app.items.splice(2)`

6. 在对 `app` 中的对象进行更新时，可以使用 `Vue.set(app.currentObject,attrName,value)`来**更新单个属性的值**。如果需要**批量更新一个对象的值**，需要使用两个对象的属性构建一个**新的对象**
```
    app.currentObject=Object.assign({},app.currentObject,{
        age:27,
        name:"yangmin"
    })
```

7. `v-for="n in 10"`可以接受**整型**数值，代表将该模板渲染10次

8. 在组件上使用 `v-for`,***待补充...***

# 五、Vue组件
## 代码示例
```
//全局注册组件
Vue.component("custom-button",{
    data:function(){
        return {
            count:0
        }
    },
    template:'<button @click="count++">点击{{count}}次了</button>'
})
```
## 注意点
1. **组件是可复用的Vue实例**，它的 `data` 属性必须是一个**函数**

2. 