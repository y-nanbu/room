import org.springframework.jdbc.datasource.DriverManagerDataSource

beans {
    xmlns context:"http://www.springframework.org/schema/context"
    xmlns tx:"http://www.springframework.org/schema/tx"

    dataSource(DriverManagerDataSource) {
        driverClassName = "org.postgresql.Driver"
        url = "jdbc:postgresql://172.17.20.221:5432/room-ut"
        username = "room-db"
        password = "room-db"
    }

    context."component-scan"("base-package": "jp.co.tads.room")
}