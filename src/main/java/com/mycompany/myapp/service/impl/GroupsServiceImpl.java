package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Groups;
import com.mycompany.myapp.repository.GroupsRepository;
import com.mycompany.myapp.service.GroupsService;
import com.mycompany.myapp.service.dto.GroupsDTO;
import com.mycompany.myapp.service.mapper.GroupsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Groups}.
 */
@Service
@Transactional
public class GroupsServiceImpl implements GroupsService {

    private final Logger log = LoggerFactory.getLogger(GroupsServiceImpl.class);

    private final GroupsRepository groupsRepository;

    private final GroupsMapper groupsMapper;

    public GroupsServiceImpl(GroupsRepository groupsRepository, GroupsMapper groupsMapper) {
        this.groupsRepository = groupsRepository;
        this.groupsMapper = groupsMapper;
    }

    @Override
    public GroupsDTO save(GroupsDTO groupsDTO) {
        log.debug("Request to save Groups : {}", groupsDTO);
        Groups groups = groupsMapper.toEntity(groupsDTO);
        groups = groupsRepository.save(groups);
        return groupsMapper.toDto(groups);
    }

    @Override
    public GroupsDTO update(GroupsDTO groupsDTO) {
        log.debug("Request to save Groups : {}", groupsDTO);
        Groups groups = groupsMapper.toEntity(groupsDTO);
        groups = groupsRepository.save(groups);
        return groupsMapper.toDto(groups);
    }

    @Override
    public Optional<GroupsDTO> partialUpdate(GroupsDTO groupsDTO) {
        log.debug("Request to partially update Groups : {}", groupsDTO);

        return groupsRepository
            .findById(groupsDTO.getId())
            .map(existingGroups -> {
                groupsMapper.partialUpdate(existingGroups, groupsDTO);

                return existingGroups;
            })
            .map(groupsRepository::save)
            .map(groupsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Groups");
        return groupsRepository.findAll(pageable).map(groupsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GroupsDTO> findOne(Long id) {
        log.debug("Request to get Groups : {}", id);
        return groupsRepository.findById(id).map(groupsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Groups : {}", id);
        groupsRepository.deleteById(id);
    }
}
