package paulevs.betternether.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import paulevs.betternether.registry.NetherItems;
import ru.bclib.items.tool.BaseHoeItem;

public class NetherHoe extends BaseHoeItem {
	protected float speed;

	public NetherHoe(Tier material, float speed) {
		super(material, 1, -2.8F, NetherItems.defaultSettings().fireResistant());
		this.speed = speed;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return super.getDestroySpeed(stack, state) * speed;
	}
}