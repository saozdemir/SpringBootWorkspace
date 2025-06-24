package com.sao.personneleducation.controller.impl;

import com.sao.personneleducation.controller.IPersonnelController;
import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.dto.PersonnelDto;
import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Personnel;
import com.sao.personneleducation.service.IPersonnelService;
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

    @PostMapping(path = "/save")
    @Operation(
            summary = "Yeni personel oluştur",
            description = "Personel bilgilerini kaydeder",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Başarılı"),
                    @ApiResponse(responseCode = "400", description = "Geçersiz istek")
            })
    @Override
    public Personnel savePersonnel(@RequestBody Personnel personnel) {
        return personnelService.savePersonnel(personnel);
    }

    @GetMapping(path = "/list")
    @Operation(summary = "Tüm personel listesini getir")
    @Override
    public List<Personnel> getAllPersonnel() {
        return personnelService.getAllPersonnel();
    }

    @GetMapping(path = "/list/{id}")
    @Operation(summary = "ID ile personnel getir")
    @Override
    public Personnel getPersonnelById(@PathVariable Long id) {
        return personnelService.getPersonnelById(id);
    }

    @GetMapping("/{personnelId}/educations")
    @Operation(
            summary = "Personnel'a ait educationları getir",
            description = "Verilen personnel ID'sine ait Eğitim Programı listesini döner",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Başarılı"),
                    @ApiResponse(responseCode = "404", description = "Personnel bulunamadı")
            })
    @Override
    public PersonnelDto getPersonnelEducations(
            @Parameter(description = "Personnel ID", example = "1")
            @PathVariable Long personnelId) {
        return personnelService.getEducationsByPersonnelId(personnelId);
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
    @GetMapping("/search")
    @Override
    public List<Personnel> searchPersonnel(
            @Parameter(description = "Aranacak isim (opsiyonel)", example = "Ahmet")
            @RequestParam(required = false) String name,

            @Parameter(description = "Aranacak soyisim (opsiyonel)", example = "Özdemir")
            @RequestParam(required = false) String surname) {

        return personnelService.searchPersonnel(name, surname);
    }

    @GetMapping("/{personnelId}/tasks")
    @Override
    public PersonnelDto getPersonnelDtoById(@PathVariable Long personnelId) {
        return personnelService.getPersonnelDtoById(personnelId);
    }

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
}
