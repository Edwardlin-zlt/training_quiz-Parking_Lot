package entities;

import Utils.ParsedInitInfoUtil;
import dao.LotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Manager {

    public void constructParkingLot(String initInfo) {
        Map<String, Integer> tagNumberMap = ParsedInitInfoUtil.parseRawInfo(initInfo);
        System.out.println("print this line");
        LotRepository lotRepository = new LotRepository();
        lotRepository.DeleteAll();
        createLots(tagNumberMap);
    }

    private static void createLots(Map<String, Integer> tagNumberMap) {
        List<Lot> lots = new ArrayList<>();
        Set<String> tags = tagNumberMap.keySet();
        for (String tag : tags) {
            Integer count = tagNumberMap.get(tag);
            for (int i = 0; i < count; i++) {
                lots.add(new Lot(tag, i + 1));
            }
        }
        LotRepository lotRepository = new LotRepository();
        lotRepository.save(lots);
    }

    public String park(String carNumber) {
        LotRepository lotRepository = new LotRepository();
        List<Lot> lots = lotRepository.queryAvailableLots();
        Lot lot = lots.get(0);
        lot.setCarNumber(carNumber);
        lotRepository.update(lot);
        return String.format("%s,%d,%s", lot.getParkingLotTag(), lot.getParkingLotNumber(), lot.getCarNumber());
    }

    public String fetch(String ticket) {
        LotRepository lotRepository = new LotRepository();
        Lot lot = lotRepository.queryByTicket(ticket);
        String carNumber = lot.getCarNumber();
        lot.setCarNumber(null);
        lotRepository.update(lot);
        return carNumber;
    }

}
