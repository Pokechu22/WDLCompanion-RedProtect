package wdl.redprotect;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import wdl.range.IRangeGroup;
import wdl.range.IRangeGroupType;

public class OwnedRegionRangeGroupType implements IRangeGroupType<OwnedRegionRangeProducer> {
	@Override
	public boolean isValidConfig(ConfigurationSection config,
			List<String> warnings, List<String> errors) {
		return true;
	}

	@Override
	public OwnedRegionRangeProducer createRangeProducer(IRangeGroup group,
			ConfigurationSection config) {
		return new OwnedRegionRangeProducer(group);
	}

	@Override
	public void dispose() {
		
	}

}
