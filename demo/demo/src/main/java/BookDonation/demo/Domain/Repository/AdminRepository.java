package BookDonation.demo.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import BookDonation.demo.Domain.Model.Admin;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin , Long> {

    @Query("SELECT a FROM Admin a WHERE a.email.endereco = :emailDigitado")
    Optional<Admin> findByEmail(@Param("emailDigitado") String emailDigitado);
}