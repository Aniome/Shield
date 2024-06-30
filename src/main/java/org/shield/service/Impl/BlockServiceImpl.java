package org.shield.service.Impl;

import lombok.AllArgsConstructor;
import org.shield.model.Block;
import org.shield.repository.BlockRepository;
import org.shield.service.BlockService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class BlockServiceImpl implements BlockService {
    private final BlockRepository blockRepository;

    @Override
    public List<Block> getBlocks() {
        return blockRepository.findAll();
    }

    @Override
    public void addBlock(Block block) {
        Long i;
        for (i = 0L; i < Long.MAX_VALUE; i++) {
            if (!blockRepository.existsById(i)){
                break;
            }
        }
        block.setId(i);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        block.setTimestamp(timestamp.getTime());
        if (i == 0){
            block.setProof(0L);
            block.setPreviousHash(0);
        }
        //block.setProof();



        blockRepository.save(block);
    }

    public void proofOfWork(){
        int proof = 0;
        while (proof < 100){}
    }

    public boolean validProofOfWork(int lastProof, int proof){
        int guess = lastProof;
        return true;
    }

}
