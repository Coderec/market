applicationContext-action.xml	
	struts action的IOC配置，在struts包中配置action时用到。


applicationContext-common.xml
	配置一个list，包含各类查询、操作数据库的语句，详情见“查询语句配置关系图”。


applicationContext-hibernate-common.xml
	Hibernate事务管理相关配置


applicationContext-hibernate-manager.xml
	基于Hibernate的manager的IOC配置。注意：各个manager都没有明确说明注入属性，也即没有指定setter方法注入或者构造方法注入，但是声明了byName方式的自动注入，因此要求被注入对象中的依赖对象必须与bean的id同名。


applicationContext-hql-configure.xml
	hql映射配置，详情见“查询语句配置关系图”。


applicationContext-jdbc-common.xml
	针对jdbc的数据库连接信息配置（本项目中使用sql语法操作时使用jdbc，而使用hql操作时使用Hibernate。不过不论是用jdbc还是Hibernate都是使用的spring提供的JdbcTemplate或者HibernateTemplate），之所以要在这个文件中配置jdbc的连接信息，是因为jdbc不能使用位于classPath：hibernate.cfg.xml配置文件。


applicationContext-jdbc-manager.xml
	基于jdbc的manager的IOC映射


applicationContext-sql-configure.xml
	sql映射配置，详情见“查询语句配置关系图”。