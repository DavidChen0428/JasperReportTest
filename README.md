# **JasperReport**

- src/main/java
  - com.project.davic
    - controller
      - MemberController.java(Member類的RestfulAPI)
      - RegisterLoginController.java(Login和Register功能)
      - **ReportController.java(報表類的Restful)**
    - dao
      - MemberRepository.java(interface)(Member的資料庫接口)
    - model
      - Member.java(class)
      - Gender.java(Enum)
    - service
      - impl
        - MemberServiceImpl.java(class)(MemberService的實作類)
        - **ReportServiceImpl.java(class)(ReportService的實作類)**
      - MemberService.java(interface)(Member類的Service接口)
      - **ReportService.java(interface)(報告接口)**
      - **ReportException.java(class)(包裝ReportService可能會發生的例外處理)**
- src/main/resources
  - **fonts(存放自定義字型(.TTF)及其封包(.jar))**
  - **jasperReport(存放.jasper檔和.jrxml檔)**
  - static
  - templates
  - application.properties(設置資料庫連線、Restful API、資料庫管理選項)
- src/test/java
  - com.project.david
    - pdf(測試下載至本地端方法功能是否正常)
    - service(測試是否能新增資料到資料庫端)
- **pox.xml (載入套件)**
---
## 參考連結
https://blog.csdn.net/c_2333/article/details/126800234<br>
https://ithelp.ithome.com.tw/m/articles/10355419
---
## 使用工具
- Eclipse
- Jaspersoft Studio(生成JasperReport)
- MySQL
- Postman
---
## 載入pom.xml 套件
- Lombok
- MySQL Driver
- Spring Boot DevTools
- Spring Web
- Spring Data JPA
- Thymeleaf
- Swagger API(Restful線上介面)
- JasperReport(含font)(共2套件) -> 處理jasperreport資料
- Apache.poi(共4套件) -> 處理excel的讀取及寫入
- font(自定義字型)
