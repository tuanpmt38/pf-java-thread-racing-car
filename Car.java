package thread;

import java.util.Random;
import static thread.Main.DISTANCE;
import static thread.Main.STEP;
public class Car implements Runnable {
    private String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // khởi tạo điểm start
        int runDistance = 0;
        // khởi tạo time bắt đầu
        long startTime = System.currentTimeMillis();
        // kiểm tra chừng nào còn xe chưa kết thúc quãng đường đua thì xe vẫn tiếp tục chạy
        while (runDistance < DISTANCE) {
            try {
                // khởi tạo vận tốc ngẫu nhiên
                int speed = (new Random()).nextInt(20);
                // tính khoảng cách đã đi đc
                runDistance += speed;
                // Build result graphic
                String log = "|";
                int percentTravel = (runDistance * 100) / DISTANCE;
                for (int i = 0; i < DISTANCE; i += STEP) {
                    if (percentTravel >= i + STEP) {
                        log += "=";
                    } else if (percentTravel >= i && percentTravel < i + STEP) {
                        log += "o";
                    } else {
                        log += "-";
                    }
                }
                log += "|";
                System.out.println("thread.Car" + this.name + ": " + log + " " + Math.min(DISTANCE, runDistance) + "KM");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("thread.Car" + this.name + " broken...");
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("thread.Car" + this.name + " Finish in " + (endTime - startTime) / 1000 + "s");
    }
}
