package security.test.secuirty1.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import security.test.secuirty1.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long>
{
        Optional<UserInfo> findByName(String name);

}

