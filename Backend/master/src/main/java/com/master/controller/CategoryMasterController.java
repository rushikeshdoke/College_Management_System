package com.master.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.master.request.dto.AddCategoryDto;
import com.master.service.CategoryMasterService;

@RestController
@RequestMapping("/category")
public class CategoryMasterController {
	
	@Autowired
	private CategoryMasterService categoryMasterService;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> addCategory(@RequestBody AddCategoryDto addCategoryDto){
		return categoryMasterService.addCategory(addCategoryDto);
		
	}
	
	@GetMapping("/has-id/{categoryId}")
	public ResponseEntity<Map<String,Object>> getCategoryById(@PathVariable Long categoryId){
		return categoryMasterService.getCategoryById(categoryId);
		
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getCategoryList(){
		return categoryMasterService.getCategoryList();
		
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<Map<String, Object>> updateCategory(@PathVariable Long categoryId, @RequestBody AddCategoryDto addCategoryDto){
		return categoryMasterService.updateCategory(categoryId,addCategoryDto);
		
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Long categoryId){
		return categoryMasterService.deleteCategory(categoryId);
		
	}
}
