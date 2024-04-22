package dw.gameshop.controller;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;
    @PostMapping("/products/purchase")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.OK);
    }
    @GetMapping("/products/purchase")
    public ResponseEntity<List<Purchase>> getAllPurchases(){
        return new ResponseEntity<>(purchaseService.getAllPurchases(), HttpStatus.OK);
    }
    @GetMapping("/products/purchase/id/{userId}")
    public List<Purchase> getPurchaseListByUser(@PathVariable String userId){
        return purchaseService.getPurchaseListByUser(userId);
    }
    @GetMapping("/products/purchase/name/{userName}")
    public ResponseEntity<List<Purchase>> getPurchaseListByUserName(@PathVariable String userName){
        return new ResponseEntity<>(purchaseService.getPurchaseListByUserName(userName), HttpStatus.OK);
    }
    @GetMapping("/products/game/name/{userName}")
    public ResponseEntity<List<Game>> getGameByUsername(@PathVariable String userName){
        return new ResponseEntity<>(purchaseService.getGameListByUserName(userName), HttpStatus.OK);
    }
}
