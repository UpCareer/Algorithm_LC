package com.wayfair;
// The people who buy ads on our network don't have enough data about how ads are working for
//their business. They've asked us to find out which ads produce the most purchases on their website.

// Our client provided us with a list of user IDs of customers who bought something on a landing page
//after clicking one of their ads:

// # Each user completed 1 purchase.
// completed_purchase_user_ids = [
//   "3123122444","234111110", "8321125440", "99911063"]

// And our ops team provided us with some raw log data from our ad server showing every time a
//user clicked on one of our ads:
// ad_clicks = [
//  #"IP_Address,      Time,             Ad_Text",
//  "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
//  "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
//  "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
//  "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
//  "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
//  "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",
//]

//The client also sent over the IP addresses of all their users.

//all_user_ips = [
//  #"User_ID,IP_Address",
//  "2339985511,122.121.0.155",
//  "234111110,122.121.0.1",
//  "3123122444,92.130.6.145",
//  "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
//  "8321125440,82.1.106.8",
//  "99911063,92.130.6.144"
//]

// Write a function to parse this data, determine how many times each ad was clicked,
//then return the ad text, that ad's number of clicks, and how many of those ad clicks
//were from users who made a purchase.


// Expected output:
// Bought Clicked Ad Text
// 1 of 2  2017 Pet Mittens
// 0 of 1  The Best Hollywood Coats
// 3 of 3  Buy wool coats for your pets

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdsConversionRate {

    public static void main(String[] args) {
        String[] completed_purchase_user_ids = {"3123122444","234111110", "8321125440", "99911063"};
        String[] ad_clicks = {"122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
                "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
                "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
                "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
                "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
                "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens"};
        String[] all_user_ips = { "2339985511,122.121.0.155",
                "234111110,122.121.0.1",
                "3123122444,92.130.6.145",
                "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
                "8321125440,82.1.106.8",
                "99911063,92.130.6.144"};

        List<String> result = getAdsConversionRate(completed_purchase_user_ids, ad_clicks, all_user_ips);
        System.out.println(result);
    }


    public static List<String> getAdsConversionRate(String[] completed_purchase_user_ids,
                                                    String[] ad_clicks, String[] all_user_ips) {

        //  String[] completed_purchase_user_ids = {"3123122444","234111110", "8321125440", "99911063"};
        Set<String> purchases_user_ids_set = new HashSet<String>();
        for(String id : completed_purchase_user_ids) {
            purchases_user_ids_set.add(id);
        }

        Map<String, String> ip_userID_map = new HashMap<String, String>();
        for(String user_ip : all_user_ips) {
            //"2339985511,122.121.0.155",
            String[] user_ip_array = user_ip.split("\\,");
            ip_userID_map.put(user_ip_array[1], user_ip_array[0]);
        }

        Map<String, Integer> boughtCounts = new HashMap<>();
        Map<String, Integer> clickCounts = new HashMap<>();
        for (String click : ad_clicks) {
            //  #"IP_Address,      Time,             Ad_Text",
            //  "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
            String[] clickArray = click.split("\\,");
            // "Buy wool coats for your pets" 1
            clickCounts.put(clickArray[2].trim(), clickCounts.getOrDefault(clickArray[2].trim(), 0) + 1);
            // clickArray[0] = "122.121.0.1"
            if(purchases_user_ids_set.contains(ip_userID_map.get(clickArray[0]))) {
                boughtCounts.put(clickArray[2].trim(), boughtCounts.getOrDefault(clickArray[2].trim(), 0) + 1);
            } else {
                boughtCounts.put(clickArray[2].trim(), 0);
            }
        }

        List<String> result = new ArrayList<>();
        for(String key : clickCounts.keySet()) {
            // 3 of 3  Buy wool coats for your pets
            result.add("" + boughtCounts.get(key) + " of " + clickCounts.get(key) + " " + key);
        }
        return result;
    }
}
