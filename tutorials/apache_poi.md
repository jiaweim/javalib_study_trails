

# 简介
apache poi 用于读写 office 文件，包括两部分：
- OLE2 文件格式（office2007之前的格式，如xls,doc等）
- OOXML (Office Open XML) 格式（.xlsx等）


|应用类型|组件|jar包|说明|
|---|---|---|---|
|OLE2文件系统|POIFS|poi|OLE2文件是一种类似zip格式的压缩文件，同时支持读写；包含 xls, doc, ppt以及基于 MFC serialization API的文件格式|
|OLE2属性集|HPSF|poi||
|xls|HSSF|poi|MS-Excel 97-2003 (.xls)，基于BIFF8格式的java接口|
|doc|HWPF|poi-scratchpad|MS-Word 97-2003，基于BIFF8格式的java接口。支持.doc文件的简单读写功能|
|ppt|HSLF|poi-scratchpad|PowerPoint PPT|
|vsd|HDGF|poi-scratchpad|Visio|
|publisher|HPBF|poi-scratchpad|Publisher pub|
|outlook|HSMF|poi-scratchpad|Outlook msg|
|xlsx|XSSF|poi-ooxml|MS-Excel 2007+|
|docx|XWPF|poi-ooxml|MS-Word 2007+|
|pptx|XSLF|poi-ooxml|MS-PowerPoint|

jar 依赖项：

|Maven artifactId|prerequisites|jar|
|---|---|---|
|poi|


# Excel
HSSF 和 XSSF 提供了读取、修改和输出EXCEL文件的方法：
- 为特别需求提供 low level 结构
- 为只读提供高效的事件模型API
- 为创建、读取和修改提供完整的usermodel api


## XSSF and SAX
使用底层API，可以访问XSSF下的XML数据，减低

## SXSSF
SXSSF 通过限制可访问的 rows 来降低内存占用，而XSSF可以访问文件的所有内容。

