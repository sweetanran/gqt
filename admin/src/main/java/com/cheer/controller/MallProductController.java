package com.cheer.controller;

import com.cheer.common.Page;
import com.cheer.common.Result;
import com.cheer.dto.MallProductAddDTO;
import com.cheer.dto.MallProductQueryDTO;
import com.cheer.dto.MallProductUpdateDTO;
import com.cheer.service.MallProductService;
import com.cheer.vo.MallProductInfoVO;
import com.cheer.vo.MallProductListVO;
import io.swagger.annotations.Api;
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
@RequestMapping("/product")
public class MallProductController {

    @Autowired
    private MallProductService mallProductService;

    @ApiOperation("查询商品列表")
    @PostMapping("/list")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result<Page<MallProductListVO>> list(@RequestBody MallProductQueryDTO productQueryDTO) {
        return Result.success(mallProductService.listByQueryCondition(productQueryDTO));
    }

    @ApiOperation("添加商品")
    @PostMapping("/add")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result add(@RequestBody @Valid MallProductAddDTO productAddDTO) {
        mallProductService.add(productAddDTO);
        return Result.success();
    }

    @ApiOperation("查询商品详情")
    @GetMapping("/info")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result<MallProductInfoVO> info(@RequestParam Long id) {
        return Result.success(mallProductService.info(id));
    }

    @ApiOperation("修改商品")
    @PostMapping("/update")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result update(@RequestBody @Valid MallProductUpdateDTO productUpdateDTO) {
        mallProductService.update(productUpdateDTO);
        return Result.success();
    }

    @ApiOperation("删除商品")
    @PostMapping("/delete")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result delete(@RequestBody List<Long> ids) {
        mallProductService.delete(ids);
        return Result.success();
    }

    @ApiOperation("审核商品")
    @GetMapping("/review")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result review(@RequestParam Long id) {
        mallProductService.review(id);
        return Result.success();
    }


    @ApiOperation("下架商品")
    @GetMapping("/remove")
    @PreAuthorize("@permissionValidator.isAdmin()")
    public Result remove(@RequestParam Long id) {
        mallProductService.remove(id);
        return Result.success();
    }
//
//    /**
//     * 查询所有商品
//     */
//    @PostMapping("/list/all")
//    @PreAuthorize("@permissionValidator.isAdmin()")
//    public Result listAll() {
//        List<ProductSimpleVO> productSimpleVOList = productService.listAll();
//        return Result.success(productSimpleVOList);
//    }

}
