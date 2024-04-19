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

import java.util.List;
import java.util.Optional;

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
}
