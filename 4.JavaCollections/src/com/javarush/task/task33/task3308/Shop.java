package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Shop {

    public Goods goods;

    public int count;
    public double profit;

    @XmlElement(name = "secretData")
    public String[] secretData;

    public static class Goods {

        @XmlElementWrapper(name = "goods")
        public List<String> names;
    }
}
