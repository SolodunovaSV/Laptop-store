import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LaptopStore {

    static List<String> colors = new ArrayList<>(Arrays.asList("Red", "Orange", "Yellow", "Green", "Blue", "Dark_Blue", "Violet", "Black", "Silver"));
    static List<String> brands = new ArrayList<>(Arrays.asList("Apple", "MSI", "Lenovo", "Huawei", "HP"));
    static List<String> os = new ArrayList<>(Arrays.asList("Linux", "Windows", "MAC OS", "Not installed"));
    static List<Integer> rams = new ArrayList<>(Arrays.asList(4, 8, 16, 32, 64));
    static List<Integer> roms = new ArrayList<>(Arrays.asList(256, 512, 1024, 2048));

    public static void main(String[] args) {
        int cntOfLaptops = 5;
        List<Laptop> list = createRandomLaptops(cntOfLaptops);
        Map<Integer, Integer> requests = new HashMap<>();
        Scanner iScanner = new Scanner(System.in);
        int nextRequest = 0;

        printList(list);    
        
        while (nextRequest != 5) {
            System.out.println("Выберите критерий поиска ноутбука: \n" +
                                "1 - ОЗУ\n" +
                                "2 - Объем жесткого диска\n" +
                                "3 - Операционная система\n" +
                                "4 - Цвет\n" +
                                "5 - Начать поиск\n");
            nextRequest = iScanner.nextInt();
            if (nextRequest == 5 && requests.size() == 0) printList(list);
            switch (nextRequest) {
                case 1:
                    System.out.println("Напишите минимальное значение желаемого ОЗУ: \n");
                    int ram = iScanner.nextInt();
                    System.out.println();
                    requests.put(nextRequest, ram);
                    break;
                case 2:
                    System.out.println("Напишите минимальное значение желаемого объема жесткого диска: \n");
                    int rom = iScanner.nextInt();
                    System.out.println();
                    requests.put(nextRequest, rom);
                    break;
                case 3:
                    System.out.println("Выберите желаемую операционную систему: \n");
                    printItems(os);
                    int req_os = iScanner.nextInt();
                    while (req_os < 1 || req_os > os.size()) {
                        System.out.println("Введите корректный номер из списка: \n");
                        printItems(os);
                        req_os = iScanner.nextInt();
                    }
                    System.out.println();
                    requests.put(nextRequest, req_os - 1);
                    break;
                case 4:
                    System.out.println("Выберите желаемый цвет: \n");
                    printItems(colors);
                    int req_color = iScanner.nextInt();
                    while (req_color < 1 || req_color > colors.size()) {
                        System.out.println("Введите корректный номер из списка: \n");
                        printItems(colors);
                        req_color = iScanner.nextInt();
                    }
                    System.out.println();
                    requests.put(nextRequest, req_color - 1);
                    break;
                case 5:
                    List<Laptop> result = new ArrayList<>();
                    
                    for (Laptop laptop : list) {
                        boolean flag = true;
                        if (laptop.RAM < requests.getOrDefault(1, 0) || 
                            laptop.ROM < requests.getOrDefault(2, 0)) {
                                flag = false;
                        }
                        if (requests.containsKey(3) && !os.get(requests.get(3)).equals(laptop.installedOS)) {
                            flag = false;
                        }
                        if (requests.containsKey(4) && !colors.get(requests.get(4)).equals(laptop.color)) {
                            flag = false;
                        }
                        if (flag) result.add(laptop);
                    }
                    if (result.size() == 0) {
                        System.out.println("По Вашему запросу ничего не найдено");
                    } else printList(result);
                    break;
            }
        }
        iScanner.close();
    }

    static void printItems(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + " - " + list.get(i));
        }
    }

    static List<Laptop> createRandomLaptops(int cnt) {
        List<Laptop> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < cnt; i++) {
            Laptop laptop = new Laptop();
            laptop.SN = rand.nextInt(1000000);
            laptop.brand = brands.get(rand.nextInt(brands.size()));
            laptop.model = (char)(rand.nextInt(65, 91)) + String.valueOf(rand.nextInt(1000000));
            laptop.RAM = rams.get(rand.nextInt(rams.size()));
            laptop.ROM = roms.get(rand.nextInt(roms.size()));
            laptop.color = colors.get(rand.nextInt(colors.size()));
            laptop.installedOS = os.get(rand.nextInt(os.size()));
            list.add(laptop);
        }

        return list;
    }

    static void printList(List<Laptop> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Вариант №" + (i + 1));
            System.out.println(list.get(i));
        }
    }
}