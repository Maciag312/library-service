package pwr.awt.demo.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import pwr.awt.demo.domain.user.User;
import pwr.awt.demo.domain.user.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JpaUserRepositoryImpl implements UserRepository {
    private final JpaUserRepo jpaUserRepo;

    @Override
    public User save(User user) {
        return jpaUserRepo.save(UserTuple.from(user)).toDomain();
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserTuple> optionalUserTuple = jpaUserRepo.findById(id);
        return optionalUserTuple.map(UserTuple::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserTuple> optionalUserTuple = jpaUserRepo.findByEmail(email);
        return optionalUserTuple.map(UserTuple::toDomain);
    }

    @Override
    public List<User> findAll() {
        List<UserTuple> userTupleList = jpaUserRepo.findAll();
        if(userTupleList.isEmpty())
            return new ArrayList<>();
        return userTupleList.stream().map(UserTuple::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteByEmail(String email) {
        jpaUserRepo.deleteByEmail(email);
    }

    public interface JpaUserRepo extends JpaRepository<UserTuple, Long>{
        Optional<UserTuple> findByEmail(String username);
        @Transactional
        void deleteByEmail(String username);
    }
}
