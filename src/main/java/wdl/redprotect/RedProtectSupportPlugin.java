package wdl.redprotect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;
import org.mcstats.Metrics.Graph;
import org.mcstats.Metrics.Plotter;

import br.net.fabiozumbi12.RedProtect.API.RedProtectAPI;
import wdl.RangeGroupTypeRegistrationEvent;
import wdl.range.IRangeProducer;

/**
 * WDLCompanion support plugin for 
 * <a href="https://www.spigotmc.org/resources/redprotect.15841/">RedProtect</a>
 */
public class RedProtectSupportPlugin extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		
		try {
			class StringPlotter extends Plotter {
				public StringPlotter(String str) {
					super(str);
				}
				
				@Override
				public int getValue() {
					return 1;
				}
			}
			
			Metrics metrics = new Metrics(this);
			
			Graph redProtectVersionGraph = metrics.createGraph("redProtectVersion");
			String redProtectVersion = getProvidingPlugin(RedProtectAPI.class)
					.getDescription().getFullName();
			redProtectVersionGraph.addPlotter(new StringPlotter(redProtectVersion));
			
			Graph wdlcVersionGraph = metrics.createGraph("wdlcompanionVersion");
			String wdlcVersion = getProvidingPlugin(IRangeProducer.class)
					.getDescription().getFullName();
			wdlcVersionGraph.addPlotter(new StringPlotter(wdlcVersion));
			
			metrics.start();
		} catch (Exception e) {
			getLogger().warning("Failed to start PluginMetrics :(");
		}
	}
	
	@EventHandler
	public void registerRangeGroupTypes(RangeGroupTypeRegistrationEvent e) {
		
	}
}
