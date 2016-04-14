package wdl.redprotect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import br.net.fabiozumbi12.RedProtect.Region;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Types of ownership / membership of a region.
 *
 * See <a href="https://github.com/FabioZumbi12/RedProtect/wiki/Player-Ranks">
 * the RedProtect wiki</a> for more info about each type.
 */
public enum OwnershipType {
	LEADER("leader") {
		@Override
		public boolean isValidRegionForPlayer(Player player, Region region) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	ADMIN("admin") {
		@Override
		public boolean isValidRegionForPlayer(Player player, Region region) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	MEMBER("member", "any", "all") {
		@Override
		public boolean isValidRegionForPlayer(Player player, Region region) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	/**
	 * All aliases used by this ownership type.
	 */
	public final List<String> aliases;
	/**
	 * All valid names for ownership types.
	 */
	public static final List<String> NAMES;
	/**
	 * A map of aliases to instances.  Note: Keys should be toUpperCased()
	 */
	private static final Map<String, OwnershipType> BY_ALIAS;
	
	/**
	 * Constructor.
	 * 
	 * @param aliases Possible names that can be found in the configuration.
	 */
	private OwnershipType(String... aliases) {
		this.aliases = ImmutableList.copyOf(aliases);
	}
	
	/**
	 * Does the given player have the required type of ownership in the given
	 * region?
	 */
	public abstract boolean isValidRegionForPlayer(Player player, Region region);
	
	/**
	 * Gets the OwnershipType with the given name or alias.
	 * 
	 * @param name the alias
	 * @return the type, or null if it can't be found.
	 */
	public static OwnershipType match(String name) {
		return BY_ALIAS.get(name.toUpperCase());
	}
	
	static {
		List<String> names = new ArrayList<String>();
		Map<String, OwnershipType> byAlias = new HashMap<>();
		for (OwnershipType type : values()) {
			for (String alias : type.aliases) {
				names.add(alias);
				byAlias.put(alias.toUpperCase(), type);
			}
		}
		
		NAMES = ImmutableList.copyOf(names);
		BY_ALIAS = ImmutableMap.copyOf(byAlias);
	}
}
