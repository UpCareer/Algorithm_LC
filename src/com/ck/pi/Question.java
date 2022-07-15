package com.ck.pi;

/**
 * You have been tasked with parsing menus from a large restaurant group. Each menu is streamed to clients via a
 * provided interface. You must design object(s) that represents a menu and can be instantiated with data from the
 * provided interface. Your design should contain an appropriate class structure to contain the parsed data,
 * as well as a function or set of functions to perform the parsing.
 * Consumers will use your object(s) to access a complete representation of the data sent by the menu stream
 * after it has finished loading. Your objects should provide easy access to the full representation of the menu.
 * It should be possible to reconstruct the menu stream from your object.
 * The menu stream represents a list of menu items. Each item in the stream is a menu item, and
 * each item will be separated by an empty string. The attributes of each item are as follows:
 * Line 0: The ID of the item
 * Line 1: The item type, either CATEGORY, ENTREE or OPTION
 * Line 2: The name of the item
 * Line 3: The price of the item for ENTREE and OPTION. Not present for CATEGORY items.
 * Any other line: A list of item IDs that are linked to the current item. OPTIONs do not have any linked items.
 * Stream Example:
 *
 * 4           // ID
 * ENTREE      // type
 * Spaghetti   // name
 * 10.95       // price
 * 2           // related IDs
 * 3           // related IDs
 *
 * 1           // ID
 * CATEGORY    // type
 * Pasta       // name
 * 4           // related IDs
 * 5           // related IDs
 *
 * 2           // ID
 * OPTION      // type
 * Meatballs   // name
 * 1.00        // price
 *
 * 3
 * OPTION
 * Chicken
 * 2.00
 *
 * 5
 * ENTREE
 * Lasagna
 * 12.00
 *
 * 6
 * ENTREE
 * Caesar Salad
 * 9.75
 * 3
 *
 * Stream Interface Example:
 * interface MenuStream {
 *  String nextLine(); // Returns null when stream is empty
 * }
 */

public class Question {
}
