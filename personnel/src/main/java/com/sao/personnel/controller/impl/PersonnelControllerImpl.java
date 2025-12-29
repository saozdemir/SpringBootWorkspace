package com.sao.personnel.controller.impl;

import com.sao.personnel.controller.IPersonnelController;
import com.sao.personnel.dto.PersonnelDto;
import com.sao.personnel.entity.Personnel;
import com.sao.personnel.service.IPersonnelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/personnel")
@Tag(name = "Personel", description = "Personel yönetim API")
public class PersonnelControllerImpl implements IPersonnelController {
    @Autowired
    private IPersonnelService personnelService;

    @GetMapping(path = "/list")
    @Operation(summary = "Tüm personel listesini getir")
    @Override
    public List<PersonnelDto> getAllPersonnel() {
        return personnelService.getAllPersonnel();
    }

    @GetMapping(path = "/list/{id}")
    @Operation(summary = "ID ile personnel getir")
    @Override
    public Personnel getPersonnelById(@PathVariable Long id) {
        return personnelService.getPersonnelById(id);
    }

    @Override
    public PersonnelDto getPersonnelEducations(Long personnelId) {
        //TODO yazılacak
        return null;
    }

    @GetMapping(path = "/list/{personnelId}/details")
    @Operation(summary = "ID ile personnel detaylarını getir")
    @Override
    public PersonnelDto getPersonnelDetails(@PathVariable Long personnelId) {
        return personnelService.getPersonnelDetails(personnelId);
    }

    /**
     * /search?name=Ahmet
     * /search?surname=Özdemir
     * /search?name=Ahmet&surname=Özdemir
     */
    @Operation(
            summary = "Personeli ada ve soyada göre ara",
            description = "İsim ve/veya soyisim ile personnel araması yapar. Her ikisi de opsiyoneldir.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Başarılı"),
                    @ApiResponse(responseCode = "404", description = "Kayıt bulunamadı")
            })

    @GetMapping("/list/details")
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetails(){
        return personnelService.getPersonnelListWithAllDetails();
    }

    @GetMapping("/list/details-virtual")
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThread() {
        return personnelService.getPersonnelListWithAllDetailsVirtualThread();
    }

    @GetMapping("/list/details-platform")
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsPlatformThread() {
        return personnelService.getPersonnelListWithAllDetailsPlatformThread();
    }

    @GetMapping("/list/details-virtual-semaphore")
    @Override
    public List<PersonnelDto> getPersonnelListWithAllDetailsVirtualThreadWithSemaphore() {
        return personnelService.getPersonnelListWithAllDetailsVirtualThreadWithSemaphore();
    }
}
