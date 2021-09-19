
import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {
        System.out.println(solution("00:00:01,400-234-090\n00:00:01,701-080-080\n01:05:00,600-234-090"));

    }

    public static int solution(String S) {
        S = S.replaceAll("-", "");
        HashMap<Integer, Integer> phones = new HashMap<Integer, Integer>();
        String[] lines = S.split(Character.toString((char) 10));
        for (String line : lines) {
            String[] lineValues = line.split(",");
            String phone = lineValues[1];
            String duration = lineValues[0];
            String[] durationParameters = duration.split(":");
            int hour = Integer.parseInt(durationParameters[0]);
            int minute = Integer.parseInt(durationParameters[1]);
            int second = Integer.parseInt(durationParameters[2]);
            int total = (hour * 3600) + (minute * 60) + second;
            int prevValue = phones.get(Integer.parseInt(phone)) == null ? 0 : phones.get(Integer.parseInt(phone));
            phones.put(Integer.parseInt(phone), prevValue + total);
        }
        int maxKey = 0;
        for (int key : phones.keySet()) {
            if (maxKey == 0) {
                maxKey = key;
            } else if (phones.get(key).intValue() > phones.get(maxKey).intValue()) {
                maxKey = key;
            } else if (phones.get(key).intValue() == phones.get(maxKey).intValue() && key < maxKey) {

                maxKey = key;
            }
        }
        phones.remove(maxKey);
        int cost = 0;
        for (int key : phones.keySet()) {
            if (phones.get(key) < 300) {
                cost = cost + (phones.get(key) * 3);
            } else {
                int minutes = phones.get(key) / 60;
                int seconds = phones.get(key) % 60;
                if (seconds > 0) {
                    minutes++;
                }
                cost = cost + (minutes * 150);
            }
        }
        return cost;
    }
}
