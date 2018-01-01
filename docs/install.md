 Installation
 ============

**Step 1:** Configure to use the Kaha repository (hosted on Bintray).

For Maven:
```xml
<repositories>
    <repository>
        <id>bintray-ahwnz-kaha</id>
        <name>bintray-kaha</name>
        <url>https://dl.bintray.com/ahwnz/kaha</url>
    </repository>
</repositories>
```

For Gradle:
```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/ahwnz/kaha" 
    }
}
```

**Step 2:** Add the dependency (where `{version}` is replaced with the desired Kaha version).

For Maven:
```xml
<dependencies>
    <dependency>
      <groupId>nz.ahw.kaha</groupId>
      <artifactId>kaha</artifactId>
      <version>{version}</version>
    </dependency>
</dependencies>
```

For Gradle
```groovy
compile 'nz.ahw.kaha:kaha:{version}'
```
