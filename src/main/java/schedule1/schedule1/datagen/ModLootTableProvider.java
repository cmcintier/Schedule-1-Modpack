package schedule1.schedule1.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import schedule1.schedule1.block.ModBlocks;
import schedule1.schedule1.block.custom.GrandaddyPurpleBushBlock;
import schedule1.schedule1.block.custom.GrandaddyPurpleCropBlock;
import schedule1.schedule1.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        this.addDrop(ModBlocks.GRANDADDY_PURPLE_BLOCK, multipleOreDrops(ModBlocks.GRANDADDY_PURPLE_BLOCK, ModItems.GRANDADDY_PURPLE, 3, 12));
        // Checks that GRANDADDY_PURPLE_CROP is fully grown before dropping GRANDADDY_PURPLE
        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.GRANDADDY_PURPLE_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(GrandaddyPurpleCropBlock.AGE, 4));
        this.addDrop(ModBlocks.GRANDADDY_PURPLE_CROP, this.cropDrops(ModBlocks.GRANDADDY_PURPLE_CROP, ModItems.GRANDADDY_PURPLE, ModItems.SEED_GRANDADDY_PURPLE, builder2));

//        this.addDrop(ModBlocks.GRANDADDY_PURPLE_BUSH,
//                block -> this.applyExplosionDecay(block,
//                        LootTable.builder()
//                                .pool(LootPool.builder()
//                                        .conditionally(
//                                                BlockStatePropertyLootCondition.builder(ModBlocks.GRANDADDY_PURPLE_BUSH).properties(StatePredicate.Builder.create().exactMatch(GrandaddyPurpleBushBlock.AGE, 3))
//                                        )
//                                        .with(ItemEntry.builder(ModItems.GRANDADDY_PURPLE))
//                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(7.0F, 12.0F)))
//                                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
//                                ).pool(LootPool.builder()
//                                                .conditionally(
//                                                        BlockStatePropertyLootCondition.builder(ModBlocks.GRANDADDY_PURPLE_BUSH).properties(StatePredicate.Builder.create().exactMatch(GrandaddyPurpleBushBlock.AGE, 3))
//                                                )
//                                                .with(ItemEntry.builder(ModItems.GRANDADDY_PURPLE))
//                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
//                                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
//                                ).pool(LootPool.builder()
//                                                .conditionally(
//                                                        BlockStatePropertyLootCondition.builder(ModBlocks.GRANDADDY_PURPLE_BUSH).properties(StatePredicate.Builder.create().exactMatch(GrandaddyPurpleBushBlock.AGE, 2))
//                                                )
//                                                .with(ItemEntry.builder(ModItems.GRANDADDY_PURPLE))
//                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
//                                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
//                                )
//                )
//        );
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops){
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow((RegistryKeys.ENCHANTMENT));
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
