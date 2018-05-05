package duplicatefilefinder.app.config;

import static duplicatefilefinder.app.utils.DataBaseUtils.*;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ravi Nain on 4/15/2018.
 */
public class CMPhysicalNamingStrategy implements PhysicalNamingStrategy {

    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final List<String> parts = splitAndReplace(name.getText());
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                join(parts),
                name.isQuoted()
        );
    }

    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final LinkedList<String> parts = splitAndReplace(name.getText());
        if (!"seq".equalsIgnoreCase(parts.getLast())) {
            parts.add("seq");
        }

        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                join(parts),
                name.isQuoted()
        );
    }

    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final List<String> parts = splitAndReplace(name.getText());
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                join(parts),
                name.isQuoted()
        );
    }
}
