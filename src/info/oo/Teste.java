package info.oo;


import java.util.ArrayList;
import java.util.List;

public class Teste{
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(12);
        list.add(13);

        list.addAll(new ArrayList<>());

        System.out.println(list);
    }


}