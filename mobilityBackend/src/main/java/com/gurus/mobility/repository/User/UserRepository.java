package com.gurus.mobility.repository.User;

import com.gurus.mobility.entity.user.Role;
import com.gurus.mobility.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface    UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);
    Boolean existsByIdentifiant(String identifiant);
    Boolean existsByUserName(String userName);

    User findByIdentifiant(String identifiant);

    User findByEmail(String email);

    User getUserByIdentifiant(String identifiant);

    User findByVerificationCode(String verificationCode);

    @Query("SELECT u FROM User u WHERE u.roles= ?1")
    List<User> findUsersByRole(Set<Role> roles);

    Optional<User> findByUserName(String username);

    User findByToken(String token);
    /***
     * Developed by sidaoui mohamed amine
     * */
    /*@Query("select count(a) from Accomodation a ")
    public int nbReservation();
    @Query("select u from User u where u.roles='ROLE_PROPRIETAIRE_LOGEMENT' ")
    public List<User> getAllOwners();
*/
    @Query("select u from User u,Role r where  u.id=:id and r.name='OWNER' and r.id=u.id")
    public User findUserByIdAndRole(@Param("id") Long id);


}
