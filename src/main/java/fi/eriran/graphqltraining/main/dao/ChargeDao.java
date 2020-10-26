package fi.eriran.graphqltraining.main.dao;

import fi.eriran.graphqltraining.main.pojo.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class ChargeDao {

    @Autowired
    private DataSource dataSource;

    public Collection<Charge> getCharges(Integer criminalId) throws SQLException {
        try (PreparedStatement statement = dataSource.getConnection()
                .prepareStatement("select id, description from criminal.charge where criminal_id = ?")) {
            statement.setInt(1, criminalId);
            statement.execute();
            return mapResults(statement.getResultSet());
        }
    }

    private Collection<Charge> mapResults(ResultSet resultSet) throws SQLException {
        Collection<Charge> charges = new ArrayList<>();
        while (resultSet.next()) {
            Charge newCharge = new Charge();
            newCharge.setId(resultSet.getInt(1));
            newCharge.setDescription(resultSet.getString(2));
            charges.add(newCharge);
        }
        return charges;
    }

}
