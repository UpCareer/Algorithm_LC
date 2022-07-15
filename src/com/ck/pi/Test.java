package com.ck.pi;

import java.util.LinkedHashMap;

public class Test {
    public static void main(String[] args) {
        MenuStream stream = new MenuStreamImpl("src/com/ck/pi/menuFile");
        MenuService menuService = new MenuService();
        LinkedHashMap<Integer, MenuItem> map = menuService.populate(stream);
        System.out.println(map.size());
        return;
    }
}
