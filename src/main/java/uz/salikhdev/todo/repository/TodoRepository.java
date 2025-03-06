package uz.salikhdev.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.salikhdev.todo.entitiy.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(t.id) < u.todo_limit THEN TRUE ELSE FALSE END " +
            "FROM my_user u LEFT JOIN todo t ON u.id = t.user_id " +
            "WHERE u.id = :userId", nativeQuery = true)
    boolean canCreateTodo(@Param("userId") Long userId);

    List<Todo> findAllByUserId(Long userId);

}