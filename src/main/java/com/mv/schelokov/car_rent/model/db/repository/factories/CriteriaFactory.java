package com.mv.schelokov.car_rent.model.db.repository.factories;

import com.mv.schelokov.car_rent.model.db.repository.UserRepository;
import com.mv.schelokov.car_rent.model.db.repository.interfaces.Criteria;
import com.mv.schelokov.car_rent.model.entities.User;

/**
 *
 * @author Maxim Chshelokov <schelokov.mv@gmail.com>
 */
public class CriteriaFactory {
    
    public static Criteria getUserFindLoginPassword(User user) {
        return new UserRepository.FindLoginPassword(user);
    }
    
    public static Criteria getUserFindLogin(String login) {
        return new UserRepository.FindLogin(login);
    }
}
