package com.master.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.master.request.dto.AddCategoryDto;

public interface CategoryMasterService {

	ResponseEntity<Map<String, Object>> addCategory(AddCategoryDto addCategoryDto);

	ResponseEntity<Map<String, Object>> getCategoryList();

	ResponseEntity<Map<String, Object>> getCategoryById(Long categoryId);

	ResponseEntity<Map<String, Object>> updateCategory(Long categoryId, AddCategoryDto addCategoryDto);

	ResponseEntity<Map<String, Object>> deleteCategory(Long categoryId);

}
