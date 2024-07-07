package com.trinhtantai.springdocui.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryService {

    public Folder getFolderStructure(String path) {
        return readFolder(new File(path));
    }

    private Folder readFolder(File directory) {
        Folder folder = new Folder(directory.getName());
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    folder.addSubFolder(readFolder(file));
                } else {
                    folder.addFile(file.getName());
                }
            }
        }
        return folder;
    }

    public static class Folder {
        private String name;
        private List<Folder> subFolders = new ArrayList<>();
        private List<String> files = new ArrayList<>();

        public Folder(String name) {
            this.name = name;
        }

        public void addSubFolder(Folder folder) {
            subFolders.add(folder);
        }

        public void addFile(String fileName) {
            files.add(fileName);
        }

        // Getters
        public String getName() {
            return name;
        }

        public List<Folder> getSubFolders() {
            return subFolders;
        }

        public List<String> getFiles() {
            return files;
        }
    }
}
