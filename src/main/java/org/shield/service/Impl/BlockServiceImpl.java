package org.shield.service.Impl;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.shield.model.Block;
import org.shield.repository.BlockRepository;
import org.shield.service.BlockService;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        Long lastProof = 0L;
        if (i == 0){
            block.setPreviousHash(0L);
            lastProof = 0L;
        }
        block.setProof(proofOfWork(lastProof));
        blockRepository.save(block);
    }

    public Long proofOfWork(Long lastProof){
        Long proof = 0L;
        while (!validProofOfWork(lastProof, proof)){
            proof++;
        }
        return proof;
    }

    public boolean validProofOfWork(Long lastProof, Long proof){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] guess = (String.format("%d%d", lastProof, proof)).getBytes();
        byte[] guessHash = messageDigest.digest(guess);
        String hex = Hex.encodeHexString(guessHash);
        System.out.println(hex);
        for (int i = 0; i < 4; i++){
            if (hex.charAt(i) != '0'){
                return false;
            }
        }
        return true;
    }

}
