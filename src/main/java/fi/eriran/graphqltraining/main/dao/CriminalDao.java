package fi.eriran.graphqltraining.main.dao;

import fi.eriran.graphqltraining.main.pojo.Criminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CriminalDao {

    @Autowired
    private DataSource dataSource;

    public Criminal getCriminal(Integer id) throws SQLException {
        try (PreparedStatement statement = dataSource.getConnection()
                .prepareStatement("select name, description from criminal.criminal where id = ?")) {
            statement.setInt(1, id);
            statement.execute();
            ResultSet results = statement.getResultSet();
            if (!results.next()) {
                return new Criminal();
            }
            Criminal criminal = new Criminal();
            criminal.setId(id);
            criminal.setName(results.getString(1));
            criminal.setAppearance(results.getString(2));
            return criminal;
        }
    }
}
