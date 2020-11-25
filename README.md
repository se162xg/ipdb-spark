## ipdb-spark

### 新特性
1. 可以兼容 IPv4 数据库与 IPv6 数据库查询
```
val ipdb = new City("dbv4.ipdb") 

或 

val ipdb = new City("dbv4.ipdb", "dbv6.ipdb")

ipdb.findInfo("2002:2dc5:9642::2dc5:9642")
ipdb.findInfo("112.64.64.43")

```
2. 对 IPv6 6to4 地址转换后查询，解决了 6to4 地址无法查询到地理位置的问题

### 使用示例
```
//test.scala
import com.se162xg.City;
import org.apache.spark.SparkFiles;
sc.addFile("dbv4.ipdb");                                         
sc.addFile("dbv6.ipdb");
val db = sc.broadcast(new City(SparkFiles.get("dbv4.ipdb"),SparkFiles.get("dbv6.ipdb")));
spark.udf.register("getRegionName", (ip:String)=>{db.value.findInfo(ip).getRegionName()});
spark.sql("SELECT getRegionName(ip)");
```

### 编译
1. 在 build.sbt 中修改为对应的 scala 版本
2. 执行 `sbt assembly` 
