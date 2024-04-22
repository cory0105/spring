package dw.gameshop.repository;

import dw.gameshop.model.Game;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    // Repository에서 jpql 사용법 : @query 어노테이션 사용
    @Query("select g1 from Game g1 where g1.price = (select max(g2.price) from Game g2)")
    Game getGamewithMaxPrice();
    @Query("select g1 from Game g1 order by g1.price desc")
    public List<Game> getGameWithMaxTop3();
}
