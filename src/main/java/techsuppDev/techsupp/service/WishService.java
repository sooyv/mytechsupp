package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.repository.WishListRepository;

@Service
@RequiredArgsConstructor
public class WishService {
    public final WishListRepository wishListRepository;


    public void wishPost(String userId, Long productId){
        wishListRepository.wishInsert(userId, productId);

    }

    public void wishDelete(String userId, Long productId) {
        wishListRepository.wishDelete(userId, productId);
    }
}
