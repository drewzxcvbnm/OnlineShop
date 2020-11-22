package com.online.shop.application;

import com.online.shop.application.entities.*;
import com.online.shop.application.repositories.CategoryRepo;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@SuppressWarnings("checkstyle:linelength")
public class Runner implements CommandLineRunner {

    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepo categoryRepo;

    @Override
    public void run(String... args) throws Exception {
        if (!productRepo.findAll().isEmpty()) {
            return;
        }
        Category portableElectronics = new Category("pe.png", "Portable electronics");
        Category computers = new Category("c.png", "Computers");
        Category videoGames = new Category("vg.png", "Video games");
        Category automobile = new Category("a.png", "Automobile");
        Category furniture = new Category("f.png", "Furniture");
        Category householdAppliances = new Category("ha.png", "Household appliances");
        Category clothes = new Category("cl.png", "Clothes");
        Category cosmetics = new Category("cm.png", "Cosmetics");
        List<Category> categoryList = Arrays.asList(portableElectronics, computers, videoGames, automobile, furniture, householdAppliances, clothes, cosmetics);
        categoryRepo.saveAll(categoryList);
        User normalUser = User.builder().authority(Authority.USER).username("typicalUser").password(hash("strongPassword")).build();
        userRepo.save(normalUser);
        User user = User.builder().authority(Authority.ADMIN).username("chadAdmin").password(hash("abc123")).profilePicture(getAdminPic()).build();
        userRepo.save(user);
        Product samsungGalaxyS10 = Product.builder().category(portableElectronics)
                .name("Samsung Galaxy S10")
                .description("Beautiful all-screen design and top-of-the-line processor. Samsung packed in tons of fun features: An ultrasonic fingerprint sensor! Wireless charging with power sharing! A headphone jack! Samsung UI has finally improved. Security updates are planned.")
                .price(new BigDecimal("899.99"))
                .properties(Arrays.asList("Weight: 157g.", "Dimensions: 149.9 x 70.4 x 7.8mm.", "OS: Android 9.", "CPU: Octa-core chipset.", "RAM: 8GB.", "Storage: 128/512GB."))
                .build();
        samsungGalaxyS10.getReviews().add(createReview(8, normalUser, samsungGalaxyS10, "The Galaxy S10 is a fitting 10th anniversary phone for Samsung and its storied S series. It delivers on change with a novel-looking Infinity-O screen so large it displaces the front camera, and a triple-lens rear camera that takes ultra-wide photos. Its in-screen fingerprint sensor tech should serve you well, while its Wireless PowerShare could serve your friends well. That’s a lot of change – just know that it comes at a high price and the Galaxy S10e and S10 Plus flank it from both sides of the coin as better options."));
        samsungGalaxyS10.getReviews().add(createReview(7, user, samsungGalaxyS10, "The Samsung Galaxy S10 is an all-round great phone that thankfully keeps things at a manageable size. It still has a big, beautiful screen that’s great for watching videos, but it doesn’t make your hand hurt holding it.\n Performance is good, the camera’s good and the software is the start of a rethink of how we use big phones. The ultrasonic fingerprint scanner works well enough too and there’s even a headphone socket."));
        productRepo.save(samsungGalaxyS10);
        productRepo.save(Product.builder().category(portableElectronics)
                .name("iPhone 11")
                .description("The iPhone 11 succeeds the iPhone XR, and it features a 6.1-inch LCD display that Apple calls a \"Liquid Retina HD Display.\" It features a 1792 x 828 resolution at 326ppi, a 1400:1 contrast ratio, 625 nits max brightness, True Tone support for adjusting the white balance to the ambient lighting, and wide color support for true-to-life colors.")
                .price(new BigDecimal("1299.99"))
                .properties(Arrays.asList("Display: Liquid Retina HD display", "Camera: Dual 12MP Ultra Wide and Wide cameras", "OS: iOS 13, upgradable to iOS 13.3", "CPU: Hexa-core (2x2.65 GHz Lightning + 4x1.8 GHz Thunder)"))
                .build());
        productRepo.save(Product.builder().category(computers)
                .name("Lenovo V130 15")
                .description("The V130 15.6\" laptop delivers great performance in a patterned, textured cover that bespeaks modern style. A simple, clean design features a large, one-piece touchpad and hinges that open 180 degrees—perfect for collaborating. Powerful Intel® technology keeps you working productively, while enhanced security protects your critical data.")
                .price(new BigDecimal("239.99"))
                .properties(Arrays.asList("Processor: Up to 7th gen Intel® Core™ i5-7200U", "Operating System: Up to Windows 10 Pro", "Display: Up to 15.6 TN FHD antiglare (1920 x 1080)"))
                .build());
        productRepo.save(Product.builder().category(computers)
                .name("Apple MacBook Air 13")
                .description("Available in silver, space gray, and gold, the new thinner and lighter MacBook Air features a brilliant Retina display with True Tone technology, Touch ID, the latest-generation keyboard, and a Force Touch trackpad. The iconic wedge is created from 100 percent recycled aluminum, making it the greenest Mac ever.1 And with all-day battery life, MacBook Air is your perfectly portable, do-it-all notebook")
                .price(new BigDecimal("1299.99"))
                .properties(Arrays.asList("Display: Retina display with True Tone", "Processor: 1.6GHz dual-core 8th-generation Intel Core i5 processor with Turbo Boost up to 3.6GHz", "Storage: 128GB SSD storage", "Graphics: Intel UHD Graphics 617"))
                .build());
        productRepo.save(Product.builder().category(computers)
                .name("ASUS TUF Gaming FX505")
                .description("ASUS TUF Gaming FX505 will change the way you look at gaming laptops. It exceeds expectations, boasting impressive hardware and a compact, aggressively-designed chassis that's exceptionally tough. FX505 has a gaming desktop-inspired keyboard with RGB-backlit keys, a highlighted WASD key group, and Overstroke technology for fast and precise control. With a cutting-edge IPS-level NanoEdge display, and a MIL-STD-810G test certified durability, FX505 delivers an immersive gaming experience without breaking the bank!")
                .price(new BigDecimal("890.00"))
                .properties(Arrays.asList("Processor: AMD Ryzen 7 3750U, 2.3GHz", "Graphics: NVIDIA GeForce GTX 1650", "Operating System: Windows 10 Home"))
                .build());
        Product razerLaptop = Product.builder().category(computers)
                .name("Razer Blade Pro 17")
                .description("Pushing the limits to what a gaming laptop can do. The Razer Blade Pro 17 is built with a powerful processor to run performance-demanding AAA games on the go. We’ve made it nearly 25% smaller than its predecessor, with a faster 17.3” display that’s fitted into a body as sleek and compact as a 15” laptop.")
                .price(new BigDecimal("1790.00"))
                .properties(Arrays.asList("Processor: Intel Core i7-10875H", "Graphics: GeForce RTX 2080 Super Max-Q", "Operating System: Windows 10 Pro"))
                .build();
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "I saw one of these in Barbados and I bought one."));
        razerLaptop.getReviews().add(createReview(5, normalUser, razerLaptop, "talk about shame."));
        razerLaptop.getReviews().add(createReview(5, normalUser, razerLaptop, "The box this comes in is 3 yard by 6 yard and weights 19 pound!!!"));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "one of my hobbies is piano. and when i'm playing piano this works great."));
        razerLaptop.getReviews().add(createReview(9, normalUser, razerLaptop, "I tried to manhandle it but got bun all over it."));
        razerLaptop.getReviews().add(createReview(0, normalUser, razerLaptop, "talk about sadness!"));
        razerLaptop.getReviews().add(createReview(9, normalUser, razerLaptop, "The box this comes in is 4 meter by 5 foot and weights 18 kilogram."));
        razerLaptop.getReviews().add(createReview(4, normalUser, razerLaptop, "My bass loves to play with it."));
        razerLaptop.getReviews().add(createReview(3, normalUser, razerLaptop, "i use it profusely when i'm in my garage."));
        razerLaptop.getReviews().add(createReview(5, normalUser, razerLaptop, "heard about this on chicha radio, decided to give it a try."));
        razerLaptop.getReviews().add(createReview(9, normalUser, razerLaptop, "one of my hobbies is hiking. and when i'm hiking this works great."));
        razerLaptop.getReviews().add(createReview(2, normalUser, razerLaptop, "I tried to belly-flop it but got Turkish Delight all over it."));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "this Razer laptop is papery."));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "My neighbor Elisha has one of these. She works as a fortune teller and she says it looks floppy."));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "My neighbor Karly has one of these. She works as a gambler and she says it looks tall."));
        razerLaptop.getReviews().add(createReview(9, normalUser, razerLaptop, "The box this comes in is 4 yard by 5 kilometer and weights 11 pound!"));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "My baboon loves to play with it."));
        razerLaptop.getReviews().add(createReview(3, normalUser, razerLaptop, "I saw one of these in Grenada and I bought one."));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "one of my hobbies is baking. and when i'm baking this works great."));
        razerLaptop.getReviews().add(createReview(5, normalUser, razerLaptop, "The box this comes in is 3 meter by 6 yard and weights 12 pound."));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "I saw one of these in The Gambia and I bought one."));
        razerLaptop.getReviews().add(createReview(10, normalUser, razerLaptop, "heard about this on bouyon radio, decided to give it a try."));
        razerLaptop.getReviews().add(createReview(5, normalUser, razerLaptop, "This Razer laptop works certainly well. It accidentally improves my baseball by a lot."));
        razerLaptop.getReviews().add(createReview(5, normalUser, razerLaptop, "The box this comes in is 4 light-year by 5 inch and weights 11 megaton!!"));
        razerLaptop.getReviews().add(createReview(7, normalUser, razerLaptop, "I saw one of these in Vanuatu and I bought one."));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "talk about lust!!"));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "talk about shame."));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "this Razer laptop is papery."));
        razerLaptop.getReviews().add(createReview(9, normalUser, razerLaptop, "talk about sadness."));
        razerLaptop.getReviews().add(createReview(3, normalUser, razerLaptop, "My co-worker Houston has one of these. He says it looks invisible."));
        razerLaptop.getReviews().add(createReview(4, normalUser, razerLaptop, "i use it never again when i'm in my station."));
        razerLaptop.getReviews().add(createReview(9, normalUser, razerLaptop, "It only works when I'm South Korea."));
        razerLaptop.getReviews().add(createReview(7, normalUser, razerLaptop, "My co-worker Luka has one of these. He says it looks purple."));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "heard about this on balearic beat radio, decided to give it a try."));
        razerLaptop.getReviews().add(createReview(10, normalUser, razerLaptop, "one of my hobbies is gaming. and when i'm gaming this works great."));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "i use it every Tuesday when i'm in my pub."));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "It only works when I'm New Caledonia."));
        razerLaptop.getReviews().add(createReview(10, normalUser, razerLaptop, "My co-worker Merwin has one of these. He says it looks bubbly."));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "My neighbor Alida has one of these. She works as a gambler and she says it looks spotless."));
        razerLaptop.getReviews().add(createReview(4, normalUser, razerLaptop, "i use it profusely when i'm in my garage."));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "This Razer laptop works certainly well. It energetically improves my golf by a lot."));
        razerLaptop.getReviews().add(createReview(7, normalUser, razerLaptop, "this Razer laptop is dominant."));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "i use it once a week when i'm in my firetruck."));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "The box this comes in is 4 mile by 5 yard and weights 18 pound!!"));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "talk about contentment!!!"));
        razerLaptop.getReviews().add(createReview(9, normalUser, razerLaptop, "My neighbor Krista has one of these. She works as a salesman and she says it looks soapy."));
        razerLaptop.getReviews().add(createReview(8, normalUser, razerLaptop, "this Razer laptop is gracious."));
        razerLaptop.getReviews().add(createReview(10, normalUser, razerLaptop, "I tried to vomit it but got bonbon all over it."));
        razerLaptop.getReviews().add(createReview(6, normalUser, razerLaptop, "talk about shame."));
        razerLaptop.getReviews().add(createReview(7, normalUser, razerLaptop, "one of my hobbies is web-browsing. and when i'm browsing the web this works great."));
        productRepo.save(razerLaptop);
        productRepo.save(Product.builder().category(portableElectronics)
                .name("Xiaomi Mi 10T Pro")
                .description(" The Xiaomi Mi 10T Pro is a great phone for its price, with a main camera that takes good pictures, a powerful processor and a fairly long-lasting battery. It’s not perfect - we had issues with its size, screen quality and the way the back picked up fingerprints too much, but some might not mind these shortcomings at all. In general it’s a pretty good handset, but it’s not for everyone. ")
                .price(new BigDecimal("540.00"))
                .properties(Arrays.asList("Dimensions: 165.1 x 76.4 x 9.3 mm (6.5 x 3.01 x 0.37 in)", "Weight: 218 g (7.69 oz)", "Operating System: Android 10, MIUI 12", "Chipset: Qualcomm SM8250 Snapdragon 865 (7 nm+)", "Processor: Octa-core (1x2.84 GHz Kryo 585 & 3x2.42 GHz Kryo 585 & 4x1.80 GHz Kryo 585)", "Graphics: Adreno 650"))
                .build());
        Product genericProduct = Product.builder().category(computers)
                .name("Generic Product Name")
                .description("Best product description")
                .price(new BigDecimal("123"))
                .properties(Arrays.asList("Good Price and you know it", "Excellent quality"))
                .build();
        for (Category value : categoryList) {
            genericProduct.setId(null);
            genericProduct.setCategory(value);
            productRepo.save(genericProduct);
        }
    }

    @SneakyThrows
    private byte[] getAdminPic() {
        byte[] bytes = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("static/images/admin_profile.png").readAllBytes();
        Byte[] byteObjects = new Byte[bytes.length];
        int i = 0;
        for (byte b : bytes) {
            byteObjects[i++] = b;  // Autoboxing.
        }
        return bytes;
    }

    private ProductReview createReview(int rating, User user, Product product, String content) {
        ProductReview review = new ProductReview();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);
        review.setContent(content);
        return review;
    }

    private String hash(String pass) {
        return passwordEncoder.encode(pass);
    }

}
