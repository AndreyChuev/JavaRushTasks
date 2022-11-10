package com.javarush.task.pro.task16.task1616;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.TreeSet;

/* 
Тренировка временных зон
*/

public class Solution {

    public static void main(String[] args) {
        TreeSet<String> sortedZones = getSortedZones();
        System.out.println(sortedZones.size());
        System.out.println(sortedZones.first());
        System.out.println(sortedZones.last());

        System.out.println(getBeijingDateTime());
    }

    static TreeSet<String> getSortedZones() {
        TreeSet<String> set = new TreeSet<>();
        for (String availableZoneId : ZoneId.getAvailableZoneIds()) {
            set.add(availableZoneId);
        }
        return set;
    }

    static ZonedDateTime getBeijingDateTime() {
        ZoneId zoneId = null;
        for (String sortedZone : getSortedZones()) {
            if (sortedZone.equalsIgnoreCase("Asia/Shanghai")) {
                zoneId = ZoneId.of(sortedZone);
                break;
            }
        }
        return ZonedDateTime.now(zoneId);
    }
}
