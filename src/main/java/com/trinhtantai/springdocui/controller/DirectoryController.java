package com.trinhtantai.springdocui.controller;

import com.trinhtantai.springdocui.service.DirectoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectoryController {

    private final DirectoryService directoryService = new DirectoryService();

    @GetMapping("/directory")
    public String showDirectory(Model model) {
        String projectRootPath = System.getProperty("user.dir");
        String fullPath = projectRootPath + "/specmatic/open-api-contract/contracts";
        DirectoryService.Folder folderStructure = directoryService.getFolderStructure(fullPath);
        model.addAttribute("folder", folderStructure);
        return "directory";
    }
}
