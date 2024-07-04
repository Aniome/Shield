package org.shield.service.interfaces;

import org.shield.entities.Block;
import java.util.List;

public interface BlockService {
    List<Block> getBlocks();
    void addBlock(Block block);
}
