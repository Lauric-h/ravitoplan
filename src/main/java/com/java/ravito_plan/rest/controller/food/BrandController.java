package com.java.ravito_plan.rest.controller.food;

import com.java.ravito_plan.food.application.dto.command.CreateBrandCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateBrandCommand;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.BrandSummaryView;
import com.java.ravito_plan.food.domain.ports.inbound.BrandPort;
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

    private final BrandPort brandService;
    public BrandController(BrandPort brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandSummaryView>> getAllBrands()
    {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @PostMapping
    public ResponseEntity<Void> createBrand(@RequestBody CreateBrandCommand command) {
        BrandDetailView brand = this.brandService.createBrand(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDetailView> getBrandById(@PathVariable("id") Long id) {
        BrandDetailView brand = this.brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDetailView> updateBrand(@PathVariable("id") Long id, @RequestBody
            UpdateBrandCommand updateBrandCommand) {
        BrandDetailView brand = this.brandService.updateBrand(updateBrandCommand);
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrandById(@PathVariable("id") Long id) {
        this.brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
