<html>
<head>
	<title>测试页面</title>
</head>
<body>
	学生信息：<br>
	学号：${student.id}<br>
	姓名：${student.name}<br>
	年龄：${student.age}<br>
	家庭住址：${student.address}<br>
    学生列表：<br>
    <table border="1" width="500">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>地址</th>
        </tr>
	<#list stuList as stu>
		<#if stu_index%2==0>
        <tr bgcolor="red">
		<#else>
        <tr bgcolor="blue">
		</#if>
            <td>${stu_index}</td>
            <td>${stu.id}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.address}</td>
        </tr>
	</#list>
    </table>
    <br>
    日期类型的处理：${date?string("yyyy/MM/dd HH:mm:ss")}
	<br>
	null值的处理：${val!"默认值"}
	<br>
	使用if判断null值：
	<#if val??>
	val是有值的。。。
	<#else>
	val值为null。。。
	</#if>
	<br>
	include标签测试：
	<#include "hello.ftl">
</body>
</html>