package uz.salikhdev.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.todo.entitiy.Premium;

@Repository
public interface PremiumRepository extends JpaRepository<Premium, Long> {
}