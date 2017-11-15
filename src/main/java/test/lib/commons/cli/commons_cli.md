Apache Commons CLI 库用于命令行的解析。
CLI 支持的命令格式：
- POSIX 格式：如 tar -zxvf foo.tar.gz
- GNU 格式：如 du --human-readable --max-depth=1
- Java 属性格式：如 java -Djava.awt.handless=true -Djava.net.userSystemProxies = true Foo
- 短格式：gcc -02 foo.c
- 长格式，单连字符：ant -projecthelp

# 快速入门
Apache Commons CLI 命令行解析包含三步：定义，解析和查询。

## 定义阶段
在定义阶段，需要定义好所有可能的命令选项。CLI使用 `Option`类表示一个命令，使用 `Options`表示一串命令。
`Options`的创建，可以使用构造函数，也可以使用 `Options` 的工厂方法。

**boolean Option**

如果命令行中存在该选项，就表示 `true`，否则表示 `false`。

例如：如果命令行中存在 `-t` 选项，则输出当前时间。定义方式：

```java
Options options = new Options();
options.addOption("t", false, "display current time");
```

`addOption` 方法包含三个参数：
- 参数一表示选项名
- 参数二表示该选项是否需要参数，对 boolean 参数，不需要额外参数
- 参数三是选项的描述信息

下面以 Ant 命令为例说明如何定义 `Option`：
```
ant [options] [target [target2 [target3] ...]]
  Options: 
  -help                  print this message
  -projecthelp           print project help information
  -version               print the version information and exit
  -quiet                 be extra quiet
  -verbose               be extra verbose
  -debug                 print debugging information
  -emacs                 produce logging information without adornments
  -logfile <file>        use given file for log
  -logger <classname>    the class which is to perform logging
  -listener <classname>  add an instance of class as a project listener
  -buildfile <file>      use given buildfile
  -D<property>=<value>   use value for given property
  -find <file>           search for buildfile towards the root of the
                         filesystem and use it
```

要解析 Ant 的命令，首先要定义各个命令对应的 `Option`。

#### Boolean Options 定义
```java
Option help = new Option("help", "print this message");
Option projecthelp = new Option("projecthelp", "print project help information");
Option version = new Option("version", "print the version information and exit");
Option quiet = new Option("quiet", "be extra quiet");
Option verbose = new Option("verbose", "be extra verbose");
Option debug = new Option("debug", "print debugging information");
Option emacs = new Option("emacs", "produce logging information without adornments");
```

#### Argument Options 定义
```java
Option logfile = Option.builder("logfile").argName("file").hasArg().desc("use given file for log").build();
Option logger = Option.builder("logger").argName("classname").hasArg().desc("the class which it to perform logging").build();
Option listener = Option.builder("listener").argName("classname").hasArg().desc("add an instance of class as a project listener").build();
Option buildfile = Option.builder("buildfile").argName("file").hasArg().desc("use given buildfile").build();
Option find = Option.builder("find").argName("file").hasArg().desc("search for buildfile towards the root of the filesystem and use it").build();
```

#### Java Property Option
最后一部分是 Java 属性定义，定义方式如下：
```java
Option property = Option.builder("D").argName("property=value").numberOfArgs(2).valueSeparator()
        .desc("use value for given property").build();
```

#### 创建 Options
```java
Options options = new Options();

options.addOption( help );
options.addOption( projecthelp );
options.addOption( version );
options.addOption( quiet );
options.addOption( verbose );
options.addOption( debug );
options.addOption( emacs );
options.addOption( logfile );
options.addOption( logger );
options.addOption( listener );
options.addOption( buildfile );
options.addOption( find );
options.addOption( property );
```

## 解析阶段
使用 `CommandLineParser` 解析命令行，返回 `CommandLine` 对象。`CommandLineParser` 是接口，一般使用其默认实现 `DefaultParser`。

创建 `Parser`:

```java
CommandLineParser parser = new DefaultParser();
try {
    parser.parse(options, args);
} catch (ParseException e) {
    e.printStackTrace();
}
```

## 查询阶段
通过如下方式查询值：
```java
// has the buildfile argument been passed?
if( line.hasOption( "buildfile" ) ) {
    // initialise the member variable
    this.buildfile = line.getOptionValue( "buildfile" );
}
```

## 输出帮助信息
在参数错误时，输出帮助信息，方式如下：
```java
// automatically generate the help statement
HelpFormatter formatter = new HelpFormatter();
formatter.printHelp( "ant", options );
```


# Option 属性

|Name|Type|说明|
|---|---|---|
|opt|String|用于识别 Option 的字符串|
|longOpt|String|加长版的识别字符串，一般更具有描述性|
|description|String|Option 的描述信息|
|required|boolean|是否是必须选项|
|arg|boolean|该选项是否包含参数|
|args|boolean|该选项是否包含多个参数|
|optionalArg|boolean|其参数是否是可选的|
|argName|String|参数名称|
|valueSeparator|char|参数分隔符，和 `multipleArgs`对应|
|type|Object|参数类型|
|value|String|选项值|
|values|String[]|选项所有的值|

以上所有的属性值都可以通过 `OptionBuilder` 中对应的 set 方法设置。

# 参考资料
- http://commons.apache.org/proper/commons-cli/index.html