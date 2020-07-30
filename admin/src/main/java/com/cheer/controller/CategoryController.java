package com.cheer.controller;

import com.cheer.common.Result;
import com.cheer.dto.MallCategoryAddDTO;
import com.cheer.dto.MallCategoryProductDTO;
import com.cheer.dto.MallCategoryProductSortDTO;
import com.cheer.dto.MallCategoryUpdateDTO;
import com.cheer.service.MallCategoryProductRelationService;
import com.cheer.service.MallCategoryService;
import com.cheer.vo.MallCategoryProductVO;
import com.cheer.vo.MallCategorySimpleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author cheer
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private MallCategoryService mallCategoryService;

    @Autowired
    private MallCategoryProductRelationService mallCategoryProductRelationService;

    @ApiOperation("查询分类与商品的树形结构")
    @GetMapping("/tree")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result<List<MallCategoryProductVO>> tree(){
        return Result.success(mallCategoryService.tree());
    }

    @ApiOperation("添加分类")
    @PostMapping("/add")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result add(@RequestBody @Valid MallCategoryAddDTO categoryAddDTO){
        mallCategoryService.add(categoryAddDTO);
        return Result.success();
    }

    @ApiOperation("修改分类")
    @PostMapping("/update")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result update(@RequestBody @Valid MallCategoryUpdateDTO categoryUpdateDTO){
        mallCategoryService.update(categoryUpdateDTO);
        return Result.success();
    }

    @ApiOperation("删除分类")
    @PostMapping("/delete")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result delete(@RequestBody List<Long> ids){
        mallCategoryService.delete(ids);
        return Result.success();
    }

    @ApiOperation("修改分类排序")
    @PostMapping("/update/sort")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result updateSort(@RequestBody @Valid List<MallCategoryProductSortDTO> categoryProductSortDTOList){
        mallCategoryService.updateSort(categoryProductSortDTOList);
        return Result.success();
    }

    @ApiOperation("添加分类与商品的关联")
    @PostMapping("/add/product")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result addProduct(@RequestBody @Valid MallCategoryProductDTO categoryProductDTO){
        mallCategoryProductRelationService.add(categoryProductDTO);
        return Result.success();
    }

    @ApiOperation("删除分类与商品的关联")
    @PostMapping("/delete/product")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result deleteProduct(@RequestBody @Valid MallCategoryProductDTO categoryProductDTO){
        mallCategoryProductRelationService.delete(categoryProductDTO);
        return Result.success();
    }

    @ApiOperation("查询所有分类简要信息")
    @PostMapping("/list/all")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result<List<MallCategorySimpleVO>> listAll() {
        return Result.success(mallCategoryService.listAllSimply());
    }
}
