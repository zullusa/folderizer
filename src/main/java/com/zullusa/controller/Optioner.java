package com.zullusa.controller;

import org.apache.commons.cli.*;
import org.apache.commons.cli.Option.Builder;

import java.util.logging.Level;

/**
 * Purpose .
 */
class Optioner extends Baser{
    private final static String HELP_HEADER = "folderizer -m \"mp*,avi\" -p \"yyyy-MM-dd\"";
    private String[] args;
    private Options options;
    private CommandLine cmdline;

    Optioner(String[] args){
        this.args = args;
        init();
    }

    String getOptionValue(char opt, String def){
        return cmdline.getOptionValue(opt, def);
    }

    void printHelp(){
        new HelpFormatter().printHelp(HELP_HEADER, options);
    }

    private void init() {
        options = new Options()
                .addOption(Option.builder("m")
                        .argName("file_ext_mask")
                        .desc("Specify list separated \",\" of needed extension(s), you may use *, default \"mp*,avi\"")
                        .hasArg()
                        .longOpt("mask")
                        .build())
                .addOption(Option.builder("p")
                        .argName("pattern")
                        .desc("Pattern for new folder name, default \"yyyy-MM-dd\"")
                        .hasArg()
                        .longOpt("pattern")
                        .build())
                .addOption(Option.builder("d")
                        .argName("path\\to\\destination\\folder")
                        .desc("Destination folder")
                        .hasArg()
                        .longOpt("destination")
                        .build())
                .addOption("h", "help", false,"Show this help");
        try {
            cmdline = new DefaultParser().parse(options, args);
            if (cmdline.hasOption("h"))
                printHelp();
        } catch (ParseException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
