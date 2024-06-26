package org.shield.service.Impl;

import lombok.AllArgsConstructor;
import org.shield.entities.Block;
import org.shield.repository.BlockRepository;
import org.shield.service.BlockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlockServiceImpl implements BlockService {
    private final BlockRepository blockRepository;

    @Override
    public List<Block> getBlocks() {
        return blockRepository.findAll();
    }
}
