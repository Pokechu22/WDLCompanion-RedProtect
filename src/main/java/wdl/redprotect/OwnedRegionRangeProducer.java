package wdl.redprotect;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import br.net.fabiozumbi12.RedProtect.Region;
import br.net.fabiozumbi12.RedProtect.API.RedProtectAPI;
import wdl.range.IRangeGroup;
import wdl.range.IRangeProducer;
import wdl.range.ProtectionRange;

public class OwnedRegionRangeProducer implements IRangeProducer {
	private final IRangeGroup rangeGroup;
	
	public OwnedRegionRangeProducer(IRangeGroup group) {
		this.rangeGroup = group;
	}

	@Override
	public List<ProtectionRange> getInitialRanges(Player player) {
		Set<Region> regions = RedProtectAPI.getPlayerRegions(player.getUniqueId().toString(), player.getWorld());
		List<ProtectionRange> returned = new ArrayList<>();
		for (Region region : regions) {
			int minX = (int)Math.ceil(region.getMinMbrX() / 16f);
			int maxX = (int)Math.ceil(region.getMaxMbrX() / 16f);
			int minZ = (int)Math.ceil(region.getMinMbrZ() / 16f);
			int maxZ = (int)Math.ceil(region.getMaxMbrZ() / 16f);
			returned.add(new ProtectionRange(region.getID(), minX, maxX, minZ, maxZ));
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
