package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // Раньше с файлами работали так
        File file = new File("text.txt");
        if (!file.exists()){
            file.createNewFile();
        }

        // Но это было очень неудобно, так как мало функционала
        // Поэтому в 7 джаве добавили Path и Files

        // Path и его возможности:
        Path path = Paths.get("text.txt").toAbsolutePath();
        // Или через файл
        //File file = new File("Text.txt");
        //path = file.toPath();

        // Например, можно узнать (имя файла/путь к папке/начальная точка/полный путь)
        System.out.println(path.getFileName());
        System.out.println(path.getParent());
        System.out.println(path.getRoot());
        System.out.println(path.toAbsolutePath());
        // размер / это директория? / спрятан? / исполняемый? / читаемый? / можно записать?
        System.out.println(Files.size(path));
        System.out.println(Files.isDirectory(path));
        System.out.println(Files.isHidden(path));
        System.out.println(Files.isExecutable(path));
        System.out.println(Files.isReadable(path));
        System.out.println(Files.isWritable(path));
        // Дополнительные атрибуты (например последнее время работы)
        BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
        System.out.println(basicFileAttributes.lastAccessTime());
        // для Linux
        // PosixFileAttributes posixFileAttributes = Files.readAttributes(path, PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS);


        //копирование
        Files.copy(path, Paths.get("text2.txt"), StandardCopyOption.REPLACE_EXISTING);

        //переименовать
        Files.move(Paths.get("text2.txt"), Paths.get("text3.txt"), StandardCopyOption.REPLACE_EXISTING);

        //удалить
        Files.deleteIfExists(Paths.get("text3.txt"));

        // Прочитать файл в массив байтов
        byte[] bytes = Files.readAllBytes(path);
        for (int i=0; i<bytes.length; i++){
            System.out.println(bytes[i]);
        }

        //Записать в файл
        Files.write(path, "text\ntext\n".getBytes(), StandardOpenOption.APPEND);

        //Считать в Лист
        List<String> list = Files.readAllLines(path);
        for (String s: list){
            System.out.println(s);
        }
        //Добавим в него новую строчку и перезапишем файл
        list.add("new text");
        list.add("new text 2");
        Files.write(path, list);
        
    }
}
