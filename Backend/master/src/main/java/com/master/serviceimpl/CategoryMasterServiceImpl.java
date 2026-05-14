package com.master.serviceimpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.master.entity.CategoryMaster;
import com.master.exception.ResourceNotFoundException;
import com.master.mapper.CategoryMapper;
import com.master.repository.CategoryMasterRepository;
import com.master.request.dto.AddCategoryDto;
import com.master.response.dto.CategoryResponseDto;
import com.master.service.CategoryMasterService;

@Service
public class CategoryMasterServiceImpl implements CategoryMasterService{
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private CategoryMasterRepository categoryMasterRepository;
	
	Map<String, Object> response = new HashMap<>();

	@Override
	public ResponseEntity<Map<String, Object>> addCategory(AddCategoryDto addCategoryDto) {

	    

	    try {

	        CategoryMaster categoryMaster =
	                categoryMapper.toCategoryMaster(addCategoryDto);

	        CategoryMaster savedCategory =
	                categoryMasterRepository.save(categoryMaster);

	        response.put("success", true);
	        response.put("message", "Category added successfully");
	        response.put("data", savedCategory);

	        return new ResponseEntity<>(response, HttpStatus.CREATED);

	    } catch (Exception e) {

	        throw new RuntimeException("Failed to add category"+ e.getMessage());
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> getCategoryList() {
		
		try {
		List<CategoryMaster> categoryList = categoryMasterRepository.findAll();
		
		 List<CategoryResponseDto> responseList =
		            categoryList.stream()
		                    .map(categoryMapper::tocategoryResponseDto)
		                    .toList();
		
		 response.put("success", true);
	        response.put("message", "Category List Fetched Successfully");
	        response.put("data", responseList);
		return new ResponseEntity<>(response,HttpStatus.OK);
		}catch(Exception e) {
			throw new RuntimeException("Failed to fetch category list  "+e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> getCategoryById(Long categoryId) {
		
		
		CategoryMaster categoryMaster = categoryMasterRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found...!"));
		
		CategoryResponseDto categoryResponseDto=categoryMapper.tocategoryResponseDto(categoryMaster);
		
		response.put("success", true);
        response.put("message", "Category Fetched Successfully");
        response.put("data", categoryResponseDto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateCategory(
	        Long categoryId,
	        AddCategoryDto addCategoryDto) {

	    Map<String, Object> response = new HashMap<>();

	    try {

	        CategoryMaster categoryMaster =
	                categoryMasterRepository.findById(categoryId)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException(
	                                "Category not found with id : "
	                                + categoryId
	                        ));

	        categoryMaster.setCategoryName(
	                addCategoryDto.getCategoryName()
	        );

	        CategoryMaster updatedCategory =
	                categoryMasterRepository.save(categoryMaster);
	        
	        CategoryResponseDto categoryResponseDto= categoryMapper.tocategoryResponseDto(updatedCategory);

	        response.put("success", true);
	        response.put(
	                "message", "Category updated successfully"
	        );

	        response.put("data", categoryResponseDto);

	        return new ResponseEntity<>(
	                response,
	                HttpStatus.OK
	        );

	    }  catch (Exception e) {

	        throw new RuntimeException(
	                "Failed to update category"
	        );
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> deleteCategory(
	        Long categoryId) {

	    Map<String, Object> response = new HashMap<>();

	    try {

	        CategoryMaster categoryMaster =
	                categoryMasterRepository.findById(categoryId)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException(
	                                "Category not found with id : "
	                                + categoryId
	                        ));
	        categoryMaster.setDeletedAt(LocalDateTime.now());
	        categoryMasterRepository.save(categoryMaster);
	       // categoryMasterRepository.delete(categoryMaster);

	        response.put("success", true);

	        response.put(
	                "message",
	                "Category deleted successfully"
	        );

	        return new ResponseEntity<>(
	                response,
	                HttpStatus.OK
	        );

	    } catch (ResourceNotFoundException e) {

	        throw e;

	    } catch (Exception e) {

	        throw new RuntimeException(
	                "Failed to delete category"
	        );
	    }
	}
}
