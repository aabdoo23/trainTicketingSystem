import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class globals {
    public static LinkedList<User>userLinkedList=new LinkedList<>();
    public static LinkedList<Train>trainLinkedList=new LinkedList<>();
    public static LinkedList<Station>stationLinkedList=new LinkedList<>();
    public static LinkedList<Reservation>reservationsLinkedList=new LinkedList<>();
    public static boolean[] usersIDs = new boolean[2000];
    public static boolean[] trainsIDs = new boolean[2000];
    public static boolean[] reservationsIDs = new boolean[2000];
    public static boolean[] stationsIDs = new boolean[2000];
    public static boolean[] ticketsIDs = new boolean[2000];
    public static int createNewID(boolean[] v) {
        Random random = new Random();
        int x = random.nextInt(v.length-1);
        while (v[x]) {
            x = random.nextInt(v.length-1);
        }
        v[x] = true;
        return x;
    }
    public static void makeList(LinkedList linkedList, JList<String> list) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        list.setListData(strings);
    }
    public static String[] makeList(LinkedList linkedList) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        return strings;
    }

    public static void makeList(String[] strings, JList<String> list) {
        list.setListData(strings);
    }
    public static void makeList(LinkedList linkedList, JComboBox<String> list) {

        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        list.setModel(new DefaultComboBoxModel<>(strings));
    }
    public static void defaultMakeList(LinkedList linkedList, JComboBox<String> list) {

        String[] strings = new String[linkedList.size()+1];
        strings[0]="-";
        int i = 1;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        list.setModel(new DefaultComboBoxModel<>(strings));
    }


}
