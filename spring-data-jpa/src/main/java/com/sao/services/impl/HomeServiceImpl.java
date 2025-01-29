package com.sao.services.impl;

import com.sao.dto.HomeDto;
import com.sao.dto.RoomDto;
import com.sao.entities.Home;
import com.sao.entities.Room;
import com.sao.repository.HomeRepository;
import com.sao.services.IHomeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 30 Oca 2025
 * <p>
 * @description:
 */
@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private HomeRepository homeRepository;

    @Override
    public HomeDto findHomeById(Long id){
        HomeDto homeDto = new HomeDto();
        Optional<Home> optional = homeRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }

        Home home = optional.get();
        List<Room> rooms = optional.get().getRoom();
        BeanUtils.copyProperties(home, homeDto);

        if(rooms != null && !rooms.isEmpty()){
            for(Room room : rooms){
                RoomDto roomDto = new RoomDto();
                BeanUtils.copyProperties(room, roomDto);
                homeDto.getRooms().add(roomDto);
            }
        }

        return homeDto;
    }
}
