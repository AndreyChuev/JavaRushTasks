package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Читаем и пишем в файл: Human
*/

public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("temp", null);
//            String your_file_name = "D:\\IDEA_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2001\\serialize.txt";
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println("Результат = " + ivanov.equals(somePerson));
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            StringBuilder builder = new StringBuilder();
            builder.append("Human{").append(name).append(";").append("Assets{");
            for (Asset asset : assets) {
                String name = asset.getName() != null ? asset.getName() : "DEFAULT";
                String price = asset.getPrice() != 0d ? String.valueOf(asset.getPrice()) : "0.0";
                builder.append("Asset{").append(name).append(";").append(price).append("}");
            }
            builder.append("}\n");
            outputStream.write(builder.toString().getBytes());
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            Matcher human = Pattern.compile("Human\\{\\w+;").matcher(line);
            String[] n = null;
            if (human.find())
                this.name = human.group().split("Human\\{|;")[1];
            Matcher assets = Pattern.compile("Asset\\{.+?;.+?\\}").matcher(line);
            ArrayList<Asset> assetList = new ArrayList<>();
            while (assets.find()) {
                String[] asset = assets.group().split("Asset\\{|;|\\}");
                assetList.add(new Asset(asset[1], Double.parseDouble(asset[2])));
            }
            this.assets = assetList;
        }
    }
}
