import exception.InvalidInitInfoException;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;

import java.util.Scanner;

public class Application {
    private static Manager manager = new Manager();

    public static void main(String[] args) {
        operateParking();
    }

    public static void operateParking() {
        while (true) {
            System.out.println("1. 初始化停车场数据\n2. 停车\n3. 取车\n4. 退出\n请输入你的选择(1~4)：");
            Scanner printItem = new Scanner(System.in);
            String choice = printItem.next();
            if (choice.equals("4")) {
                System.out.println("系统已退出");
                break;
            }
            handle(choice);
        }
    }

    private static void handle(String choice) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case "1":
                System.out.println("请输入初始化数据\n格式为\"停车场编号1：车位数,停车场编号2：车位数\" 如 \"A:8,B:9\"：");
                String initInfo = scanner.next();
                init(initInfo);
                break;
            case "2": {
                System.out.println("请输入车牌号\n格式为\"车牌号\" 如: \"A12098\"：");
                String carInfo = scanner.next();
                String ticket = park(carInfo);
                if (ticket != null) {
                    String[] ticketDetails = ticket.split(",");
                    System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：\"%s\"，请您妥善保存。\n",
                        ticketDetails[2], ticketDetails[0], ticketDetails[1], ticket);
                }
                break;
            }
            case "3": {
                System.out.println("请输入停车券信息\n格式为\"停车场编号,车位编号,车牌号\" 如 \"A,1,8\"：");
                String ticket = scanner.next();
                String car = fetch(ticket);
                if (car != null) {
                    System.out.format("已为您取到车牌号为%s的车辆，很高兴为您服务，祝您生活愉快!\n", car);
                }
                break;
            }
        }
    }

    public static void init(String initInfo) {
        try {
            manager.constructParkingLot(initInfo);
        } catch (InvalidInitInfoException e) {
            System.out.println("初始化信息格式错误，请重新输入");
        }
    }

    public static String park(String carNumber) {
        try {
            return manager.park(carNumber);
        } catch (ParkingLotFullException e) {
            System.out.println("非常抱歉，由于车位已满，暂时无法为您停车！");
            throw e;
        }
    }

    public static String fetch(String ticket) {
        try {
            return manager.fetch(ticket);
        } catch (InvalidTicketException e) {
            System.out.println("很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！");
            throw e;
        }
    }

}
