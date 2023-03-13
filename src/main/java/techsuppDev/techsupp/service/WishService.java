package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.repository.WishListRepository;

@Service
@RequiredArgsConstructor
public class WishService {
    public final WishListRepository wishListRepository;


    public String wishPost(String userId, Long productId){
        return wishListRepository.wishInsert(userId, productId);

    }

    public String wishDelete(String userId, Long productId) {
        return wishListRepository.wishDelete(userId, productId);
    }
}
