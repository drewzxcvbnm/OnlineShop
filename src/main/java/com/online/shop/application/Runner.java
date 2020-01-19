package com.online.shop.application;

import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import com.online.shop.application.entities.Role;
import com.online.shop.application.entities.User;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {
        productRepo.save(Product.builder().category(Category.COMPUTERS)
                .name("Lenovo V130 15")
                .description("The V130 15.6\" laptop delivers great performance in a patterned, textured cover that bespeaks modern style. A simple, clean design features a large, one-piece touchpad and hinges that open 180 degrees—perfect for collaborating. Powerful Intel® technology keeps you working productively, while enhanced security protects your critical data.")
                .price(new BigDecimal("239.99"))
                .properties(Arrays.asList("Processor: Up to 7th gen Intel® Core™ i5-7200U", "Operating System: Up to Windows 10 Pro", "Display: Up to 15.6 TN FHD antiglare (1920 x 1080)"))
                .build());
        productRepo.save(Product.builder().category(Category.COMPUTERS)
                .name("Apple MacBook Air 13")
                .description("Available in silver, space gray, and gold, the new thinner and lighter MacBook Air features a brilliant Retina display with True Tone technology, Touch ID, the latest-generation keyboard, and a Force Touch trackpad. The iconic wedge is created from 100 percent recycled aluminum, making it the greenest Mac ever.1 And with all-day battery life, MacBook Air is your perfectly portable, do-it-all notebook")
                .price(new BigDecimal("1299.99"))
                .properties(Arrays.asList("Display: Retina display with True Tone", "Processor: 1.6GHz dual-core 8th-generation Intel Core i5 processor with Turbo Boost up to 3.6GHz", "Storage: 128GB SSD storage", "Graphics: Intel UHD Graphics 617"))
                .build());
        productRepo.save(Product.builder().category(Category.COMPUTERS)
                .name("ASUS TUF Gaming FX505")
                .description("ASUS TUF Gaming FX505 will change the way you look at gaming laptops. It exceeds expectations, boasting impressive hardware and a compact, aggressively-designed chassis that's exceptionally tough. FX505 has a gaming desktop-inspired keyboard with RGB-backlit keys, a highlighted WASD key group, and Overstroke technology for fast and precise control. With a cutting-edge IPS-level NanoEdge display, and a MIL-STD-810G test certified durability, FX505 delivers an immersive gaming experience without breaking the bank!")
                .price(new BigDecimal("890.00"))
                .properties(Arrays.asList("Processor: AMD Ryzen 7 3750U, 2.3GHz", "Graphics: NVIDIA GeForce GTX 1650", "Operating System: Windows 10 Home"))
                .build());
        Product genericProduct = Product.builder().category(Category.COMPUTERS)
                .name("Generic Product Name")
                .description("Best product description")
                .price(new BigDecimal("123"))
                .properties(Arrays.asList("Good Price and you know it", "Excellent quality"))
                .build();
        for (Category value : Category.values()) {
            genericProduct.setId(null);
            genericProduct.setCategory(value);
            productRepo.save(genericProduct);
        }
        userRepo.save(User.builder().role(Role.USER).username("typicalUser").password(hash("strongPassword")).build());
        userRepo.save(User.builder().role(Role.ADMIN).username("chadAdmin").password(hash("abc123")).build());
    }

    private String hash(String pass) {
        return passwordEncoder.encode(pass);
    }

}
