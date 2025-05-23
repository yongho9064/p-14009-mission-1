package main.java;

import main.java.dto.WiseSaying;
import main.java.service.AddFileService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        Map<Integer, WiseSaying> map = new HashMap<>();
        int count = 1;

        System.out.println("== 명언 앱 ==");

        while (true) {

            System.out.print("명령) ");
            String command = br.readLine();

            if (command.equals("등록")) {
                add(map, count); count++;
            } else if (command.equals("종료")) {
                br.close(); break;
            } else if (command.equals("목록")) {
                list(map);
            } else if (command.startsWith("삭제?id=")) {
                delete(command, map);
            } else if (command.startsWith("수정?id=")) {
                update(command, map);
            } else {
                System.out.println("잘못된 명령어입니다.");
            }
        }
    }

    public static int add(Map<Integer, WiseSaying> map, int count) throws IOException {
        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.id = count;
        System.out.printf("명언 : ");
        wiseSaying.wiseSaying = br.readLine();
        System.out.printf("작가 : ");
        wiseSaying.writer = br.readLine();

        map.put(count, wiseSaying);

        System.out.println(count + "번 명언이 등록되었습니다.");

        return count;
    }

    public static void list(Map<Integer, WiseSaying> map) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------------");

        for (int i = map.size(); i > 0; i--) {
            WiseSaying wiseSaying = map.get(i);

            System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.writer, wiseSaying.wiseSaying);
        }
    }

    public static void delete(String command, Map<Integer, WiseSaying> map) {
        try {
            int id = Integer.parseInt(command.substring(6));
            if (map.containsKey(id)) {
                map.remove(id);
                System.out.println(id + "번 명언이 삭제되었습니다.");
            } else {
                System.out.println(id + "번 명언은 존재하지 않습니다.");
            }
        } catch (Exception e) {
            System.out.println("잘못된 명령어입니다.");
        }
    }

    public static void update(String command, Map<Integer, WiseSaying> map) {
        try {
            int id = Integer.parseInt(command.substring(6));
            if (map.containsKey(id)) {
                WiseSaying wiseSaying = map.get(id);
                System.out.println("명언(기존): " + wiseSaying.wiseSaying);
                System.out.printf("명언: ");
                wiseSaying.wiseSaying = br.readLine();
                System.out.println("작가(기존): " + wiseSaying.writer);
                System.out.printf("작가: ");
                wiseSaying.writer = br.readLine();
            } else {
                System.out.println(id + "번 명언은 존재하지 않습니다.");
            }
        } catch (Exception e) {
            System.out.println("잘못된 명령어 입니다.");
        }
    }
}