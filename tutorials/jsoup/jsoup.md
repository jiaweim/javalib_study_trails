# 概述
解析 HTML 文档：

```java
String html = "<html><head><title>First parse</title></head>"
  + "<body><p>Parsed HTML into a doc.</p></body></html>";
Document doc = Jsoup.parse(html);
```

解析器会尽力解析内容，包括格式不规范的 HTML 文档：
- 没有关闭标签（如 `<p>Lorem <p>Ipsum` 被解析为 `<p>Lorem</p> <p>Ipsum</p>`）
- 隐含标签（如 `<td>Table data</td>` 被解析为 `<table><tr><td>...`）

## 文档的对象模型
- 一个HTML文档包含 Elements 和 TextNodes
- `Document` extends `Element` extends `Node`. `TextNode` extends `Node`
- 一个 Element 包含一系列的子节点和一个父节点。

# HTML 解析

## 解析字符串
HTML文档内容以字符串的形式存在，解析该内容，可以使用 `Jsoup.parse(String html)` 方法。

```java
String html = "<html><head><title>First parse</title></head>"
  + "<body><p>Parsed HTML into a doc.</p></body></html>";
Document doc = Jsoup.parse(html);
```