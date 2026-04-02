package BookDonation.demo.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import BookDonation.demo.Domain.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin , String> {

}