import dao.LotRepository;
import entities.Lot;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        LotRepository lotRepository = new LotRepository();
        lotRepository.DeleteAll();
        Lot lot1 = new Lot(0, "A", 3, "A12345");
        Lot lot2 = new Lot(0, "C", 8, null);
        lotRepository.save(Arrays.asList(lot1, lot2));

        List<Lot> lots = lotRepository.queryAll();
        lots.forEach(System.out::println);

        Lot lot = lotRepository.queryById(2);
        System.out.println(lot);

        lot.setCarNumber("da234");
        lotRepository.update(lot);

        lot = lotRepository.queryById(2);
        System.out.println(lot);

    }
}
