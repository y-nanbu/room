import org.springframework.jdbc.datasource.DriverManagerDataSource

beans {
    xmlns context:"http://www.springframework.org/schema/context"
    xmlns tx:"http://www.springframework.org/schema/tx"

    dataSource(DriverManagerDataSource) {
        driverClassName = "org.h2.Driver"
        url = "jdbc:h2:file:~/room-ut"
        username = "sa"
        password = ""
    }

    context."component-scan"("base-package": "jp.co.tads.room")
}