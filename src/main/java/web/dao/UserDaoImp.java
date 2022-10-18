package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAll() {
        String hql = "select e from User e";
        return entityManager.createQuery(hql, User.class).getResultList();
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User updateUser) {
        User userToBeUpdated = entityManager.find(User.class, id);

        userToBeUpdated.setName(updateUser.getName());
        userToBeUpdated.setAge(updateUser.getAge());
        userToBeUpdated.setEmail(updateUser.getEmail());
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
