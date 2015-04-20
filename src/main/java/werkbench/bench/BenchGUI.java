package werkbench.bench;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import werkbench.helper.SpatialHelper;
import werkbench.reference.Compendium;
import werkbench.reference.Compendium.AdjacentBlockType;
import werkbench.reference.Compendium.RelativeBenchSide;

public class BenchGUI extends GuiContainer
{
    BenchTileEntity bench;

    public BenchGUI(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {
        super(new BenchContainer(inventoryPlayer, bench));
        this.bench = bench;

        this.xSize = 420;

        this.ySize = 206;

    }

    public int getLeft()
    {
        return this.guiLeft;
    }

    public int getTop()
    {
        return this.guiTop;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mousex, int mousey)
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.background);
        int x = (width - xSize) / 2 + 122;
        int y = (height - ySize) / 2 + 40;
        drawTexturedModalRect(x, y, 0, 0, 176, 166);

        AdjacentBlockType leftBlock = SpatialHelper.getBlockForRelativeSide(bench, RelativeBenchSide.LEFT);
        AdjacentBlockType rightBlock = SpatialHelper.getBlockForRelativeSide(bench, RelativeBenchSide.RIGHT);

        if (leftBlock == AdjacentBlockType.CHEST_SINGLE || leftBlock == AdjacentBlockType.CHEST_DOUBLE)
        {
            if (bench.isChestDouble(SpatialHelper.getDirectionFromRelativeSide(bench, RelativeBenchSide.LEFT)))
            {
                renderDoubleChestLeft();
            } else
            {
                renderSingleChestLeft();
            }
        }

        if (leftBlock == AdjacentBlockType.FURNACE_INACTIVE || leftBlock == AdjacentBlockType.FURNACE_ACTIVE)
        {
            renderFurnaceLeft();
        }
        if (rightBlock == AdjacentBlockType.CHEST_SINGLE || rightBlock == AdjacentBlockType.CHEST_DOUBLE)
        {
            if (bench.isChestDouble(SpatialHelper.getDirectionFromRelativeSide(bench, RelativeBenchSide.RIGHT)))
            {
                renderDoubleChestRight();
            } else
            {
                renderSingleChestRight();
            }
        }
        if (rightBlock == AdjacentBlockType.FURNACE_INACTIVE || rightBlock == AdjacentBlockType.FURNACE_ACTIVE)
        {
            renderFurnaceRight();
        }
    }

    private void renderDoubleChestLeft()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.doubleChest);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2 + 30;
        drawTexturedModalRect(x, y, 0, 0, 122, 176);
    }

    private void renderSingleChestLeft()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.singleChest);
        int x = (width - xSize) / 2 + 54;
        int y = (height - ySize) / 2 + 30;
        drawTexturedModalRect(x, y, 0, 0, 68, 176);
    }

    private void renderDoubleChestRight()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.doubleChest);
        int x = (width - xSize) / 2 + 298;
        int y = (height - ySize) / 2 + 30;
        drawTexturedModalRect(x, y, 0, 0, 122, 176);
    }

    private void renderSingleChestRight()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.singleChest);
        int x = (width - xSize) / 2 + 298;
        int y = (height - ySize) / 2 + 30;
        drawTexturedModalRect(x, y, 0, 0, 68, 176);
    }

    private void renderFurnaceRight()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.furnace);
        /*
         * @TODO - get burn times from the tileEntity
         * TileEntityFurnace furnace = bench.getRightFurnaceTileEntity();
         * if (furnace != null)
         * {
         * if (furnace.isBurning())
         * {
         * drawTexturedModalRect(x, y + 38, 0, 76, 76,
         * furnace.getBurnTimeRemainingScaled(38));
         * } else
         * {
         * drawTexturedModalRect(x, y + 38, 0, 115, 76, 38);
         * }
         *
         * }
         */

        // @TODO - make these number self explanitory
        int x = (width - xSize) / 2 + 298;
        int y = (height - ySize) / 2 + 40;

        if (SpatialHelper.getBlockForRelativeSide(bench, RelativeBenchSide.RIGHT) == AdjacentBlockType.FURNACE_ACTIVE)
        {
            drawTexturedModalRect(x, y + 38, 0, 76, 76, 38);
        } else
        {
            drawTexturedModalRect(x, y + 38, 0, 115, 76, 38);
        }

        drawTexturedModalRect(x, y, 0, 0, 76, 76);

    }

    private void renderFurnaceLeft()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.furnace);

        // @TODO - make these number self explanitory
        int x = (width - xSize) / 2 + 46;
        int y = (height - ySize) / 2 + 40;

        if (SpatialHelper.getBlockForRelativeSide(bench, RelativeBenchSide.LEFT) == AdjacentBlockType.FURNACE_ACTIVE)
        {
            drawTexturedModalRect(x, y + 38, 0, 76, 76, 38);
        } else
        {
            drawTexturedModalRect(x, y + 38, 0, 115, 76, 38);
        }

        drawTexturedModalRect(x, y, 0, 0, 76, 76);

    }

}
