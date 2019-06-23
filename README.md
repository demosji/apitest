
## How to Run
Run testng.xml in IDE tool
OR
mvn test

## Test Report
1. ${workspace}/test-output/index.html when you run testng.xml 
2. ${workspace}/target/test-output/index.html when you use maven test

## api-config.xml config

> The api request root path, request header, and initialization parameter values can be configured on the api-config.

- rootUrl: 
The required configuration, the root path of the api, is used for splicing when the api is called. After configuration, it is automatically added to the prefix of the url in the use case. 
- headers: 
It is not required to be configured. When the api is called, the corresponding name:value value will be set to the header-name:header-value in all request headers.

```
<root>
	<rootUrl>http://apis.baidu.com</rootUrl>
	<headers>
		<!-- xxxx -->
		<header name="apikey" value="123456"></header>
	</headers>
	<params>
		<param name="param1" value="value1"></param>
	</params>
	<project_name>report demo</project_name>
</root>
```
## api case(case/api-data.xls)

> The api requests use case specific data. In addition to the header, a line represents an api use case. Execution will be performed from left to right and top to bottom.

