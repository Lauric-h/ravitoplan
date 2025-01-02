package com.java.ravito_plan.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.food.application.dto.command.CreateBrandCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateBrandCommand;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.BrandSummaryView;
import com.java.ravito_plan.food.application.service.BrandApplicationService;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import com.java.ravito_plan.unit.Generator;
import jakarta.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestBrandApplicationService {

    @Autowired
    private BrandApplicationService brandApplicationService;

    @MockBean
    private BrandRepository brandRepository;

//    @Test
//    public void testGetAllBrands() {
//        List<Brand> brands = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Brand brand = Generator.getBrand((long) i, String.format("Brand %s", i),
//                    Collections.emptyList());
//            brands.add(brand);
//        }
//        when(brandRepository.findAll()).thenReturn(brands);
//
//        List<BrandSummaryView> expectedBrands = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            BrandSummaryView brand = Generator.getBrandSummaryView(String.format("Brand %s", i));
//            expectedBrands.add(brand);
//        }
//
//        List<BrandSummaryView> actualBrands = brandApplicationService.getAllBrands();
//        for (int i = 0; i < 5; i++) {
//            assertThat(actualBrands.get(i).name).isEqualTo(expectedBrands.get(i).name);
//        }
//    }

    @Test
    public void testGetBrand() {
        Brand brand = Generator.getBrand(1L, "Test Brand", Collections.emptyList());
        when(this.brandRepository.findById(brand.getId())).thenReturn(brand);

        BrandDetailView expected = Generator.getBrandDetailView("Test Brand",
                Collections.emptyList());

        BrandDetailView actual = this.brandApplicationService.getBrandById(brand.getId());
        assertThat(actual.name).isEqualTo(expected.name);
        assertThat(actual.foods.size()).isEqualTo(expected.foods.size());
    }

    @Test
    public void testCreateBrandWithNullNameFails() {
        CreateBrandCommand createBrandCommand = Generator.getCreateBrandCommand(null);

        when(this.brandRepository.save(any(Brand.class))).thenThrow(new PersistenceException());

        assertThatThrownBy(
                () -> brandApplicationService.createBrand(createBrandCommand)).isInstanceOf(
                PersistenceException.class);

        verify(this.brandRepository).save(any(Brand.class));
    }

    @Test
    public void testCreateBrand() {
        var brandName = "Test Brand";

        CreateBrandCommand createBrandCommand = Generator.getCreateBrandCommand(brandName);
        ArgumentCaptor<Brand> brandCaptor = ArgumentCaptor.forClass(Brand.class);

        BrandDetailView expected = Generator.getBrandDetailView(brandName, Collections.emptyList());
        when(this.brandRepository.save(any(Brand.class))).thenAnswer(
                invocation -> invocation.getArgument(0));

        BrandDetailView actual = this.brandApplicationService.createBrand(createBrandCommand);

        verify(this.brandRepository).save(brandCaptor.capture());

        assertThat(actual.name).isEqualTo(expected.name);
        assertThat(actual.foods).isEmpty();
    }

    @Test
    public void testUpdateBrand() {
        var brandName = "Test Brand";
        Brand brand = Generator.getBrand(1L, brandName, Collections.emptyList());
        UpdateBrandCommand updateBrandCommand = Generator.getUpdateBrandCommand(1L,
                "Updated brand name");
        BrandDetailView expected = Generator.getBrandDetailView("Updated brand name",
                Collections.emptyList());

        when(this.brandRepository.findById(brand.getId())).thenReturn(brand);
        when(this.brandRepository.save(any(Brand.class))).thenAnswer(
                invocation -> invocation.getArgument(0));

        BrandDetailView actual = this.brandApplicationService.updateBrand(updateBrandCommand);

        ArgumentCaptor<Brand> brandCaptor = ArgumentCaptor.forClass(Brand.class);
        verify(this.brandRepository).save(brandCaptor.capture());
        assertThat(actual.name).isEqualTo(expected.name);
    }
}
