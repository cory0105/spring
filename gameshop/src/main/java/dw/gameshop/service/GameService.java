package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    GameRepository gameRepository;
    UserRepository userRepository;
    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Game getGameById(long id){
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()){
            return game.get();
        } else {
            throw new ResourceNotFoundException("Exception", "id", id);
        }
    }
    public Game updateGameById(long id, Game game){
        Optional<Game> game1 = gameRepository.findById(id);
        if (game1.isPresent()){
            if (game.getGenre()!=null){
                game1.get().setGenre(game.getGenre());
            }
            if (game.getImage()!=null){
                game1.get().setImage(game.getImage());
            }
            if (game.getText()!=null){
                game1.get().setText(game.getText());
            }

            if (game.getPrice()!=0){
                game1.get().setPrice(game.getPrice());
            }
            if (game.getTitle()!=null){
                game1.get().setTitle(game.getTitle());
            }
            gameRepository.save(game1.get());
            return game1.get();
        }else {
            throw new ResourceNotFoundException("Exception", "id", id);
        }
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    // 제일 비싼 게임의 정보
    public Game getGameWithMaxPrice(){
        List<Game> games = gameRepository.findAll();
        // 람다식이 아닌 일반 자바코드
//        if (games.size() <= 0){
//            throw new ResourceNotFoundException("Max Price", " ", " ")
//        }
//        Game max = games.get(0);
//        for (int i=0; i<games.size()-1; i++){
//            if (max.getPrice() < games.get(i+1).getPrice()){
//                max = games.get(i+1);
//            }
//        }
//        return max;
        // 람다식
//        return games.stream()
//                .sorted(Comparator.comparingInt(Game::getPrice).reversed())
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Max Price", " ", " "));
        // JPQL 사용
        return gameRepository.getGamewithMaxPrice();
    }

    // 제일 비싼 게임 top3
    public List<Game> getGameWithMaxPriceTop3(){
        List<Game> games = gameRepository.findAll();
//        games.sort(Comparator.comparingInt((Game g) -> g.getPrice()).reversed());
//        List<Game> newGames = new ArrayList<>();
//        newGames.add(games.get(0));
//        newGames.add(games.get(1));
//        newGames.add(games.get(2));
//        return newGames;
//        return games.stream()
//                .sorted(Comparator.comparingInt(Game::getPrice).reversed())
//                .limit(3)
//                .collect(Collectors.toList());
        return gameRepository.getGameWithMaxTop3().stream().limit(3).collect(Collectors.toList());
    }

    public List<Game> getGameMaxPriceTop3(){
        List<Game> games = gameRepository.findAll();
        int lastPrice = 0;
        List<Game> result = new ArrayList<>();
        for (int i=0; i<3; i++){
            Game game = new Game();
            for (int j=0; j<games.size(); j++){
                if (game.getPrice() < games.get(j).getPrice() && (games.get(j).getPrice() < lastPrice || i==0)){
                    game = games.get(j);
                }
            }
            lastPrice = game.getPrice();
            result.add(game);
        }
        return result;
    }
}
