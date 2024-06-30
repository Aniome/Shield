package org.shield.service;

import org.shield.model.Block;
import java.util.List;

public interface BlockService {
    List<Block> getBlocks();
    void addBlock(Block block);
}
