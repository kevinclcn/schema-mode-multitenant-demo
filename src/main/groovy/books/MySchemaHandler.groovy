package books

import org.grails.datastore.gorm.jdbc.schema.SchemaHandler

import javax.sql.DataSource
import java.sql.Connection
import java.sql.ResultSet

/**
 * Created by long on 2017/6/11.
 */
class MySchemaHandler implements SchemaHandler {
    @Override
    Collection<String> resolveSchemaNames(DataSource dataSource) {
        Collection<String> schemaNames = []
        Connection connection
        try {
            connection = dataSource.getConnection()
            ResultSet schemas = connection.getMetaData().getSchemas()
            while(schemas.next()) {
                schemaNames.add(schemas.getString("TABLE_SCHEM"))
            }
        } finally {
            connection?.close()
        }
        return schemaNames
    }

    @Override
    void useSchema(Connection connection, String name) {
        connection
            .createStatement()
            .execute(String.format("use %s", name) )
    }

    @Override
    void useDefaultSchema(Connection connection) {
        connection
            .createStatement()
            .execute(String.format("use %s", "tenant1") )
    }

    @Override
    void createSchema(Connection connection, String name) {

    }
}
