package com.javarush.task.task18.task1828;

/*
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        if (args.length == 0) return;
        ProductStringDataBase dataBase = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
          dataBase = new ProductStringDataBase(reader.readLine());
          switch (args[0]) {
              case "-u":
                  dataBase.update(Integer.parseInt(args[1]), Product.getInstanceFromArgs(args, 2));
                  break;
              case "-d":
                  dataBase.delete(Integer.parseInt(args[1]));
                  break;
          }
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
            if (dataBase != null)
                dataBase.closes();
        }
    }

    public static class ProductStringDataBase {
        private ArrayList<Product> products = new ArrayList<>();
        private BufferedReader reader;
        private FileWriter writer;

        public ProductStringDataBase(String file) {
            try {
                reader = new BufferedReader(new FileReader(file));
                readInList();
                writer = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(int id, Product newProduct) {
            readInList();
            int index = -1;
            for (Product product : products) {
                if (product.getId() == id) {
                    index = products.indexOf(product);
                    break;
                }
            }
            newProduct.setId(id);
            products.set(index, newProduct);
            writeInFile();
        }

        public void delete(int id) {
            readInList();
            int index = -1;
            for (Product product : products) {
                if (product.getId() == id) {
                    index = products.indexOf(product);
                    break;
                }
            }
            products.remove(index);
            writeInFile();
        }

        private void readInList() {
            try {
                while (reader.ready()) {
                    String line = reader.readLine();
                    if (!line.equals("")) {
                        products.add(Product.getInstanceFormString(line));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void writeInFile() {
                products.forEach(s -> {
                    try {
                        writer.write(s.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        }

        public void closes() {
            try {
                if (reader != null)
                    reader.close();
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Product {
        private int id;
        private String productName;
        private String price;
        private String quantity;

        public Product(int id, String productName, String price, String quantity) {
            this.id = id;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public Product(String productName, String price, String quantity) {
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        private Product() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static Product getInstanceFromArgs(String[] str, int startIndex) {
            String productName = extractProductNameFormArray(str, startIndex);
            String price = str[str.length - 2];
            String quantity = str[str.length - 1];
            return new Product(productName, price, quantity);
        }

        public static Product getInstanceFormString(String str) {
            int id = Integer.parseInt(str.substring(0,8).trim());
            String productName = str.substring(8,38);
            String price = str.substring(38,46);
            String quantity = str.substring(46);
            return new Product(id, productName, price, quantity);
        }

        public String toString() {
            return formatter(String.valueOf(id), 8) +
                    formatter(productName,30) +
                    formatter(price, 8) +
                    formatter(quantity, 4) + System.lineSeparator();
        }

        private String formatter(String str, int maxLength) {
            String result = "";
            String pattern = "%-" + maxLength + "s";
            if (str.length() <= maxLength)
                result = String.format(pattern,str);
            else
                result = str.substring(0,maxLength);
            return result;
        }

        private static String extractProductNameFormArray(String[] array, int startIndex) {
            if (startIndex == 0) startIndex = 1;
            String result = "";
            for (int i = startIndex; i < array.length - 2; i++) {
                result += array[i] + " ";
            }
            return result;
        }
    }
}
