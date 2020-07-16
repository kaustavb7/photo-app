package cotn.photoapp.users.repository;

import cotn.photoapp.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kaustavbasu
 * @Date 7/1/20
 * @Time 7:24 PM
 */
@Repository
public interface UsersRepository  extends JpaRepository<Users,Long> {

    public Users findByEmail(String email);
}
