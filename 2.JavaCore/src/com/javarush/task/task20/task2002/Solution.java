package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("tempFile", null);
//            String yourFile = "D:\\IDEA_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2002\\temp.txt";
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.users.add(new User("Настя","Хуястя",new Date(),false, User.Country.RUSSIA));
            javaRush.users.add(new User("Петя","Пупкин",new Date(),true, User.Country.RUSSIA));
            javaRush.users.add(new User("Вася","Попов",new Date(),true, User.Country.RUSSIA));
            javaRush.users.add(new User("Коля","Хохлов",new Date(),true, User.Country.UKRAINE));
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println("Результат = " + javaRush.equals(loadedObject));
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();
        private static final String DELIMITER = "@";

        public void save(OutputStream outputStream) throws Exception {
            for (User user : users) {
                StringJoiner joiner = new StringJoiner(DELIMITER);
                String firstName = user.getFirstName();
                String lastName = user.getLastName();
                String birthDate = String.valueOf(user.getBirthDate().getTime());
                String isMale = String.valueOf(user.isMale());
                String country = user.getCountry().toString();
                joiner.add(firstName)
                        .add(lastName)
                        .add(birthDate)
                        .add(isMale)
                        .add(country + "\n");
                outputStream.write(joiner.toString().getBytes());
                outputStream.flush();
            }
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()) {
                String[] data = reader.readLine().split(DELIMITER);
                String firstName = data[0];
                String lastName = data[1];
                Date birthDate = new Date(Long.parseLong(data[2]));
                boolean isMale = Boolean.parseBoolean(data[3]);
                User.Country country = User.Country.parseCountry(data[4]);
                users.add(new User(firstName,lastName,birthDate,isMale,country));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
