package com.java.ravito_plan.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.food.application.dto.command.CreateBrandCommand;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.service.BrandApplicationService;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.outbound.BrandRepository;
import jakarta.persistence.PersistenceException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
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
//    public void testGetAllBrands() {}


    @Test
    public void testCreateBrandWithNullNameFails() {
        CreateBrandCommand createBrandCommand = Generator.getCreateBrandCommand(null);

        when(brandRepository.save(any(Brand.class))).thenThrow(new PersistenceException());

        assertThatThrownBy(() -> brandApplicationService.createBrand(createBrandCommand))
                .isInstanceOf(PersistenceException.class);

        verify(brandRepository).save(any(Brand.class));
    }

    @Test
    public void testCreateBrand() {
        var brandName = "Test Brand";

        CreateBrandCommand createBrandCommand = Generator.getCreateBrandCommand(brandName);
        ArgumentCaptor<Brand> brandCaptor = ArgumentCaptor.forClass(Brand.class);

        BrandDetailView expected = Generator.getBrandDetailView(brandName, Collections.emptyList());
        when(brandRepository.save(any(Brand.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BrandDetailView actual = this.brandApplicationService.createBrand(createBrandCommand);

        verify(brandRepository).save(brandCaptor.capture());

        assertThat(actual.name).isEqualTo(expected.name);
        assertThat(actual.foods).isEmpty();
    }
}
