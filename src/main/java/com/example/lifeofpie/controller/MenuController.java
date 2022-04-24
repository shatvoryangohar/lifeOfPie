package com.example.lifeofpie.controller;

import com.example.lifeofpie.dto.CreateMenuRequest;
import com.example.lifeofpie.entity.Category;
import com.example.lifeofpie.entity.Menu;
import com.example.lifeofpie.entity.Role;
import com.example.lifeofpie.entity.User;
import com.example.lifeofpie.security.CurrentUser;
import com.example.lifeofpie.service.CategoryService;
import com.example.lifeofpie.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {


    public final ModelMapper mapper;
    public final MenuService menuService;
    public final CategoryService categoryService;

    @Value("${lifeOfPie.upload.path}")
    private String imagePath;


    @GetMapping("/loginPage")
    public String login() {
        return "loginPage";
    }

    @GetMapping("/successLogin")
    public String successLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return "redirect:/";
        }
        User user = currentUser.getUser();
        if (user.getRole() == Role.ADMIN) {
            return "redirect:/admin";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/menu")
    public String menu(ModelMap map) {
        List<Menu> menus = menuService.findAll();
        map.addAttribute("menus", menus);

        return "menu";
    }

    @GetMapping("/addDish")
    public String AddDishPage(ModelMap map) {
        List<Category> categories = categoryService.findAll();
        map.addAttribute("categories", categories);
        return "addDish";
    }

    @PostMapping("/addDish")
    public String addDish(@ModelAttribute Menu menu,
                          @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imagePath + fileName);
            multipartFile.transferTo(file);
            menu.setPicUrl(fileName);

        }
        menuService.save(menu);


        return "redirect:/addDish";
    }

    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(imagePath + picName);
        return IOUtils.toByteArray(inputStream);
    }


}
