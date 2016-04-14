package wdl.redprotect;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import br.net.fabiozumbi12.RedProtect.RedProtect;
import br.net.fabiozumbi12.RedProtect.Region;
import wdl.range.IRangeGroup;
import wdl.range.IRangeProducer;
import wdl.range.ProtectionRange;

public class OwnedRegionRangeProducer implements IRangeProducer {
	private final IRangeGroup rangeGroup;
	private final OwnershipType type;
	
	public OwnedRegionRangeProducer(IRangeGroup group, OwnershipType type) {
		this.rangeGroup = group;
		this.type = type;
	}

	@Override
	public List<ProtectionRange> getInitialRanges(Player player) {
		Set<Region> allRegions = RedProtect.rm.getRegionsByWorld(player.getWorld());
		
		List<ProtectionRange> returned = new ArrayList<>();
		for (Region region : allRegions) {
			if (!type.isValidRegionForPlayer(player, region)) {
				continue;
			}
			int minX = (int)Math.floor(region.getMinMbrX() / 16f);
			int maxX = (int)Math.floor(region.getMaxMbrX() / 16f);
			int minZ = (int)Math.floor(region.getMinMbrZ() / 16f);
			int maxZ = (int)Math.floor(region.getMaxMbrZ() / 16f);
			returned.add(new ProtectionRange(region.getID(), minX, minZ, maxX, maxZ));
		}
		return returned;
	}

	@Override
	public IRangeGroup getRangeGroup() {
		return rangeGroup;
	}

	@Override
	public void dispose() {
		
	}

}
