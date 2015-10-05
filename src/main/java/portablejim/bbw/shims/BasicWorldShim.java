package portablejim.bbw.shims;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import portablejim.bbw.basics.Point3d;

/**
 * Wrap a world to provide basic functions.
 */
public class BasicWorldShim implements IWorldShim {
    private World world;

    public BasicWorldShim(World world) {

        this.world = world;
    }

    @Override
    public Block getBlock(Point3d point) {
        if(world != null) {
            return world.getBlock(point.x, point.y, point.z);
        }
        return null;
    }

    @Override
    public boolean blockIsAir(Point3d point) {
        Block block = world.getBlock(point.x, point.y, point.z);
        return block.isAir(world, point.x, point.y, point.z);
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public boolean copyBlock(Point3d originalBlock, Point3d blockPos) {
        Block block = world.getBlock(originalBlock.x, originalBlock.y, originalBlock.z);
        int meta = world.getBlockMetadata(originalBlock.x, originalBlock.y, originalBlock.z);
        return world.setBlock(blockPos.x, blockPos.y, blockPos.z, block, meta, 3);
    }

    @Override
    public void setBlockToAir(Point3d blockPos) {
        world.setBlock(blockPos.x, blockPos.y, blockPos.z, Blocks.air);
    }

    @Override
    public int getMetadata(Point3d point) {
        if(world != null) {
            return world.getBlockMetadata(point.x, point.y, point.z);
        }
        return 0;
    }
}
