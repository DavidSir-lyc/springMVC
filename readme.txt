在这个学习性web项目中，大的目录分成了java、resources、webapp三部分。

java目录下：
    bean，这个目录是存放和数据库对应的基本类；
    controller，这里面是存放控制器类，这就类似于之前Servlet的作用；
    dao，这是存放数据库映射文件，功能和之前的JDBC相同；
    service，service是和dao层交互的服务层，实现具体的功能，比如插入一条数据之类的。
resources目录下：
    resources，资源路径，多数的配置文件和Mybatis的映射文件都放在这里。
webapp目录下：
    webapp，这里面不放jsp，多出来一个SpringMVC的配置文件