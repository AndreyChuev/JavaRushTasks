package com.javarush.task.task14.task1414;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

/* 
MovieFactory
*/

public class Solution {
    public static HashSet<String> set = new HashSet<>();

    static {
        set.add("soapOpera");
        set.add("cartoon");
        set.add("thriller");
    }
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            Movie movie;
            String key = reader.readLine();
            movie = MovieFactory.getMovie(key);
            if (movie != null)
                System.out.println(movie.getClass().getSimpleName());
            if (!set.contains(key)) break;
        }

        /*
8 Создать переменную movie класса Movie и для каждой введенной строки(ключа):
8.1 получить объект используя MovieFactory.getMovie и присвоить его переменной movie
8.2 вывести на экран movie.getClass().getSimpleName()
        */

    }

    static class MovieFactory {

        static Movie getMovie(String key) {
            Movie movie = null;
            if ("soapOpera".equals(key)) {
                movie = new SoapOpera();
            } else if ("cartoon".equals(key)) {
                movie = new Cartoon();
            } else if ("thriller".equals(key)) {
                movie = new Thriller();
            }
            return movie;
        }
    }

    static abstract class Movie {
    }

    static class SoapOpera extends Movie {
    }

    static class Cartoon extends Movie {
    }

    static class Thriller extends Movie {
    }
}
