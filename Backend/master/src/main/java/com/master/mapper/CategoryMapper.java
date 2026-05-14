package com.master.mapper;

import org.springframework.stereotype.Component;

import com.master.entity.CategoryMaster;
import com.master.request.dto.AddCategoryDto;
import com.master.response.dto.CategoryResponseDto;

@Component
public class CategoryMapper {

	public CategoryMaster toCategoryMaster(AddCategoryDto addCategoryDto) {
		
		CategoryMaster categoryMaster = new CategoryMaster();
		categoryMaster.setCategoryName(addCategoryDto.getCategoryName().toUpperCase());
		
		return categoryMaster;
	}
	
	public CategoryResponseDto tocategoryResponseDto(CategoryMaster categoryMaster) {
		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		categoryResponseDto.setCategoryId(categoryMaster.getCategoryId());
		categoryResponseDto.setCategoryName(categoryMaster.getCategoryName());
		
		return categoryResponseDto;
	}
	
	
}
