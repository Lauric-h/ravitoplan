package com.java.ravito_plan.rest.controller.food;

import com.java.ravito_plan.food.application.dto.BrandDto;
import com.java.ravito_plan.food.application.dto.BrandFullDto;
import com.java.ravito_plan.food.domain.service.BrandService;
import com.java.ravito_plan.rest.view.food.BrandRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandDto>> getAllBrands()
    {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @PostMapping
    public ResponseEntity<Void> createBrand(@RequestBody BrandRequest brandRequest) {
        BrandDto brandDto = this.brandService.create(brandRequest.name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandFullDto> getBrandById(@PathVariable("id") Long id) {
        BrandFullDto brand = this.brandService.getById(id):
        return ResponseEntity.ok(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBrand(@PathVariable("id") Long id, @RequestBody BrandRequest brandRequest) {
        this.brandService.updateBrand(new BrandDto(brandRequest.name), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrandById(@PathVariable("id") Long id) {
        this.brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
