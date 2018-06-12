package com.mv.schelokov.car_rent.model.db.repository.criteria.make;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Maxim Chshelokov <schelokov.mv@gmail.com>
 */
public class SelectAllMake implements MakeReadCriteria {
    
    private static final String QUERY = "SELECT make_id,name FROM makes";

    @Override
    public String toSqlQuery() {
        return QUERY;
    }

    @Override
    public void setStatement(PreparedStatement ps) throws SQLException {
    }
    
}
