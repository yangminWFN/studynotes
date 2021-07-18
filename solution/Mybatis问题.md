# Mybatis存在问题

1. 在 insert 语句中获取自增主键的两种方式中，如果是第二种，对于没有自增主键的数据库，模拟获取自增主键的方式，会存在自增的值获取不到的问题

   ```java
   <!--    <insert id="insertEmployee" useGeneratedKeys="true" keyProperty="id">-->
   <!--        INSERT INTO employee(name,gender,email) VALUES (#{employee.name},#{employee.gender},#{employee.email});-->
   <!--    </insert>-->
   
   <!--    使用selectKey方式获取自动生成的主键Id赋给employee，传入的employee对象如果用@param注解修饰，keyProperty必须使用employee.id，即与下面插入语句中的占位字符串#{employee.id}一致-->
       <insert id="insertEmployee">
           <selectKey order="BEFORE"  resultType="integer" keyProperty="employee.id">
               select max(id)+1 from employee;
           </selectKey>
           INSERT INTO  employee(id,name,gender,email) VALUES (#{employee.id},#{employee.name},#{employee.gender},#{employee.email})
       </insert>
               
               
       //当前接口
       int insertEmployee(@Param("employee")Employee employee);
   
   	//也可以直接使用属性名，如#{id},那么keyProperty也要使用 id，就说两者要保持一致
   ```

   

2. 