package net.quedoom.francium.mixin;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.quedoom.francium.block.ModdedBambooStalkBlock;
import net.quedoom.francium.block.SolidSugarCaneBlock;
import net.quedoom.francium.block.thicc_farming.EighthsDrinkableBlock;
import net.quedoom.francium.block.thicc_farming.RotateableEighthsEatableBlock;
import net.quedoom.francium.block.thicc_farming.ShearableEightsEatableBlock;
import net.quedoom.francium.block.thicc_crops.ThickableBeetrootBlock;
import net.quedoom.francium.block.thicc_crops.ThickableCarrotBlock;
import net.quedoom.francium.block.thicc_crops.ThickablePotatoBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(Blocks.class)
public class RegisterBlockMixin {
    @Inject(
            method = "register(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;",
            at =  @At(value = "HEAD"),
            cancellable = true)

    private static void register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, CallbackInfoReturnable<Block> cir) {
        String name = id.toString();

        reRegister(name.equals(string("potatoes")), id, cir, ThickablePotatoBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY));
        reRegister(name.equals(string("carrots")), id, cir, ThickableCarrotBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY));
        reRegister(name.equals(string("beetroots")), id, cir, ThickableBeetrootBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY));

        reRegister(name.equals(string("bedrock")), id, cir, factory, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(10.0F, 3600000.0F).isValidSpawn(Blocks::never).requiresCorrectToolForDrops());

        reRegister(name.equals(string("sugar_cane")), id, cir,
                SolidSugarCaneBlock::new,
                BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks()
                        .sound(SoundType.BAMBOO).pushReaction(PushReaction.DESTROY).strength(2f));

        reRegister(name.equals(string("bamboo")), id, cir,
                ModdedBambooStalkBlock::new,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.PLANT)
                        .forceSolidOn()
                        .randomTicks()
                        .strength(1.0F)
                        .sound(SoundType.BAMBOO)
                        .noOcclusion()
                        .dynamicShape()
                        .offsetType(BlockBehaviour.OffsetType.XZ)
                        .ignitedByLava()
                        .pushReaction(PushReaction.DESTROY)
                        .isRedstoneConductor(Blocks::never)
        );

        if ((name.contains("melon") || name.contains("pumpkin")) && !name.contains("stem")) {
            if (name.contains("pumpkin")) {
                if (name.contains("carved")) {
                    BlockBehaviour.Properties customProperty = properties.noOcclusion().strength(-1);
                    Function<BlockBehaviour.Properties, Block> newFactory = RotateableEighthsEatableBlock::new;
                    Block block = newFactory.apply(customProperty.setId(id));
                    cir.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, id, block));
                } else {
                    BlockBehaviour.Properties customProperty = properties.noOcclusion().strength(-1);
                    Function<BlockBehaviour.Properties, Block> newFactory = p -> new ShearableEightsEatableBlock(Blocks.CARVED_PUMPKIN, p);
                    Block block = newFactory.apply(customProperty.setId(id));
                    cir.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, id, block));
                }
            } else {
                BlockBehaviour.Properties customProperty = properties.noOcclusion().strength(-1);
                Function<BlockBehaviour.Properties, Block> newFactory = p -> new EighthsDrinkableBlock(1, 0, 3, 0.5f, p);
                Block block = newFactory.apply(customProperty.setId(id));
                cir.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, id, block));
            }
        }

        boolean forbiddenCopperCheck = name.contains("copper") && !name.contains("chain") && !name.contains("lantern") && !name.contains("door") && !name.contains("bar") && !name.contains("grate") && !name.contains("bulb");
        if (forbiddenCopperCheck || name.equals(string("gold_block"))) {
            reRegister(true, id, cir, factory, properties.noLootTable());
        }
    }

    @Unique
    private static String string(String string) {
        return "ResourceKey[minecraft:block / minecraft:" + string + "]";
    }
    @Unique
    private static String string(String namespace, String string) {
        return "ResourceKey[minecraft:block / " + namespace + ":" + string + "]";
    }
    @Unique
    private static String string(Identifier identifier) {
        return "ResourceKey[minecraft:block / " + identifier.getNamespace() + ":" +  identifier.getPath() + "]";
    }

    @Unique
    private static void reRegister(boolean shouldReRegisterForThisBlock, ResourceKey<Block> key, CallbackInfoReturnable<Block> cir, Function<BlockBehaviour.Properties, Block> newFactory, BlockBehaviour.Properties properties) {
        if (shouldReRegisterForThisBlock) {
            Block block = newFactory.apply(properties.setId(key));
            cir.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, key, block));
        }
    }
}
