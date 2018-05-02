package com.taotao.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Created by Administrator on 2018/4/25.
 */
public class TestFreeMarker {

    @Test
    public void testFreeMarker() throws Exception{
        //1.创建一个模板文件(就是我们刚创建的hello.ftl模板)
        //2.创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //3.设置模板所在的路径
        configuration.setDirectoryForTemplateLoading(new File("D://IDEA java/Factory/taotao-parent/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
        //4.设置模板的字符集，一般为utf-8
        configuration.setDefaultEncoding("utf-8");
        //5.使用Configuration对象加载一个模板文件，需要指定模板文件的文件名。
        Template template = configuration.getTemplate("student.ftl");
        //6.创建一个数据集，可以是pojo也可以是map，推荐使用map
        Map data = new HashMap();
        data.put("hello", "hello freemarker");
        Student student=new Student(1,"张三",18,"北京市海鼎区西二旗");
        data.put("student",student);
        List<Student> studentList=new ArrayList<Student>();
        studentList.add(new Student(1,"小米",20,"北京市昌平区小米科技有限公司"));
        studentList.add(new Student(2,"小米",20,"北京市昌平区小米科技有限公司"));
        studentList.add(new Student(3,"小米",20,"北京市昌平区小米科技有限公司"));
        studentList.add(new Student(4,"小米",20,"北京市昌平区小米科技有限公司"));
        studentList.add(new Student(5,"小米",20,"北京市昌平区小米科技有限公司"));
        studentList.add(new Student(6,"小米",20,"北京市昌平区小米科技有限公司"));
        data.put("stuList",studentList);
        //日期类型
        data.put("date",new Date());
        //给变量赋值
        data.put("val",123);
        //7.创建一个Writer对象，指定输出文件的路径及文件名
        Writer out = new FileWriter(new File("D://IDEA java/Factory/freemarker/out/student.html"));
        //8.使用模板对象的process方法输出文件
        template.process(data, out);
        //9.关闭流
        out.close();
    }
}
