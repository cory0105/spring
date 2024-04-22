package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.repository.PurchaseRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRepository gameRepository;

    public Purchase savePurchase(Purchase purchase){
        // 구매확정 바로 직전, 현재시간을 저장함
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }
    public List<Purchase> getAllPurchases(){
        return purchaseRepository.findAll();
    }
    public List<Purchase> getPurchaseListByUser(String userId){
        // 유저 아이디로 유저 객체 찾기
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isEmpty()){
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        return purchaseRepository.findByUser(userOptional.get());
    }

    // 유저 이름으로 구매한 게임 찾기
    public List<Purchase> getPurchaseListByUserName(String userName){
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()){
            throw new ResourceNotFoundException("User", "Name", userName);
        }
        return purchaseRepository.findByUser(userOptional.get());
    }
    public List<Game> getGameListByUserName(String userName){
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()){
            throw new ResourceNotFoundException("User", "Name", userName);
        }
        List<Purchase> purchases = purchaseRepository.findByUser(userOptional.get());
        List<Game> games = new ArrayList<>();
        for (int i=0; i<purchases.size(); i++){
            games.add(purchases.get(i).getGame());
        }
        return games;
    }
}
