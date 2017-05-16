package com.zullusa.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Purpose .
 */
class Mover extends Baser{
    private String destination = "./";
    Mover(String dest) {
        this.destination = dest;
    }

    void moveFile(Path file, String folder) throws IOException {
        Path dest = Paths.get(makeDirectory(folder).toString(), file.getFileName().toString());
        log.info(dest.toString());
        Files.move(file, dest);
    }

    private Path makeDirectory(String folder) throws IOException {
        Path path = Paths.get(destination, folder);
        return Files.createDirectories(path);
    }
}
