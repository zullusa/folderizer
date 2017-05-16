package com.zullusa.controller;


import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * Purpose .
 */
class Folderizer extends Baser{

    private String fileTypes = "*";
    private String folderPattern = "yyy-MM-dd";
    private String dest = "./";

    Folderizer(String[] args){
        Optioner optioner = new Optioner(args);
        fileTypes = optioner.getOptionValue('m', "mp*,avi");
        folderPattern = optioner.getOptionValue('p', "yyyy-MM-dd");
        dest = optioner.getOptionValue('d', "./");
        dest = dest.endsWith("\\") || dest.endsWith("/") ? dest: dest + "/";
    }

    void run(){
        Date date;
        DateFormat sdf = new SimpleDateFormat(folderPattern);
        Mover mover = new Mover(dest);

        Path path = Paths.get("");
        BasicFileAttributes attrs;
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{" + fileTypes + "}")){
            for (Path file : stream){
                attrs = Files.readAttributes(file, BasicFileAttributes.class);
                date = new Date(attrs.lastModifiedTime().toMillis());
                mover.moveFile(file, sdf.format(date));
            }
        } catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
