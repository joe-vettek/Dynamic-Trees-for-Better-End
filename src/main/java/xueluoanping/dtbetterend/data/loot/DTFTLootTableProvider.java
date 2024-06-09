package xueluoanping.dtbetterend.data.loot;


import com.ferreusveritas.dynamictrees.data.provider.DTLootTableProvider;
import com.ferreusveritas.dynamictrees.resources.Resources;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;


//  I inherited DTLootTableProvider, but many of its functions are private,
//  so I have to copy them to facilitate modification.

public class DTFTLootTableProvider extends DTLootTableProvider {

    private final PackOutput generator;
    private final String modId;
    private final ExistingFileHelper existingFileHelper;

    public DTFTLootTableProvider(PackOutput generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
        this.generator = generator;
        this.modId = modId;
        this.existingFileHelper = existingFileHelper;
    }

    // The reason why these functions appear is that
    // the loot table of the leaves block needs to be overwritten.

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {

        Resources.MANAGER.reload(Resources.MANAGER.prepareReload(null, null));
        // Resources.MANAGER.gatherData();
        // Resources.MANAGER.setup();
        // First generate the default


        // Now overwrite and generate the parts that need to be customized.
        // Not need any more
        // addTables();
        // writeTables(cache);
        return super.run(cache);
    }

}
