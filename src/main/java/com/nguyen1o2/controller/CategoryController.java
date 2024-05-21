package com.nguyen1o2.controller;

import com.nguyen1o2.payload.ResponseData;
import com.nguyen1o2.service.imp.CategoryServiceImp;
import com.nguyen1o2.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryServiceImp;

    @Autowired
    FileServiceImp fileServiceImp;

    @GetMapping()
    public ResponseEntity<?> getCategoryHomePage(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.getCategoryHomePage());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileCategory(@PathVariable String filename){
        Resource resource = fileServiceImp.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=/"+resource.getFilename()+"/").body(resource);
    }

    @CacheEvict(value = "CategoryCache",allEntries = true)
    @GetMapping("/clear_cache")
    public String clearCache(){
        return "Clear cache success";
    }
}
