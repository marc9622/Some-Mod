package somemod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import somemod.frost.item.FrostItems;

public class ItemTagProvider extends FabricTagProvider<Item> {

    public ItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup registries) {
        
        getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES)
            .add(FrostItems.ARCTIC_HAT, FrostItems.ARCTIC_JACKET, FrostItems.ARCTIC_PANTS, FrostItems.ARCTIC_BOOTS)
            .setReplace(false);

    }

}

