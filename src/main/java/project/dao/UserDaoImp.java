package project.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public List<User> getAllUsers() {
      return entityManager.createQuery("SELECT u FROM User u").getResultList();
   }
  @Transactional
   @Override
   public void createUser(User user) {
      entityManager.persist(user);
   }

   @Transactional
   @Override
   public void updateUser(User user) {
      entityManager.merge(user);
   }

   @Override
   @Transactional
   public User readUser(long id) {
      return entityManager.find(User.class, id);
   }

   @Transactional
   @Override
   public User deleteUser(long id) throws NullPointerException {
      User user = readUser(id);
      if (null == user) {
         throw new NullPointerException("User not found");
      }
      entityManager.remove(user);
      return user;
   }
}