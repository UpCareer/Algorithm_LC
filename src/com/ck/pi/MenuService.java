package com.ck.pi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MenuService {
    private LinkedHashMap<Integer, MenuItem> map;

    public MenuService() {
        map = new LinkedHashMap<>();
    }

    public LinkedHashMap <Integer, MenuItem> populate (MenuStream stream) {
        List<String> tmp = new ArrayList<>();
        String str;
        while((str = stream.nextLine()) != null) {
            if (str.length() == 0 && !tmp.isEmpty()) {
                MenuItem menu = generateMenu(tmp);
                if(menu instanceof Category || menu instanceof Option || menu instanceof Entree)
                    this.map.put(menu.getId(), menu);
                tmp.clear();
            } else {
                tmp.add(str);
            }
        }
        return this.map;
    }

    private void GotoNextItem(MenuStream stream) {
        String str;
        while((str = stream.nextLine()) != null ) {
            if(str.length() == 0)
                return;
        }
    }

    private MenuItem generateMenu(List<String> input) {
        int id = Integer.parseInt(input.get(0));
        String type = input.get(1);
        String name = input.get(2);
        if(type.equals("ENTREE")) {
            float price = Float.parseFloat(input.get(3));
            Entree entree = new Entree(id, name, price);
            for(int index = 4; index < input.size(); index++) {
                entree.addRelatedID(Integer.parseInt(input.get(index)));
            }
            return entree;
        } else if(type.equals("CATEGORY")) {
            Category category = new Category(id, name);
            for(int index = 3; index < input.size(); index++) {
                category.addRelatedID(Integer.parseInt(input.get(index)));
            }
            return category;
        } else if(type.equals("OPTION")) {
            float price = Float.parseFloat(input.get(3));
            return new Option(id, name, price);
        } else {
            return new MenuItem(id, name);
        }
     }
}
